package com.pyt.demo.handler;

import static com.pyt.demo.constants.PYTConstants.GSON;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.pyt.demo.service.UserService;
import com.pyt.demo.util.AWSUtil;

public class S3UserCreatorHandler implements RequestHandler<S3Event, Void> {

	private static final Logger log = Logger.getLogger(S3UserCreatorHandler.class.getName());

	@Override
	public Void handleRequest(S3Event input, Context context) {
		try {
			log.log(Level.INFO, GSON.toJson(input));
			input.getRecords().parallelStream().forEach(record -> UserService.createUser(AWSUtil.getS3Object(record)));
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception : ", e);
		}
		return null;
	}

}
