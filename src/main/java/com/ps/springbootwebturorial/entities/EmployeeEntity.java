package com.ps.springbootwebturorial.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name= "employees")
public class EmployeeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Auto-generate IDs
	private long id;
	private String name;
	private String email;
	private Integer age;
	private Integer salary;
	private String role;
	private LocalDate dateOfJoining; 
	public EmployeeEntity(long id, String name, String email, Integer age, Integer salary, String role,
			LocalDate dateOfJoining, Boolean isActive) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.salary = salary;
		this.role = role;
		this.dateOfJoining = dateOfJoining;
		this.isActive = isActive;
	}
	public EmployeeEntity() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@JsonProperty("isActive")
	private Boolean isActive;
	
}
