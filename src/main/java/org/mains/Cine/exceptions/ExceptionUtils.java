package org.mains.Cine.exceptions;

import java.util.Date;

public class ExceptionUtils {
	public static String entityNotFoundExceptionMessage(String entity, String id) {
		String message = "L'entité %s de l'ID : %d n'existe pas.";
        return String.format(message, entity, id);
    }

    public static String emptyNameExceptionMessage(String entity) {
    	String message = "Le nom de l'entité %s ne peut pas être vide.";
        return String.format(message, entity);
    }

    public static String duplicateNameExceptionMessage(String entity) {
    	String message = "Une entité avec le même nom existe déjà pour %s.";
        return String.format(message, entity);
    }
    
    public static String entityNotDeletedExceptionMessage(String entity, String id) {
    	String message = "L'entité %s de l'ID : %d n'a pas été supprimée.";
        return String.format(message, entity, id);
    }
    
    public static String invalidProjectionDateException(Date projectionDate) {
    	String message = "La date de l'entité %s n'est pas valide.";
        return String.format(message, projectionDate);
    }
}
