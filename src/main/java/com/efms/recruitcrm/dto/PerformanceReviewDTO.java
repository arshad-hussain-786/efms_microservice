package com.efms.recruitcrm.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceReviewDTO {
	
	private LocalDate reviewDate;
	private Integer score;
	private String reviewComments;

}
