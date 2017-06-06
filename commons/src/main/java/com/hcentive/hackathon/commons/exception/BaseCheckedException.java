/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.exception;

/**
 * @author Nitin.Gupta
 *
 */
public class BaseCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -613895247773654597L;
	
	public static final String DEFAULT_EXCEPTION_CODE = "CHECKED_EXCEPTION";

	private String exceptionCode, exceptionMessage;

	public BaseCheckedException() {
		super();
	}

	public BaseCheckedException(String message, String exceptionCode,
			Throwable e) {
		super(e);
		this.exceptionMessage = message;
		this.exceptionCode = exceptionCode;
	}

	public BaseCheckedException(String message, Throwable e) {
		super(e);
		this.exceptionMessage = message;
	}

	public BaseCheckedException(Throwable e) {
		super(e);
	}

	public BaseCheckedException(String messgae) {
		super();
		this.exceptionCode = DEFAULT_EXCEPTION_CODE;
		this.exceptionMessage = messgae;
	}

	public BaseCheckedException(String exceptionCode, String messgae) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = messgae;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
