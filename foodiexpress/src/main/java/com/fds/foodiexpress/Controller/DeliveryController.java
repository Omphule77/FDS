package com.fds.foodiexpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fds.foodiexpress.Service.DeliveryServiceDAO;
import com.fds.foodiexpress.Service.OrderService;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Orders;

import java.util.List;

@Controller
public class DeliveryController {
    private final DeliveryServiceDAO dsdao;
    private final OrderService orderService;

    public DeliveryController(DeliveryServiceDAO dsdao, OrderService orderService) {
        this.dsdao = dsdao;
        this.orderService = orderService;
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
    public String showOrders(Model theModel) {
    	List<Orders> activeOrders = orderService.getOrdersByFlag("2");

        if (activeOrders == null || activeOrders.isEmpty()) {
            System.out.println("No active orders found."); // Debug log
        } else {
            System.out.println("Active Orders Retrieved: " + activeOrders); // Debug log
        }

        theModel.addAttribute("orders", activeOrders);
        return "Delivery/details";
    }

    @GetMapping("/orders")
    public String showAvailableOrders(Model theModel) {
        List<Orders> pendingOrders = orderService.getOrdersByFlag("1");
        theModel.addAttribute("orders", pendingOrders);
        return "Delivery/orders";
    }

    @PostMapping("/delivery/accept-order")
    public String acceptOrder(@RequestParam("orderId") int orderId) {
        orderService.updateOrderStatus(orderId, "2");
        System.out.println("Order #" + orderId + " has been accepted."); // Debug log
        return "redirect:/orders"; // User stays on available orders page
    }

    @PostMapping("/delivery/reject-order")
    public String rejectOrder(@RequestParam("orderId") int orderId) {
        orderService.updateOrderStatus(orderId, "3");
        System.out.println("Order #" + orderId + " has been rejected."); // Debug log
        return "redirect:/orders";
    }

    @PostMapping("/delivery/complete-order")
    public String completeOrder(@RequestParam("orderId") int orderId) {
        orderService.updateOrderStatus(orderId, "4");
        System.out.println("Order #" + orderId + " has been completed."); // Debug log
        return "redirect:/details";
    }

    @GetMapping("/performance")
    public String showPerformance() {
        return "Delivery/performance"; 
    }

    @GetMapping("/profile")
    public String showProfile(@RequestParam(value = "agentid", required = false) Integer agentId, Model theModel) {
        if (agentId == null || agentId == 0) {
            agentId = getLoggedInAgentId();
        }

        if (agentId == 0) {
            throw new RuntimeException("Agent ID is required to view the profile.");
        }

        Delivery theDelivery = dsdao.findById(agentId);
        if (theDelivery == null) {
            throw new RuntimeException("No profile found for Agent ID " + agentId);
        }

        theModel.addAttribute("user", theDelivery);
        theModel.addAttribute("agentid", agentId); 

        return "Delivery/profile";
    }

    @GetMapping("/update-profile")
    public String showUpdateProfile(@RequestParam("agentid") int theId, Model theModel) {
        Delivery theDelivery = dsdao.findById(theId);

        if (theDelivery == null) {
            throw new RuntimeException("Agent ID " + theId + " not found.");
        }

        theModel.addAttribute("delivery", theDelivery);
        return "Delivery/update-profile"; 
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
