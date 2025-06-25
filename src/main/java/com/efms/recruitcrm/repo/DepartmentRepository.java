package com.efms.recruitcrm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efms.recruitcrm.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
