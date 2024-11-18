package com.testvue.testvue.Utils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ClientException;


import com.testvue.testvue.properties.AliOssProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Data
@Component
@Slf4j
public class AliOssUtil {

    private final String endpoint;
    private final String accessKeyId;
    private final String accessKeySecret;
    private final String bucketName;

    // 使用构造器注入 AliOssProperties
    @Autowired
    public AliOssUtil(AliOssProperties aliOssProperties) {
        this.endpoint = aliOssProperties.getEndpoint();
        this.accessKeyId = aliOssProperties.getAccessKeyId();
        this.accessKeySecret = aliOssProperties.getAccessKeySecret();
        this.bucketName = aliOssProperties.getBucketName();
    }

    /**
     * 文件上传
     *
     * @param bytes 文件字节数组
     * @param objectName 文件名
     * @return 上传后的文件 URL
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建 OSSClient 实例。
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 创建 PutObject 请求并上传文件
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

            // 拼接文件的 URL
            String fileUrl = generateFileUrl(objectName);

            log.info("文件上传成功，文件 URL: {}", fileUrl);
            return fileUrl;

        } catch (OSSException oe) {
            log.error("OSSException occurred while uploading file to OSS. Error Message: {}", oe.getErrorMessage(), oe);
        } catch (ClientException ce) {
            log.error("ClientException occurred while uploading file to OSS. Error Message: {}", ce.getMessage(), ce);
        } catch (Exception e) {
            log.error("Exception occurred while uploading file to OSS", e);
        } finally {
            // 确保 ossClient 被正确关闭
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return null;
    }

    /**
     * 生成文件的 URL
     * @param objectName 上传文件的名称
     * @return 完整的文件访问 URL
     */
    private String generateFileUrl(String objectName) {
        // 使用 StringBuilder 拼接文件的 URL
        StringBuilder fileUrl = new StringBuilder("https://")
                .append(bucketName)
                .append(".")
                .append(endpoint.replaceAll("https://", ""))  // 去除协议部分（https://）
                .append("/")
                .append(objectName);

        return fileUrl.toString();
    }
}

