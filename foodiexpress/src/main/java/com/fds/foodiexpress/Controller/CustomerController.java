package com.fds.foodiexpress.Controller;


import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fds.foodiexpress.Service.CustomerService;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

@Controller
public class CustomerController {
	
	CustomerService userService;
	public CustomerController(CustomerService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/dashboard")
	public String afterLogin() {
		return "customer/userNavBar";
	}
	
	@GetMapping("/goto")
	public String gotoDash(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
		    String username = authentication.getName();
		    String role = authentication.getAuthorities().stream()
		                                .map(GrantedAuthority::getAuthority)
		                                .findFirst()
		                                .orElse("ROLE_DEFAULT"); // Default role if none found

		    System.out.println("Username: " + username);
		    System.out.println("Role: " + role);

		    switch (role) {
		        case "ROLE_CUSTOMER":
		            System.out.println("Redirecting to Admin Dashboard...");
		            return "redirect:/dashboard";
		        case "ROLE_RESTAURANT":
		            System.out.println("Redirecting to Restro Dashboard...");
		            return "redirect:/restro-dashboard";
		        case "ROLE_DELIVERY":
		            System.out.println("Redirecting to Moderator Panel...");
		            return "redirect:/delivery-dashboard";
		        case "ROLE_ADMIN":
		            System.out.println("Redirecting to Moderator Panel...");
		            return "redirect:/admin-dashboard";
		        default:
		            System.out.println("Redirecting to Default Dashboard...");
		            return "redirect:/login";
		    }
		}
		return "login";
	}
	
	@GetMapping("/showLogin")
	public String showLogin(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
//		    String username = authentication.getName();
//		    String role = authentication.getAuthorities().stream()
//		                                .map(GrantedAuthority::getAuthority)
//		                                .findFirst()
//		                                .orElse("ROLE_DEFAULT"); // Default role if none found
//
//		    System.out.println("Username: " + username);
//		    System.out.println("Role: " + role);
//
//		    switch (role) {
//		        case "ROLE_CUSTOMER":
//		            System.out.println("Redirecting to Admin Dashboard...");
//		            return "redirect:/dashboard";
//		        case "ROLE_RESTAURANT":
//		            System.out.println("Redirecting to Restro Dashboard...");
//		            return "redirect:/restro-dashboard";
//		        case "ROLE_DELIVERY":
//		            System.out.println("Redirecting to Moderator Panel...");
//		            return "redirect:/delivery-dashboard";
//		        default:
//		            System.out.println("Redirecting to Default Dashboard...");
		            return "redirect:/login";
		    }
		

		
		return "login";
	}
	
	@GetMapping("/showRegister")
	public String showRegister(Model model) {
		Customer customer=new Customer();
		Delivery delivery=new Delivery();
		Restaurant restaurant=new Restaurant();
		model.addAttribute("ctm",customer);
		model.addAttribute("dvr",delivery);
		model.addAttribute("restro", restaurant);
		return "multiSignUp";
	}

	@PostMapping("/customerRegister")
	public String userRegister(@ModelAttribute("ctm") Customer customer) {
		System.out.println(customer);
		userService.register(customer);
		System.out.println("Done!");
		return "redirect:/showLogin";
	}
	
	@PostMapping("/deliveryRegister")
	public String deliveryRegister(@ModelAttribute("dvr") Delivery delivery) {
		delivery.setFlag("1");
		System.out.println(delivery);
		userService.registerDelivery(delivery);
		System.out.println("Done!");
		return "login";
	}
	
	@PostMapping("/restaurantRegister")
	public String restaurantRegister(@ModelAttribute("restro") Restaurant restaurant) {
		restaurant.setFlag("1");
		System.out.println(restaurant);
		userService.registerRestaurant(restaurant);
		System.out.println("Done!");
		return "login";
	}
	
}
