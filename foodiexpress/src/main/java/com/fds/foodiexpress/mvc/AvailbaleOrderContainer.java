package com.fds.foodiexpress.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AvailbaleOrderContainer {
	@GetMapping("/orders")
	public String showAvailableOrders(){
		return "orders";
	}

}
