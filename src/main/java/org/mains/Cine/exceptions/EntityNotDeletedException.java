package org.mains.Cine.exceptions;

public class EntityNotDeletedException extends Exception{
	private static final long serialVersionUID = 1L;

    public EntityNotDeletedException(String entity, String id) {
        super(ExceptionUtils.entityNotDeletedExceptionMessage(entity, id));
    }

}
