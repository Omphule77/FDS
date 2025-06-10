package com.fds.foodiexpress.Controller;

import com.fds.foodiexpress.Service.RestaurantService;
import com.fds.foodiexpress.entity.Orders;
import com.fds.foodiexpress.entity.Restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fds.foodiexpress.Service.RestaurantService;
import com.fds.foodiexpress.entity.FoodItems;
import com.fds.foodiexpress.entity.Restaurant;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/restaurant/{rName}") 
    public String viewAllRestaurantOrders(@PathVariable String rName, Model model) {
        List<Orders> pendingOrders = restaurantService.findPendingOrders(rName);

        model.addAttribute("orders", pendingOrders);
        model.addAttribute("restaurantName", rName);
        model.addAttribute("flag", 0);
        return "Restaurant/rest_orders";
    }
    
    


    @GetMapping("/restaurant/completed-orders/{rName}") 
    public String viewCompletedRestaurantOrders(@PathVariable String rName, Model model) {
        List<Orders> completedOrders = restaurantService.findCompletedOrders(rName);

        model.addAttribute("orders", completedOrders);
        model.addAttribute("restaurantName", rName);
        model.addAttribute("flag", 1); 
        return "Restaurant/rest_orders";
    }

    // This method handles both pending and completed orders based on the flag
    @GetMapping("/restaurant/orders")
    public String viewFilteredRestaurantOrders(@RequestParam("restaurantName") String restaurantName,
                                               @RequestParam("flag") int flag,
                                               Model model) {
        List<Orders> orders;

        if (flag == 0) { // Pending orders
            orders = restaurantService.findPendingOrders(restaurantName);
        } else if (flag == 1) { // Completed orders
            orders = restaurantService.findCompletedOrders(restaurantName);
        } else {
            // Default to pending if an invalid flag is provided
            orders = restaurantService.findPendingOrders(restaurantName);
            model.addAttribute("errorMessage", "Invalid flag parameter. Showing pending orders.");
            model.addAttribute("flag", 0);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("restaurantName", restaurantName);
        return "Restaurant/rest_orders";
    }


    @PostMapping("/restaurant/{restaurantName}")
    public String completeOrder(@RequestParam("orderId") int orderId,
                                @PathVariable String restaurantName,
                                RedirectAttributes redirectAttributes) {
        try {
            restaurantService.markOrderAsCompleted(orderId);
            redirectAttributes.addFlashAttribute("successMessage", "Order marked as completed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error completing order: " + e.getMessage());
            e.printStackTrace();
        } 
        return "redirect:/restaurant/"+restaurantName;
    }
    
   

	 @GetMapping("/restro-dashboard/{username}")
	    public String showDashboard(@PathVariable String username,Model model) {
		    System.out.println(username);
		    Optional<Restaurant> restro =restaurantService.findRByEmail(username);
		    if (restro.isPresent()) {
            model.addAttribute("restaurantName", restro.get().getrName()); // Pass restaurant name to the dashboard
        }
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
