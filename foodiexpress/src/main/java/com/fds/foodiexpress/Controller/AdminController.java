package com.fds.foodiexpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin-dashboard")
	public String admindashboard() {
		return "Admin/adminhome";
	}
}
