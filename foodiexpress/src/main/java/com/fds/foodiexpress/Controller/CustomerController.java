package com.fds.foodiexpress.Controller;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fds.foodiexpress.Service.CustomerService;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.persistence.criteria.Order;

@Controller
public class CustomerController {
	
	CustomerService userService;
	public CustomerController(CustomerService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/dashboard/{username}")
	public String afterLogin(Model m,@PathVariable String username) {
		System.out.println("UserName: "+username);
		Optional<Customer> c=userService.customerFind(username);
		List<FoodItems> f= userService.findAllFood();
		System.out.println(f);
		m.addAttribute("food", f);
		m.addAttribute("ctm", c.orElse(null));
		System.out.println(c);
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
		            return "redirect:/dashboard/"+username;
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
	
	@GetMapping("/burger/{email}")
	public String burger(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalBurger = userService.findAllBuger(); // Get Optional
	    
	    FoodItems burger = optionalBurger.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("burger", burger); 
	    
	    return "Customer/burger";
	}
	
	@GetMapping("/icecream/{email}")
	public String iceCream(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalIce = userService.findAllIceCream(); // Get Optional
	    
	    FoodItems ice = optionalIce.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("ice", ice); 
	    
	    return "Customer/ice";
	}
	
	@GetMapping("/cake/{email}")
	public String cake(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalcake = userService.findAllCake(); // Get Optional
	    
	    FoodItems cake = optionalcake.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("cake", cake); 
	    
	    return "Customer/cake";
	}
	
	@GetMapping("/fries/{email}")
	public String fries(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalFries = userService.findAllFries(); // Get Optional
	    
	    FoodItems fries = optionalFries.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("fries", fries); 
	    
	    return "Customer/fries";
	}
	
	@GetMapping("/biryani/{email}")
	public String biryani(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalBiryani = userService.findAllBiryani(); // Get Optional
	    
	    FoodItems biryani = optionalBiryani.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("biryani", biryani); 
	    
	    return "Customer/biryani";
	}
	
	@GetMapping("/chicken/{email}")
	public String chicken(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalChicken = userService.findAllChicken(); // Get Optional
	    
	    FoodItems chicken = optionalChicken.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("chicken", chicken); 
	    
	    return "Customer/chicken";
	}
	
	@GetMapping("/pizza/{email}")
	public String pizza(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalPizza = userService.findAllPizza(); // Get Optional
	    
	    FoodItems pizza = optionalPizza.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("pizza", pizza); 
	    
	    return "Customer/pizza";
	}
	
	@GetMapping("/sandwitch/{email}")
	public String sandwitch(Model m,@PathVariable String email) {
	    Optional<FoodItems> optionalSandwitch = userService.findAllSandwitch(); // Get Optional
	    
	    FoodItems sandwitch = optionalSandwitch.orElse(null); // Unwrap Optional safely
	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
	    m.addAttribute("sandwitch", sandwitch); 
	    
	    return "Customer/sandwitch";
	}
	
	@GetMapping("/buynow/{id}/{email}")
	public String buynow(Model m,@PathVariable int id,@PathVariable String email) {
		FoodItems f=userService.findFoodById(id);
		Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); // Extract the Customer object
	    } else {
	        m.addAttribute("ctm", null); // Handle missing customer gracefully
	    }
		m.addAttribute("food", f);
		Orders order=new Orders();
		m.addAttribute("order", order);
		return "Customer/buynow";
	}
	
	@PostMapping("/order")
	public String order(@ModelAttribute Orders order,Model m) {
		System.out.println(order);
		userService.addctmorder(order);
		m.addAttribute("order", order);
		return "Customer/success";
	}
	
	@GetMapping("/card/{email}")
	public String card(@PathVariable String email, Model model) {
	    List<Orders> orders = userService.findOrderCard(email);
	    
	    // Ensure the list isn't null or empty before passing to the view
	    if (orders == null || orders.isEmpty()) {
	        model.addAttribute("order", Collections.emptyList()); 
	    } else {
	        model.addAttribute("order", orders);
	    }

	    Feedback fb=new Feedback();
	    if (orders != null && !orders.isEmpty()) {
	        fb.setcEmail(orders.get(0).getcEmail()); 
	    }
	    model.addAttribute("fb", fb);
	    return "Customer/cart";
	}
	
	@PostMapping("/feedback")
	public String feedback(@ModelAttribute Feedback feedback,Model m) {
		System.out.println(feedback);
		String mail=feedback.getcEmail();
		userService.addFeedback(feedback);
		return "redirect:/card/"+mail;
	}

	
	
	
}
