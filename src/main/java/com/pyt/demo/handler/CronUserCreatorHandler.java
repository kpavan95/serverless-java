package com.pyt.demo.handler;

import static com.pyt.demo.constants.PYTConstants.GSON;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.pyt.demo.service.UserService;

public class CronUserCreatorHandler implements RequestHandler<ScheduledEvent, Void> {

	private static final Logger log = Logger.getLogger(CronUserCreatorHandler.class.getName());

	@Override
	public Void handleRequest(ScheduledEvent input, Context context) {
		try {
			log.log(Level.INFO, GSON.toJson(input));
			UserService.createUser("cron_user");
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception : ", e);
		}
		return null;
	}
}
