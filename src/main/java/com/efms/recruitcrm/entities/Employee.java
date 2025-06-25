package com.efms.recruitcrm.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private LocalDate dateOfJoining;
	private Double salary;
	
	@ManyToOne
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;
	
	@OneToMany(mappedBy = "employee")
	private List<PerformanceReview> reviews;
	
	@ManyToMany
	@JoinTable(name = "employee_project",
			joinColumns = @JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="project_id") )
	private List<Project> projects;
	
	

}
