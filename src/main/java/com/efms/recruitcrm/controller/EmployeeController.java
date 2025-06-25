package com.efms.recruitcrm.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efms.recruitcrm.dto.EmployeeDetailDTO;
import com.efms.recruitcrm.dto.EmployeeListDTO;
import com.efms.recruitcrm.entities.Employee;
import com.efms.recruitcrm.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeListDTO>> getEmployees(
			@RequestParam(name = "reviewDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reviewDate,
			@RequestParam(name = "departments",required = false) List<String> departments,
			@RequestParam(name = "projects",required = false) List<String> projects){
		
		return ResponseEntity.ok(employeeService.getFilteredEmployees(reviewDate,
				departments,projects));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDetailDTO> getEmployeeDetails(@PathVariable("id") Long id){
		return ResponseEntity.ok(employeeService.getEmployeeDetails(id));
	}

}
