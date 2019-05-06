package br.desafio.pitang.exception;

public class ErrorResponse {

	private String message;
	private int code;

	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
