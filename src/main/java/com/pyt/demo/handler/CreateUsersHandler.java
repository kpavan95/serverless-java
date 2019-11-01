package com.pyt.demo.handler;

import static com.pyt.demo.constants.PYTConstants.GSON;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pyt.demo.model.ApiGatewayRequest;
import com.pyt.demo.model.ApiGatewayResponse;
import com.pyt.demo.model.CreateUserRequest;
import com.pyt.demo.model.User;
import com.pyt.demo.service.UserService;

public class CreateUsersHandler extends BaseHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

	private static final Logger log = Logger.getLogger(CreateUsersHandler.class.getName());

	@Override
	public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
		try {
			log.log(Level.INFO, GSON.toJson(input));
			CreateUserRequest request = GSON.fromJson(input.getBody(), CreateUserRequest.class);
			User user = UserService.createUser(request.getUserName());
			return createSuccessResponse(GSON.toJson(user));
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception : ", e);
			return createErrorResponse();
		}
	}

}
