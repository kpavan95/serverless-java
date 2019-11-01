package com.pyt.demo.model;

import java.util.Map;

public class ApiGatewayRequest {

	private String body;
	
	private Map<String, String> pathParameters;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getPathParameters() {
		return pathParameters;
	}

	public void setPathParameters(Map<String, String> pathParameters) {
		this.pathParameters = pathParameters;
	}

	@Override
	public String toString() {
		return "ApiGatewayRequest [body=" + body + ", pathParameters=" + pathParameters + "]";
	}
}
