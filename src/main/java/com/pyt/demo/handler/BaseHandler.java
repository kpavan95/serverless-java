package com.pyt.demo.handler;

import com.pyt.demo.model.ApiGatewayResponse;

public class BaseHandler {
	
	protected ApiGatewayResponse createSuccessResponse(String body) {
		return new ApiGatewayResponse(200, body, false);
	}
	
	protected ApiGatewayResponse createNotFoundResponse() {
		return new ApiGatewayResponse(404, "Resource Not Found", false);
	}
	
	protected ApiGatewayResponse createErrorResponse() {
		return new ApiGatewayResponse(500, "Internal Server Error", false);
	}

}
