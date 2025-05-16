package com.fds.foodiexpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {

	 @GetMapping("/restro-dashboard")
	    public String showDashboard() {
	        return "Restaurant/restrohome"; // Must match index.html inside /templates/Delivery/
	        
	    }
}
