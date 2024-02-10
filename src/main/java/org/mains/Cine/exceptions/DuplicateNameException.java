package org.mains.Cine.exceptions;

public class DuplicateNameException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateNameException(String entity) {
        super(ExceptionUtils.duplicateNameExceptionMessage(entity));
    }

}
