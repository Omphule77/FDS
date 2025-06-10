package com.fds.foodiexpress.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fds.foodiexpress.Service.RestaurantService;
import com.fds.foodiexpress.entity.Restaurant;

@Controller
public class RestaurantController {
	private RestaurantService restaurantService;
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService=restaurantService;
	}

	 @GetMapping("/restro-dashboard/{username}")
	    public String showDashboard(@PathVariable String username) {
		    System.out.println(username);
		    Optional<Restaurant> restro =restaurantService.findRByEmail(username);
		    System.out.println(restro);
	        return "Restaurant/restrohome"; 
	    }
}
