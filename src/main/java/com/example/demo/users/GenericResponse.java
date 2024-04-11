package com.example.demo.users;

import lombok.Data;

@Data
public class GenericResponse {

	private int statusCode;
	private String message;
	private String errorMessage;
	private Object contents;
}
