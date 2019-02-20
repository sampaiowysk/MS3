package controller;

// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0 (For details, see https://github.com/awsdocs/amazon-s3-developer-guide/blob/master/LICENSE-SAMPLECODE.)

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

public class UploadObject {

    public static String getUrlS3(String assunto) {

        GetImagem.getImg(assunto);

        String bucketName = "testbucket.gsa";
//        String fileName = "C:\\Users\\DTI-GSAMPAIO\\Desktop\\Java\\teste\\img.jpg";

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

            File file = new File("C:\\Users\\DTI-GSAMPAIO\\Desktop\\Java\\teste\\"+assunto+".png");

            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            metadata.addUserMetadata("x-amz-meta-title", assunto);
            request.setMetadata(metadata);
            request.withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(request);
            return s3Client.getUrl(bucketName, assunto + ".png").toExternalForm();
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
            return e.getMessage();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
            return e.getMessage();
        }
    }
}


