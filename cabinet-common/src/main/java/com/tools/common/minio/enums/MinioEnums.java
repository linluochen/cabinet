package com.tools.common.minio.enums;

/**
 * @Title: MinioEnums
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description: Minio 配置枚举类
 * @Date 2023/4/7 10:01
 */
public enum MinioEnums {

    // 配置 API 访问地址(启动后有 9000 的才是！)
    MINIO_ENDPOINT("http://120.53.122.214:9000"),
    // 配置密匙
    MINIO_ACCESSKEY("minioadmin"),
    // 配置密钥
    MINIO_SECRETKEY("minioadmin");

    private String val;

    MinioEnums(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
