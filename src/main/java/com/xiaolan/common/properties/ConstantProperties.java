package com.xiaolan.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author dsir
 * @create 2017-09-06 10:27
 **/
@Configuration
public class ConstantProperties {
    public static final String UPLOAD_PIC_PATH = "/upload/upload-pic/";
    public static final String UPLOAD_PIC_QRCODE_PATH = "/upload/upload-pic/upload-qr-pic/";
    public static final String ALIPLAY_PNG = "alipaly.png";
    public static final String WECHAT_PNG = "wechat.png";
    @Value("${uploadPath}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
