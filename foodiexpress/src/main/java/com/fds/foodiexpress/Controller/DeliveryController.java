package com.fds.foodiexpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fds.foodiexpress.Service.DeliveryServiceDAO;
import com.fds.foodiexpress.entity.Delivery;

@Controller
public class DeliveryController {
    private final DeliveryServiceDAO dsdao;

    public DeliveryController(DeliveryServiceDAO dsdao) {
        this.dsdao = dsdao;
    }

    @GetMapping("/delivery-dashboard")
    public String showDashboard(Model theModel) {
    	int agentId = getLoggedInAgentId();
        
        if (agentId == 0) {
            throw new RuntimeException("User not found.");
        }

        Delivery delivery = dsdao.findById(agentId);
        theModel.addAttribute("user", delivery);
        
        return "Delivery/index"; 
    }

//    @GetMapping("/index")
//    public String showIndex() {
//        return "Delivery/index"; // Must match index.html inside /templates/Delivery/
//    }
    @GetMapping("/index")
    public String showIndex(Model theModel) {
        int agentId = getLoggedInAgentId();
        
        if (agentId == 0) {
            throw new RuntimeException("User not found.");
        }

        Delivery delivery = dsdao.findById(agentId);
        theModel.addAttribute("user", delivery);
        
        return "Delivery/index"; 
    }


    @GetMapping("/details")
    public String showActiveOrders() {
        return "Delivery/details"; // Must match details.html inside /templates/Delivery/
    }

    @GetMapping("/orders")
    public String showAvailableOrders() {
        return "Delivery/orders"; // Must match orders.html inside /templates/Delivery/
    }

    @GetMapping("/performance")
    public String showPerformance() {
        return "Delivery/performance"; // Must match performance.html inside /templates/Delivery/
    }

    @GetMapping("/profile")
    public String showProfile(@RequestParam(value = "agentid", required = false) Integer agentId, Model theModel) {
        if (agentId == null || agentId == 0) {
            agentId = getLoggedInAgentId(); // Get logged-in user ID if missing
        }

        if (agentId == 0) {
            throw new RuntimeException("Agent ID is required to view the profile.");
        }

        Delivery theDelivery = dsdao.findById(agentId);
        if (theDelivery == null) {
            throw new RuntimeException("No profile found for Agent ID " + agentId);
        }

        theModel.addAttribute("user", theDelivery);
        theModel.addAttribute("agentid", agentId); // Ensure agentid is set in the model

        return "Delivery/profile";
    }





    @GetMapping("/update-profile")
    public String showUpdateProfile(@RequestParam("agentid") int theId, Model theModel) {
        // Fetch user details based on agentId
        Delivery theDelivery = dsdao.findById(theId);

        // Handle case where user is not found
        if (theDelivery == null) {
            throw new RuntimeException("Agent ID " + theId + " not found.");
        }

        // Add user details to model
        theModel.addAttribute("delivery", theDelivery);

        return "Delivery/update-profile"; // Maps to update-profile.html
    }

    @PostMapping("/save-profile")
    public String save(@ModelAttribute("delivery") Delivery theDelivery) {
        if (theDelivery.getAgentid() == 0) {
            throw new RuntimeException("Agent ID is missing when saving profile.");
        }

        dsdao.save(theDelivery);
        return "redirect:/profile?agentid=" + theDelivery.getAgentid();
    }


    private int getLoggedInAgentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication.getName() == null) {
            System.out.println("No authenticated user found!");
            return 0;
        }

        String loggedInUserEmail = authentication.getName();
        Delivery theDelivery = dsdao.findByEmail(loggedInUserEmail);

        if (theDelivery == null) {
            System.out.println("No user found for email: " + loggedInUserEmail);
            return 0;
        }

        return theDelivery.getAgentid();
    }


}
