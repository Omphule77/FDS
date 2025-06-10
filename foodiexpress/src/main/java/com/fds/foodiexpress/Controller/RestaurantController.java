package com.fds.foodiexpress.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fds.foodiexpress.Service.RestaurantService;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

@Controller
public class RestaurantController {
	private RestaurantService restaurantService;
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService=restaurantService;
	}

	 @GetMapping("/restro-dashboard/{username}")
	    public String showDashboard(@PathVariable String username,Model model) {
		    System.out.println(username);
		    Optional<Restaurant> restro =restaurantService.findRByEmail(username);
		    model.addAttribute("remail", username);
	        return "Restaurant/restrohome"; 
	    }
	 

	 @GetMapping("/addFood/{remail}")
	 public String addFood(@PathVariable String remail, Model m) {
	     System.out.println(remail);
	     
	     Optional<Restaurant> optionalRestro = restaurantService.findRByEmail(remail);
	     
	     if (optionalRestro.isPresent()) {
	         Restaurant restro = optionalRestro.get(); // Get the actual Restaurant object
	         m.addAttribute("restro", restro);
	     } else {
	         System.out.println("Restaurant not found!");
	         m.addAttribute("restro", new Restaurant()); // To prevent errors in the Thymeleaf template
	     }
	     
	     FoodItems foodItems = new FoodItems();
	     m.addAttribute("foodItem", foodItems);
	     
	     return "Restaurant/addorder";
	 }

	 @PostMapping("/addFoodItem")
	 public String addFoodItem(@ModelAttribute FoodItems foodItems,@RequestParam String remail) {
		 System.out.println(foodItems);
		 System.out.println(remail);
		 restaurantService.addFood(foodItems);
		 return "redirect:/addFood/"+remail;
	 }
}
