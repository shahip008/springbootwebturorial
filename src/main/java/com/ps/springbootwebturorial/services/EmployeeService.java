package com.ps.springbootwebturorial.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.ps.springbootwebturorial.dto.EmployeeDTO;
import com.ps.springbootwebturorial.entities.EmployeeEntity;
import com.ps.springbootwebturorial.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeerepository;
	private final ModelMapper modelMapper;

	public EmployeeService(EmployeeRepository employeerepository, ModelMapper modelMapper) {
	this.employeerepository=employeerepository;
	this.modelMapper=modelMapper;
	}

	public Optional<EmployeeDTO> getEmployeeById(Long id) {
	return employeerepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
	}

	public List<EmployeeDTO> getAllEmp() {
	List<EmployeeEntity> employeeEntities = employeerepository.findAll();
	return employeeEntities.stream()
	.map(employeeEntity->modelMapper.map(employeeEntity, EmployeeDTO.class))
	.collect(Collectors.toList());
	}

	public EmployeeDTO createEmployee (EmployeeDTO inputEmployee) { 
	EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class); 
	EmployeeEntity savedEmployeEntity =employeerepository.save(toSaveEntity);
	return modelMapper.map(savedEmployeEntity, EmployeeDTO.class);
	}

	public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long empid) {
	EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
	employeeEntity.setId(empid);
	EmployeeEntity savedEmployeeEntity= employeerepository.save(employeeEntity);
	return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
	}

	public Boolean deleteEmployeeById(Long employeeld) {
	Boolean checkempId=employeerepository.existsById(employeeld); 
	if(!checkempId) return false;
	employeerepository.deleteById(employeeld);
	return true;
	}

	public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
	Boolean checkempId=employeerepository.existsById(employeeId);
	if(!checkempId) return null; 
	EmployeeEntity employeeEntity = employeerepository.findById(employeeId).get(); 
	updates.forEach((field, value)->{
	Field fieldtobeUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
	fieldtobeUpdate.setAccessible(true);
	ReflectionUtils.setField(fieldtobeUpdate, employeeEntity, value); 
	});
	EmployeeEntity updatedEmployeeEntity= employeerepository.save(employeeEntity); 
	return modelMapper.map(updatedEmployeeEntity, EmployeeDTO.class);
	}

}
