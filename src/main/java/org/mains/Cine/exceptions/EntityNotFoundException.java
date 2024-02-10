package org.mains.Cine.exceptions;

public class EntityNotFoundException extends Exception{
	/**
	 * 
	 */
	/*
	 * private static final long serialVersionUID = 1L;
	 * 
	 * public EntityNotFoundException(String entity, Long id) {
	 * super(ExceptionUtils.entityNotFoundExceptionMessage(entity, id)); }
	 */
	
	
	  private static final long serialVersionUID = 1L;
	  
	  public EntityNotFoundException(String entity, String id) {
	  //super(String.format("L'entitée %s de l'Id : %d n'existe pas.", entity,id));
		  super(createMessage(entity, id)); 
	  } 
	  private static String createMessage(String entity, String id) {
			  String message ="L'entitée %s de l'Id : %d n'existe pas."; 
		  return String.format(message, entity, id); 
	  }
	 
}
