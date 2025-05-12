package com.fds.foodiexpress.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileContainer {
	@GetMapping("/profile")
	public String showProfile(){
		return "profile";
	}

}
