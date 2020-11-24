package com.example.demo.reports;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.*;

@Entity
public class Report 
{
	 static AtomicLong modIdI = new AtomicLong();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "modId")
	public Long modId = modIdI.getAndIncrement();
	@Column
	public String username;
	
	@Column
	public String reportContent;
	
	public void setReportedUser(String user)
	{
		this.username = user;
	}
	
	public void setReportContent(String content)
	{
		this.reportContent = content;
	}
	
	public String getReportedContent()
	{
		return this.reportContent;
	}
	
	public String getReportedUser()
	{
		return this.username;
	}
	

}
