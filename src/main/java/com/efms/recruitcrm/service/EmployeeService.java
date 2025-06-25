package com.efms.recruitcrm.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efms.recruitcrm.dto.EmployeeDetailDTO;
import com.efms.recruitcrm.dto.EmployeeListDTO;
import com.efms.recruitcrm.dto.PerformanceReviewDTO;
import com.efms.recruitcrm.entities.Employee;
import com.efms.recruitcrm.entities.PerformanceReview;
import com.efms.recruitcrm.entities.Project;
import com.efms.recruitcrm.exception.ResourceNotFoundException;
import com.efms.recruitcrm.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeListDTO> getFilteredEmployees(LocalDate reviewDate, List<String> departments, List<String> projects) {
		List<Employee> employees = employeeRepository.findAll();
	    Stream<Employee> filteredStream = employees.stream();

	    if (departments != null && !departments.isEmpty()) {
	        filteredStream = filteredStream.filter(emp ->
	            emp.getDepartment() != null && departments.contains(emp.getDepartment().getName()));
	    }

	    if (projects != null && !projects.isEmpty()) {
	        filteredStream = filteredStream.filter(emp ->
	            emp.getProjects().stream().map(Project::getName).anyMatch(projects::contains));
	    }

	    if (reviewDate != null) {
	        filteredStream = filteredStream.filter(emp ->
	            emp.getReviews().stream().anyMatch(r -> reviewDate.equals(r.getReviewDate())));
	    }

	    return filteredStream.map(emp -> new EmployeeListDTO(
	        emp.getId(),
	        emp.getName(),
	        emp.getEmail(),
	        emp.getDepartment() != null ? emp.getDepartment().getName() : null,
	        emp.getProjects().stream().map(Project::getName).collect(Collectors.toList()),
	        reviewDate != null ? emp.getReviews().stream()
	            .filter(r -> reviewDate.equals(r.getReviewDate()))
	            .map(r -> new PerformanceReviewDTO(r.getReviewDate(), r.getScore(), r.getReviewcomments()))
	            .collect(Collectors.toList()) : Collections.emptyList()
	    )).collect(Collectors.toList());

	}

	public EmployeeDetailDTO getEmployeeDetails(Long id) {
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found with ID: "+id));
		
		List<String> projectNames=employee.getProjects().stream()
				.map(Project::getName).collect(Collectors.toList());
		
		List<PerformanceReviewDTO> recentReviews=employee.getReviews().stream()
				.sorted(Comparator.comparing(PerformanceReview::getReviewDate).reversed())
				.limit(3)
				.map(review -> new PerformanceReviewDTO(review.getReviewDate(), review.getScore(), review.getReviewcomments()))
				.collect(Collectors.toList());
		
		EmployeeDetailDTO dto=new EmployeeDetailDTO();
		dto.setId(employee.getId());
		dto.setName(employee.getName());
		dto.setEmail(employee.getEmail());
		dto.setDepartmentName(employee.getDepartment()!=null?employee.getDepartment().getName():null);
		dto.setProjectNames(projectNames);
		dto.setLast3Reviews(recentReviews);
		
		return dto;
	}

}
