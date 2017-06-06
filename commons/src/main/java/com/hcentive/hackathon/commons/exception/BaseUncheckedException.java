/**
 * All rights reserved.hCentive.
 */
package com.hcentive.hackathon.commons.exception;

/**
 * @author Nitin.Gupta
 *
 */
public class BaseUncheckedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066616152365735956L;

	public static final String DEFAULT_EXCEPTION_CODE = "UNCHECKED_EXCEPTION";

	private String exceptionCode, exceptionMessage;

	public BaseUncheckedException() {
		super();
	}
	
	public BaseUncheckedException(String message, String exceptionCode, Throwable e) {
		super(e);
		this.exceptionMessage = message;
		this.exceptionCode = exceptionCode;
	}
	
	public BaseUncheckedException(String message, Throwable e) {
		super(e);
		this.exceptionMessage = message;
	}
	
	public BaseUncheckedException(Throwable e) {
		super(e);
	}

	public BaseUncheckedException(String messgae) {
		super();
		this.exceptionCode = DEFAULT_EXCEPTION_CODE;
		this.exceptionMessage = messgae;
	}
	
	public BaseUncheckedException(String exceptionCode, String messgae) {
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
