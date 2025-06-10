package com.fds.foodiexpress.Controller;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fds.foodiexpress.Service.CustomerService;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Feedback;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;

@Controller
public class CustomerController {
	
	CustomerService userService;
	@Autowired
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
		                                .orElse("ROLE_DEFAULT"); 
		    System.out.println("Username: " + username);
		    System.out.println("Role: " + role);

		    switch (role) {
		        case "ROLE_CUSTOMER":
		            System.out.println("Redirecting to Customer Dashboard...");
		            return "redirect:/dashboard/"+username;
		        case "ROLE_RESTAURANT":
		            System.out.println("Redirecting to Restro Dashboard...");
		            return "redirect:/restro-dashboard";
		        case "ROLE_DELIVERY":
		            System.out.println("Redirecting to Delivery Panel...");
		            return "redirect:/delivery-dashboard";
		        case "ROLE_ADMIN":
		            System.out.println("Redirecting to Admin Panel...");
		            return "redirect:/admin-dashboard";
		        default:
		            System.out.println("Redirecting to Default Dashboard...");
		            return "redirect:/login";
		    }
		}
		return "login";
	}
	

//	@GetMapping("/showLogin")
//	public String showLogin(Authentication authentication) {
//		if (authentication != null && authentication.isAuthenticated()) {
//
//		            return "redirect:/goto";
//		    }
//		
//
//		
//		return "login";
//	}
	
	 @GetMapping("/showLogin")
	    public String showLoginPage(Authentication authentication, Model model,
	                                @RequestParam(value = "error", required = false) String error,
	                                @RequestParam(value = "logout", required = false) String logout,
	                                @RequestParam(value = "registered", required = false) String registered) {
	        if (authentication != null && authentication.isAuthenticated()) {
	            // User is already logged in, redirect them to their dashboard
	            return "redirect:/goto";
	        }

	        // Add attributes to the model for displaying messages on the login page
	        if (error != null) {
	            model.addAttribute("loginError", true);
	            // Optionally, you could add a more specific message based on the error type
	            // (e.g., from Spring Security's exception handling)
	        }
	        if (logout != null) {
	            model.addAttribute("logoutSuccess", true);
	        }
	        if (registered != null) {
	            model.addAttribute("registrationSuccess", true);
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

//	@PostMapping("/customerRegister")
//	public String userRegister(@ModelAttribute("ctm") Customer customer) {
//		System.out.println(customer);
//		userService.register(customer);
//		System.out.println("Done!");
//		return "redirect:/showLogin";
//	}
	
	//************************
	 @PostMapping("/customerRegister")
	    public String userRegister(@Valid @ModelAttribute("ctm") Customer customer,
	                               BindingResult bindingResult,
	                               Model model) {
	        System.out.println("Customer registration attempt: " + customer);

	        if (bindingResult.hasErrors()) {
	            System.out.println("Customer form validation errors: " + bindingResult.getAllErrors());
	            // If errors, add back the customer object (with errors) and other empty objects
	            model.addAttribute("ctm", customer);
	            model.addAttribute("dvr", new Delivery());
	            model.addAttribute("restro", new Restaurant());
	            // Set flag to show the customer form again
	            model.addAttribute("showCustomerForm", true);
	            model.addAttribute("showRestaurantForm", false);
	            model.addAttribute("showDeliveryForm", false);
	            return "multiSignUp"; // Return to the signup page
	        }

	        userService.register(customer);
	        System.out.println("Customer registered successfully!");
	        return "redirect:/showLogin"; // Redirect to login page on success
	    }

	 
	 
	 
	 
	//*****************************
	
	@PostMapping("/deliveryRegister")
	public String deliveryRegister( @Valid @ModelAttribute("dvr") Delivery delivery,BindingResult bindingResult,Model model) {
		
		
		if (bindingResult.hasErrors()) {
			  // If errors, add back the customer object (with errors) and other empty objects
          model.addAttribute("ctm", new Customer());
          model.addAttribute("dvr", delivery);
          model.addAttribute("restro",new Restaurant());
          // Set flag to show the customer form again
          model.addAttribute("showCustomerForm", false);
          model.addAttribute("showRestaurantForm", false);
          model.addAttribute("showDeliveryForm", true);
			return "multiSignUp";
	 }
		delivery.setFlag("1");
		System.out.println(delivery);
		userService.registerDelivery(delivery);
		System.out.println("Done!");
		 return "redirect:/showLogin";
	}
	
	
	
	//**********************************************
	
	@PostMapping("/restaurantRegister")
	public String restaurantRegister(@Valid @ModelAttribute("restro") Restaurant restaurant ,
			BindingResult bindingResult,
			Model model) {
		 if (bindingResult.hasErrors()) {
				  // If errors, add back the customer object (with errors) and other empty objects
	            model.addAttribute("ctm", new Customer());
	            model.addAttribute("dvr", new Delivery());
	            model.addAttribute("restro", restaurant);
	            // Set flag to show the customer form again
	            model.addAttribute("showCustomerForm", false);
	            model.addAttribute("showRestaurantForm", true);
	            model.addAttribute("showDeliveryForm", false);
				return "multiSignUp";
		 }
		System.out.println(restaurant);
		restaurant.setFlag("1"); 
		userService.registerRestaurant(restaurant);
		System.out.println("Done!");
		 return "redirect:/showLogin";
	}
	
	//******************************************
	
	@PostMapping("/updateCustomer")
	public String updateCustomer(@RequestParam(required = false) String email, 
	                             @RequestParam(required = false) String name, 
	                             @RequestParam(required = false) String address, 
	                             @RequestParam(required = false) String phone, 
	                             @RequestParam(required = false) String altPhone,
	                             RedirectAttributes redirectAttributes) {
	    userService.updateCustomerDetails(email, name, address, phone, altPhone);
	    redirectAttributes.addFlashAttribute("message", "Profile updated successfully!");
	    
	    // Redirect to userProfile page
	    return "redirect:/userProfile/"+email;
	}


	
	@GetMapping("/burger/{email}")
	public String burger(Model m,@PathVariable String email) {
	    Optional<List<FoodItems>> optionalBurger = userService.findAllBuger(); // Get Optional
	    
	    List<FoodItems> burger = optionalBurger.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("burger", burger); 
	    
	    return "Customer/burger";
	}
	
	@GetMapping("/icecream/{email}")
	public String iceCream(Model m,@PathVariable String email) {

	    Optional<List<FoodItems>> optionalIce = userService.findAllIceCream(); // Get Optional
	    
	    List<FoodItems> ice = optionalIce.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("ice", ice); 
	    
	    return "Customer/ice";
	}
	
	@GetMapping("/cake/{email}")
	public String cake(Model m,@PathVariable String email) {
	    Optional<List<FoodItems>> optionalcake = userService.findAllCake(); // Get Optional
	    
	    List<FoodItems> cake = optionalcake.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("cake", cake); 
	    
	    return "Customer/cake";
	}
	
	@GetMapping("/fries/{email}")
	public String fries(Model m,@PathVariable String email) {
	    Optional<List<FoodItems>> optionalFries = userService.findAllFries(); // Get Optional
	    
	    List<FoodItems> fries = optionalFries.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null);
	    }
	    m.addAttribute("fries", fries); 
	    
	    return "Customer/fries";
	}
	
	@GetMapping("/biryani/{email}")
	public String biryani(Model m,@PathVariable String email) {

	    Optional<List<FoodItems>> optionalBiryani = userService.findAllBiryani(); // Get Optional
	    
	    List<FoodItems> biryani = optionalBiryani.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("biryani", biryani); 
	    
	    return "Customer/biryani";
	}
	
	@GetMapping("/chicken/{email}")
	public String chicken(Model m,@PathVariable String email) {

	    Optional<List<FoodItems>> optionalChicken = userService.findAllChicken(); // Get Optional
	    
	    List<FoodItems> chicken = optionalChicken.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("chicken", chicken); 
	    
	    return "Customer/chicken";
	}
	
	@GetMapping("/pizza/{email}")
	public String pizza(Model m,@PathVariable String email) {

	    Optional<List<FoodItems>> optionalPizza = userService.findAllPizza(); // Get Optional
	    
	    List<FoodItems> pizza = optionalPizza.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("pizza", pizza); 
	    
	    return "Customer/pizza";
	}
	
	@GetMapping("/sandwitch/{email}")
	public String sandwitch(Model m,@PathVariable String email) {
	    Optional<List<FoodItems>> optionalSandwitch = userService.findAllSandwitch(); // Get Optional
	    
	    List<FoodItems> sandwitch = optionalSandwitch.orElse(null); // Unwrap Optional safely

	    Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
	    }
	    m.addAttribute("sandwitch", sandwitch); 
	    
	    return "Customer/sandwitch";
	}
	
	@GetMapping("/buynow/{id}/{email}")
	public String buynow(Model m,@PathVariable int id,@PathVariable String email) {
		FoodItems f=userService.findFoodById(id);
		Optional<Customer> c=userService.customerFind(email);
		if (c.isPresent()) {
	        m.addAttribute("ctm", c.get()); 
	    } else {
	        m.addAttribute("ctm", null); 
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
	    
	    if (orders == null || orders.isEmpty()) {
	        model.addAttribute("order", Collections.emptyList()); 
	    } else {
	        model.addAttribute("order", orders);
	    }

	    Feedback fb=new Feedback();
	    if (orders != null && !orders.isEmpty()) {
	        fb.setcEmail(orders.get(0).getcEmail()); 
	        fb.setdEmail(orders.get(0).getdEmail());
	    }
	    model.addAttribute("fb", fb);
	    return "Customer/cart";
	}
	
	@GetMapping("/userProfile/{email}")
	public String userProfile(Model m, @PathVariable String email) {
	    System.out.println("UserName: " + email);
	    Optional<Customer> c = userService.customerFind(email);

	    if (c.isPresent()) {
	        m.addAttribute("ctm", c.get());
	    } else {
	        m.addAttribute("ctm", null);
	    }
	    System.out.println(c);
	   
	    return "Customer/userProfile";
	}

	
	@PostMapping("/feedback")
	public String feedback(@ModelAttribute Feedback feedback,Model m) {
		System.out.println(feedback);
		String mail=feedback.getcEmail();
		List<Orders> orders = userService.findOrderCard(mail);
		 if (orders != null && !orders.isEmpty()) {
			 feedback.setcEmail(orders.get(0).getcEmail()); 
			 feedback.setdEmail(orders.get(0).getdEmail());
	    }
	    System.out.println("=================");
	    System.out.println(orders);
		userService.addFeedback(feedback);
		return "redirect:/card/"+mail;
	}
	

	@GetMapping("/track/{email}/{orderId}")
	public String track(@PathVariable String email,@PathVariable int orderId,Model m) {
		Orders o=userService.findOrderById(orderId);
		System.out.println(o);
		m.addAttribute("t", o);
		return "Customer/trackOrder";
	}
	
	@GetMapping("/default")
	public String defaultPage() {
		return "Customer/defaultPage";
	}
	
}
