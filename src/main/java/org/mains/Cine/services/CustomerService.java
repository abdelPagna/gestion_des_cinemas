package org.mains.Cine.services;

import java.util.List;

import org.mains.Cine.dto.CustomerDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;


public interface CustomerService {
	 List<CustomerDto> findAllCustomers();
	 CustomerDto getOne(String id) throws EntityNotFoundException;
	 CustomerDto save(CustomerDto customerDto)throws EmptyNameException, DuplicateNameException;
	 void delete(String id)throws EntityNotDeletedException;
	 CustomerDto update(CustomerDto customerDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException;
}
