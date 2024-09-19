package com.ps.springbootwebturorial.dto;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDTO {
	private long id;
	@NotNull(message = "Required field in Employee: name")
	private String name;
	@NotBlank(message = "Email of employee should not be null")
	@Email(message ="Email of employee should be valid")
	private String email;
	@NotNull(message = "age of employee should not be null")
	@Max(value = 80, message ="age of employee should not be greater than 80")
	@Min(value = 18, message ="age of employee should not be less than 18")
	private Integer age;
	@NotNull(message = "role of employee should not be null")
	@Pattern (regexp = "^(ADMIN|USER)$", message = "role of employee should either ADMIN or USER")
	private String role; //ADMIN, USER
	@NotNull(message = "salary of employee should not be null")
	@Positive (message = "salary of employee should only be positive")
	@Digits (integer = 6, fraction= 2, message ="The salary can be in the form of XXXX.YY")
	@DecimalMax(value = "1000000.99")
	@DecimalMin(value = "100.50")
	private Integer salary;
	@PastOrPresent(message = "DOJ of employee cannot be in future")
	private LocalDate dateOfJoining;
	@AssertTrue(message="Employee should be active")
	@JsonProperty("isActive")
	private Boolean isActive;
	public EmployeeDTO() {
	}
	
	public EmployeeDTO(long id, String name, String email, Integer age, Integer salary, String role, LocalDate dateOfJoining, Boolean isActive) {
			this.id = id;
			this.name =name;
			this.email= email;
			this.age= age;
			this.salary = salary;
			this.role= role;
			this.dateOfJoining =dateOfJoining;
			this.isActive = isActive;
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
			this.email= email;
		}

		public Integer getAge() { 
			return age;
		}

		public void setAge(Integer age) { 
			this.age = age;
		}

		public String getRole() { 
			return role;
		} 
		
		public void setRole (String role) { 
			this.role= role;
		} 
		public Integer getSalary() { 
			return salary;
		} 
		
		public void setSalary (Integer salary) { 
			this.salary = salary;
		} 
		
		public LocalDate getDateOfJoining() { 
			return dateOfJoining;
		} 
		
		public void setDateOfJoining (LocalDate dateOfJoining) { 
			this.dateOfJoining =dateOfJoining;
		}

		public Boolean getIsActive() { 
			return isActive;
		} 
		
		public void setIsActive (Boolean isActive) { 
			this.isActive = isActive;
		}
	
}
