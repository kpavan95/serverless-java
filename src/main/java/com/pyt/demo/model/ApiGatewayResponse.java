package com.pyt.demo.model;

import java.util.Collections;
import java.util.Map;

public class ApiGatewayResponse {
	
	private int statusCode;
	
	private String body;
	
	private Map<String, String> headers= Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless");
	
	private boolean isBase64Encoded;

	public ApiGatewayResponse(int statusCode, String body, boolean isBase64Encoded) {
		super();
		this.statusCode = statusCode;
		this.body = body;
		this.isBase64Encoded = isBase64Encoded;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}

	public void setBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

	@Override
	public String toString() {
		return "ApiGatewayResponse [statusCode=" + statusCode + ", body=" + body + ", headers=" + headers
				+ ", isBase64Encoded=" + isBase64Encoded + "]";
	}

}
