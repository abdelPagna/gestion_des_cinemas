package org.mains.Cine.exceptions;

public class EmptyNameException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyNameException(String entity) {
        super(ExceptionUtils.emptyNameExceptionMessage(entity));
    }

}
