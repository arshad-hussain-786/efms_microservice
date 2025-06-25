package com.efms.recruitcrm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efms.recruitcrm.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
