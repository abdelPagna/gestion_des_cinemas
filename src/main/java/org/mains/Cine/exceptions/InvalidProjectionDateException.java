package org.mains.Cine.exceptions;

import java.util.Date;

public class InvalidProjectionDateException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidProjectionDateException(Date projectionDate) {
		super(ExceptionUtils.invalidProjectionDateException(projectionDate));
	}

}
