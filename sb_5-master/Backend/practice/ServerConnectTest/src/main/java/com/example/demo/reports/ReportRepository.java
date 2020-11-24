package com.example.demo.reports;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportRepository extends JpaRepository<Report, Integer>
{
	@Transactional
	void removeBymodId(Long m);
	
}