package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.UserCode.User;
import com.example.demo.UserCode.UserRepository;
import com.example.demo.reports.Report;
import com.example.demo.reports.ReportRepository;
//hi
@RestController
public class ReportController 
{
	@Autowired
	ReportRepository reportRepo;
	
	@Autowired
	UserRepository  userRepo;
	
	@GetMapping("/report/{username}/all")
	public @ResponseBody List<Report> getAllReports(@PathVariable String username)
	{
		if(userRepo.findByusername(username).getUserType() > 0)
		{
			return reportRepo.findAll();
		}
		return null;
	}
	
	@PostMapping("/report/newReport")
	public @ResponseBody String reportAdd (@RequestBody Report r)
	{
		
		Report re = new Report();
		re.setReportContent(r.getReportedContent());
		re.setReportedUser(r.getReportedUser());
		try
		{
			reportRepo.save(re);
			return "report succesful";
		}
		catch(Exception e)
		{
			return "report failed try again later";
		}
	}
	

	@GetMapping("report/infraction/{username}/{reportNumber}")
	public void punish(@PathVariable String username, @PathVariable Long reportNumber)
	{
		reportRepo.removeBymodId(reportNumber);
		
		User infractionee = userRepo.findByusername(username);
		infractionee.addInfraction();
		userRepo.save(infractionee);
		
		if(infractionee.getInfractions() > 3)
		{
			userRepo.delete(infractionee);
		}
	}
	
	@GetMapping("report/addModerator/{username}/{updateUser}")
	public void updateUser(@PathVariable String username, @PathVariable String updateUser)
	{
		if(userRepo.findByusername(username).getUserType() == 2)
		{
			User update = userRepo.findByusername(updateUser);
			update.updateUsertype(1);
			userRepo.save(update);
		}
	}
	
	@PostMapping("report/removeReport/{report}")
	public void removeReport(@PathVariable long report)
	{
		reportRepo.removeBymodId(report);

	}
	

}
