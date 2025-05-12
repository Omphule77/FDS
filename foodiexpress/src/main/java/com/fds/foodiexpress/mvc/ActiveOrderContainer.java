package com.fds.foodiexpress.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActiveOrderContainer {
	@GetMapping("/details")
	public String showActiveOrders(){
		return "HTML/details";
	}

}
