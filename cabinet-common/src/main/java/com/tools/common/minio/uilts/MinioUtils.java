package com.tools.common.minio.uilts;

import com.tools.common.minio.enums.MinioEnums;
import io.minio.*;
import io.minio.http.Method;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @Title: MinioUtils
 * @Author LinLuoChen
 * @ProjectName Cabinet-Vue-master
 * @Description: Minio API 工具类
 * @Date 2023/4/6 14:33
 */
@Component
public class MinioUtils {

    /**
     * @Description 创建 minio 连接对象
     * @Author LinLuoChen
     * @Date 2023/4/6 16:15
     * @Return io.minio.MinioClient
     **/
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(MinioEnums.MINIO_ENDPOINT.getVal())
                .credentials(MinioEnums.MINIO_ACCESSKEY.getVal(), MinioEnums.MINIO_SECRETKEY.getVal())
                .build();
    }

    /*
     * @Description 检查桶是否存在
     * @Author LinLuoChen
     * @Date  2023/4/6 16:15
     * @Param [
     *          minioClient 连接地址
     *          bucketName 桶名称
     * ]
     * @Return void
     **/
    public void getMinioExists(String bucketName) throws Exception {
        BucketExistsArgs bucket = BucketExistsArgs.builder().bucket(bucketName).build();
        boolean isExist = minioClient().bucketExists(bucket);
        if (isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 不存在就创建一个桶
            if (!minioClient().bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build())) {
                minioClient().makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build()
                );
                System.out.printf("%s,创建成功\n" , bucketName);
            } else {
                System.out.printf("%s,已存在\n" , bucketName);
            }
        }
    }

    /**
     * @Description 删除桶或删除文件
     * @Author LinLuoChen
     * @Date 2023/4/7 15:59
     * @Param [
     * bucketName 桶名称
     * objectName 文件名称
     * type 1 删除桶 2 删除文件
     * ]
     * @Return void
     **/
    public void getRemoveObject(String bucketName, String objectName, Integer type) throws Exception {
        if (type == 1) {
            // 删除桶
            minioClient().removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        } else {
            // 删除文件
            minioClient().removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
        }
    }

    /**
     * @Description 上传文件（文件流）
     * @Author LinLuoChen
     * @Date 2023/4/6 16:34
     * @Param [
     * minioClient
     * bucketName 桶名称
     * ]
     * @Return void
     **/
    public void getInputStream(InputStream inputStream, String bucketName, String fileName) throws Exception {
        // 创建 InputStream 上传
        // 测试的时候使用
//        File file = new File("C:\\Users\\30447\\Desktop\\2.png");
//        InputStream inputStream = new FileInputStream(file);
        long start = System.currentTimeMillis();
        // 上传流
        minioClient().putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object("2023/4/6/" + fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build()
        );
        inputStream.close();
        System.out.println("uploaded successfully 耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * @Description Minio 下载 / 预览文件（取决于 contentType 属性）
     * @Author LinLuoChen
     * @Date 2023/4/6 16:17
     * @Param [
     * minioClient
     * bucketName 桶名称
     * objectName
     * ]
     * @Return java.lang.String
     **/
    public void getObjectFileUrl(String bucketName, String objectName, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        response.setHeader("Content-Disposition" , "inline;filename=" + URLEncoder.encode(objectName, "UTF-8"));
        InputStream in = minioClient().getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
        IOUtils.copy(in, response.getOutputStream());
    }

    /**
     * @Description 获取文件 URL 路径 返回临时带签名、过期时间一天、Get请求方式的访问URL
     * @Author LinLuoChen
     * @Date 2023/4/7 10:29
     * @Param [
     * minioClient 连接对象
     * bucketName 桶名称
     * objectName 文件名称
     * ]
     * @Return void
     **/
    public String getPreassignedObjectUrl(String bucketName, String objectName) throws Exception {
        return minioClient().getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(60 * 60 * 24)
                        .build()
        );
    }

    /**
     * @Description 取带签名的临时上传元数据对象，前端可获取后，直接上传到Minio
     * @Author LinLuoChen
     * @Date 2023/4/7 15:50
     * @Param [
     * bucketName 桶名称
     * fileName 文件名称
     * ]
     * @Return java.util.Map<java.lang.String, java.lang.String>
     **/
    public Map<String, String> getPresignedPostFormData(String bucketName, String fileName) throws Exception {
        // 为存储桶创建一个上传策略，过期时间为7天
        PostPolicy policy = new PostPolicy(bucketName, ZonedDateTime.now().plusDays(7));
        // 设置一个参数key，值为上传对象的名称
        policy.addEqualsCondition("key" , fileName);
        // 添加Content-Type以"image/"开头，表示只能上传照片
        policy.addStartsWithCondition("Content-Type" , "image/");
        // 设置上传文件的大小 64kiB to 10MiB.
        policy.addContentLengthRangeCondition(64 * 1024, 10 * 1024 * 1024);
        return minioClient().getPresignedPostFormData(policy);
    }


}