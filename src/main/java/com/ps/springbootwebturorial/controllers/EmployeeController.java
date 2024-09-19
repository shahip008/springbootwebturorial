package com.ps.springbootwebturorial.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ps.springbootwebturorial.services.EmployeeService;
import com.ps.springbootwebturorial.dto.*;
import com.ps.springbootwebturorial.exception.ResourceNotFoundException;
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private final EmployeeService employeeservice;
	public EmployeeController (EmployeeService employeeservice) { 
		this.employeeservice=employeeservice;
	}
	
	@GetMapping(path = "/getSec")
	public String getMySuperSecretMessage() {
	return "hello princefq";
	}
	
	@GetMapping(path="/{employeeId}")
	public ResponseEntity <EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id) {
	Optional<EmployeeDTO> employeeDTO= employeeservice.getEmployeeById(id);
	return employeeDTO
	.map(employeeDT01 -> ResponseEntity.ok(employeeDT01)) 
	.orElseThrow(()->new ResourceNotFoundException("Employee not found"));
	}
	
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getAllEmp(@RequestParam(required = false) Integer age, 
											@RequestParam(required = false) String sortBy) {
	return ResponseEntity.ok(employeeservice.getAllEmp());
	}


	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
	EmployeeDTO createemp = employeeservice.createEmployee(inputEmployee);
	return new ResponseEntity<>(createemp, HttpStatus.CREATED);
	}

	@PutMapping(path="/{employeeId}")
	public ResponseEntity <EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
	return ResponseEntity.ok(employeeservice.updateEmployeeById(employeeDTO, employeeId));
	}
	
	@DeleteMapping(path="/{employeeId}")
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
	Boolean gotDeleted = employeeservice.deleteEmployeeById(employeeId);
	if(gotDeleted) return ResponseEntity.ok(true);
	return ResponseEntity.notFound().build();
	}
	
	@PatchMapping(path="/{employeeId}")
	public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
																	@PathVariable Long employeeId) {
	EmployeeDTO employeeDTO = employeeservice.updatePartialEmployeeById(updates, employeeId); 
	if(employeeDTO == null) 
	return ResponseEntity.notFound().build();
	return ResponseEntity.ok(employeeDTO);
	}
}
