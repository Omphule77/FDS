package com.fds.foodiexpress.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fds.foodiexpress.Service.AdminService;
import com.fds.foodiexpress.entity.Admin;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;

@Controller
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model) {
		// Retrieve the map from adminService
	   // SecurityContextHolder.clearContext();
		Map<Customer, String> customerFlagMap = adminService.findAllCustomersWithFlag();
		Map<Delivery, String> deliveryPartnerFlagMap = adminService.findAllDeliveryPartnerWithFlag();
		Map<Restaurant, String> restaurentFlagMap = adminService.findAllRestaurantWithFlag();

		// Add the map as a model attribute
		model.addAttribute("customerFlagMap", customerFlagMap);
		model.addAttribute("deliveryPartnerFlagMap", deliveryPartnerFlagMap);
		model.addAttribute("restaurentFlagMap", restaurentFlagMap);

		return "Admin/adminhome";
	}

	@PostMapping("/admin/toggleFlag")
	public String toggleFlag(@RequestParam String email, @RequestParam String tab,
			RedirectAttributes redirectAttributes) {
		// Use the email as the unique identifier to toggle the flag.
		adminService.toggleCustomerFlag(email);
		redirectAttributes.addFlashAttribute("message", " flag updated successfully!");
		// return "redirect:/admin-dashboard";
		return "redirect:/admin-dashboard?tab=" + tab;
	}

	@GetMapping("/admin-profile")
	public String adminProfile(Model model) {
		String adminEmail = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			adminEmail = authentication.getName(); // Returns the username
		}
		Admin adminDetails = adminService.getAdminDetails(adminEmail);
		model.addAttribute("adminDetails", adminDetails);
		return "Admin/adminProfile";
	}
	
	@PostMapping("/updateAdmin")
	public String updateAdmin(@ModelAttribute("admin") Admin admin){
		adminService.updateAdmin(admin);
		return "redirect:/admin-profile";		
	}
}
