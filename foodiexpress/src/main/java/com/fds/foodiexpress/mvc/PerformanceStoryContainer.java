package com.fds.foodiexpress.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerformanceStoryContainer {
	
	@GetMapping("/performance")
	public String showPerformance(){
		return "performance";
	}
	
}
