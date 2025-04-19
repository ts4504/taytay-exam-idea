package com.athome.util;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

public class COSUtil {
    //腾讯云密钥。
    private static String SECRETID = System.getenv("COS_SECRETID");
    private static String SECRETKEY = System.getenv("COS_SECRETKEY");
    private static String BUCKETNAME = "taytaynote-1330094563";

    // 2 设置 bucket 的地域, COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
    private static Region REGION = new Region("ap-guangzhou");


    public static String uploadFile(String fileName,InputStream in) {
        COSCredentials cred = new BasicCOSCredentials(SECRETID, SECRETKEY);
        ClientConfig clientConfig = new ClientConfig(REGION);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
        // 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, fileName, in, objectMetadata);

        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        //https://taytaynote-1330094563.cos.ap-guangzhou.myqcloud.com/exam-project%2Fd490d0d1-beb7-47ff-bd00-6ca5d8f8c0be.webp

        return "http://"+BUCKETNAME+".cos."+REGION.getRegionName()+".myqcloud.com/"+fileName;
    }
}
