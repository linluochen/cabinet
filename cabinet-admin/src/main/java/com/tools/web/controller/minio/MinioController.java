package com.tools.web.controller.minio;

import com.tools.common.annotation.Anonymous;
import com.tools.common.minio.uilts.MinioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Title: MinioController
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description:
 * @Date 2023/4/7 10:49
 */
@Anonymous
@RestController
@RequestMapping("/minio")
public class MinioController {

    @Autowired
    MinioUtils minioUtils;

    /**
     * @Description 测试上传文件
     * @Author LinLuoChen
     * @Date 2023/4/7 15:57
     * @Return void
     **/
    @ResponseBody
    @RequestMapping("/uploadFiles")
    public void getFalg() throws Exception {
        File file = new File("C:\\Users\\30447\\Desktop\\2.png");
        InputStream inputStream = new FileInputStream(file);
        minioUtils.getInputStream(inputStream, "files" , "2.png");
    }

}
