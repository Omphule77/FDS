package com.fds.foodiexpress.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fds.foodiexpress.Service.AdminService;
import com.fds.foodiexpress.entity.Admin;
import com.fds.foodiexpress.entity.Authorities;
import com.fds.foodiexpress.entity.Customer;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Restaurant;
import com.fds.foodiexpress.entity.Users;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AdminController(AdminService adminService , PasswordEncoder passwordEncoder) {
		this.adminService = adminService;
		this.passwordEncoder=passwordEncoder;
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

	@PostMapping("/admintoggleFlag")
	public String toggleFlag(@RequestParam String email, @RequestParam String tab,
			RedirectAttributes redirectAttributes) {
		// Use the email as the unique identifier to toggle the flag.
		adminService.toggleCustomerFlag(email);
		redirectAttributes.addFlashAttribute("message", " flag updated successfully!");
		// return "redirect:/admin-dashboard";
		return "redirect:/admin/admin-dashboard?tab=" + tab;
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
	public String updateAdmin(@ModelAttribute("admin") Admin admin,RedirectAttributes redirectAttributes){
		adminService.updateAdmin(admin);
		redirectAttributes.addFlashAttribute("successMessage","Your Profiles Detail are successfully updated");
		return "redirect:/admin/admin-profile";		
	}
	
	@GetMapping("/admin-operations")
	public String adminOperations(Model model) {
		model.addAttribute("admin", new Admin());
		return "Admin/admin-operations";
	}
	
	@PostMapping("/addAdmin")
	public String addAdmin( @Valid @ModelAttribute Admin admin,BindingResult bindingResult ,RedirectAttributes redirectAttributes,Model model){
		
		if (bindingResult.hasErrors()) {
            // If there are validation errors
            model.addAttribute("admin", admin); 
            return "Admin/admin-operations"; 
        }
		System.out.println(admin);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		Users user = new Users();
		Authorities authority = new Authorities();
		
		user.setUsername(admin.getEmail());
		user.setPassword(admin.getPassword());
		user.setEnabled("1");
		
		authority.setUsername(admin.getEmail());
		authority.setAuthority("ROLE_ADMIN");
		
		adminService.addAdmin(user, authority, admin);
        redirectAttributes.addFlashAttribute("successMessage", "Admin added successfully!");
		return "redirect:/admin/admin-operations";
		
	}
}
