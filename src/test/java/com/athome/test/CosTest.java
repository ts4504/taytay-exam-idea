package com.athome.test;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CosTest {
    @Test
    public void test() {
        String SECRETID = System.getenv("COS_SECRETID");
        String SECRETKEY = System.getenv("COS_SECRETKEY");
        String JAVAHOME = System.getenv("JAVA_HOME");

        System.out.println(SECRETID);
        System.out.println(SECRETKEY);
        System.out.println(JAVAHOME);
    }

}
