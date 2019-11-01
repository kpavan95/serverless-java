package com.pyt.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class AWSUtil {
	
	private static Logger log = Logger.getLogger(AWSUtil.class.getName());

	private static AmazonS3 s3;

	/**
	 * The s3 event is processed here to obtain the final msg as text object. We use
	 * the bucket name and the key(name of the object) to fetch the data as a
	 * stream. This stream is then converted to a string and returned
	 * 
	 * @param record
	 * @return
	 */
	public static String getS3Object(S3EventNotificationRecord record) {
		String bucketName = record.getS3().getBucket().getName();
		String s3Key = record.getS3().getObject().getKey().replace('+', ' ');
		log.log(Level.INFO, "bucket Name : " + bucketName+" Key : "+s3Key);
		try {
			s3Key = URLDecoder.decode(s3Key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.log(Level.SEVERE, "Failed to decode string", e);
		}
		AmazonS3 s3Client = getAmazonS3();
		/*
		 * get object file using source bucket and s3Key name
		 */
		S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, s3Key));
		InputStream objectData = s3Object.getObjectContent(); // get content of the file
		String data = null;
		try {
			data = IOUtils.toString(objectData, "UTF-8");
		} catch (IOException e) {
			log.log(Level.SEVERE, "Failed to create string from S3 object", e);
		}
		return data;
	}

	private static AmazonS3 getAmazonS3() {
		if (s3 == null) {
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();
		}
		return s3;
	}

}
