package com.pyt.demo.handler;

import static com.pyt.demo.constants.PYTConstants.GSON;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pyt.demo.model.ApiGatewayRequest;
import com.pyt.demo.model.ApiGatewayResponse;
import com.pyt.demo.service.UserService;

public class DeleteUserHandler extends BaseHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

	private static final Logger log = Logger.getLogger(DeleteUserHandler.class.getName());

	@Override
	public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
		try {
			log.log(Level.INFO, GSON.toJson(input));
			Map<String, String> pathParameters = input.getPathParameters();
			String userId = pathParameters.get("userId");
			UserService.deleteUser(userId);
			return createSuccessResponse(userId);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception : ", e);
			return createErrorResponse();
		}
	}
}
