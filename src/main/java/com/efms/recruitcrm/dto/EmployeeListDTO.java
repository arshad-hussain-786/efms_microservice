package com.efms.recruitcrm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListDTO {
    private Long id;
    private String name;
    private String email;
    private String departmentName;
    private List<String> projectNames;
    private List<PerformanceReviewDTO> reviewsOnDate;
}