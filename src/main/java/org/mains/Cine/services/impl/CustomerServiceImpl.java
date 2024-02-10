package org.mains.Cine.services.impl;

import java.util.List;
import java.util.Optional;

import org.mains.Cine.domain.Customer;
import org.mains.Cine.dto.CustomerDto;
import org.mains.Cine.exceptions.DuplicateNameException;
import org.mains.Cine.exceptions.EmptyNameException;
import org.mains.Cine.exceptions.EntityNotDeletedException;
import org.mains.Cine.exceptions.EntityNotFoundException;
import org.mains.Cine.mapper.CustomerMapper;
import org.mains.Cine.repositories.CustomerRepository;
import org.mains.Cine.services.CustomerService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	@Override
	public List<CustomerDto> findAllCustomers() {
		List<Customer> listCustomers= customerRepository.findAll();
		return customerMapper.toDto(listCustomers);
	}

	@Override
	public CustomerDto getOne(String id) throws EntityNotFoundException {
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isPresent()) {
			return customerMapper.toDto(customer.get());
		} else {
			throw new EntityNotFoundException("Clients", id);
		}
	}

	@Override
	public CustomerDto save(CustomerDto customerDto) throws EmptyNameException, DuplicateNameException {
		Customer customer = customerMapper.toEntity(customerDto);

	    // Logique métier
	    validateCustomerName(customer.getName());
	    checkDoublonCustomer(customer);

	    Customer savedCustomer = customerRepository.save(customer);

	    return customerMapper.toDto(savedCustomer);
	}

	private void validateCustomerName(String customerName) throws EmptyNameException {
	    if (customerName == null || customerName.isEmpty()) {
	        throw new EmptyNameException(customerName);
	    }
	}

	private void checkDoublonCustomer(Customer customer) throws DuplicateNameException {
	    // Vérifier si un Customer avec le même nom existe déjà
	    Optional<Customer> existingCustomer = customerRepository.findByNameIgnoreCase(customer.getName());

	    if (existingCustomer.isPresent()) {
	        throw new DuplicateNameException(customer.toString());

	    }

	}


	@Override
	public void delete(String id) throws EntityNotDeletedException {
		Optional<Customer> Customer=customerRepository.findById(id);
		if(Customer.isPresent()) {
			customerRepository.delete(Customer.get());
		} else {
			throw new EntityNotDeletedException("Client", id);
		}
	}


	@Override
	public CustomerDto update(CustomerDto customerDto, String id) throws EntityNotFoundException, EmptyNameException, DuplicateNameException  {

		Customer customer = customerRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Clients", id));

	    // Logique métier
	    validateCustomerName(customerDto.getName());
	    checkDoublonCustomer(customerMapper.toEntity(customerDto));

	    Customer updatedCustomer = customerMapper.toEntity(customerDto);
	    updatedCustomer.setId(customer.getId());
	    updatedCustomer.setName(customer.getName());
	    updatedCustomer.setEmail(customer.getEmail());
	    updatedCustomer.setNumeroTelephone(customer.getNumeroTelephone());

	    updatedCustomer = customerRepository.save(updatedCustomer);
	    return customerMapper.toDto(updatedCustomer);
	}



}
