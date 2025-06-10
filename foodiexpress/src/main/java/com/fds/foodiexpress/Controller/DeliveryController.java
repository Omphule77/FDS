package com.fds.foodiexpress.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fds.foodiexpress.Service.DeliveryServiceDAO;
import com.fds.foodiexpress.Service.OrderService;
import com.fds.foodiexpress.entity.Delivery;
import com.fds.foodiexpress.entity.Orders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (delivery == null) {
            throw new RuntimeException("Delivery agent not found.");
        }

        String agentEmail = delivery.getEmail();

        List<Orders> activeOrders = orderService.getOrdersByFlagAndDEmail("2", agentEmail);
        int activeCount = activeOrders.size();

        List<Orders> availableOrders = orderService.getAvailableOrdersExcludingAgent(List.of("1", "3"), agentEmail);
        int availableCount = availableOrders.size();

        List<Orders> completedOrders = orderService.getOrdersByFlagAndDEmail("4", agentEmail);
        int completedCount = completedOrders.size();

        List<Orders> rejectedOrders = orderService.getOrdersByFlagAndDEmail("3", agentEmail);
        int totalRejectedDeliveries = rejectedOrders.size();

        int totalTrackedDeliveries = completedCount + totalRejectedDeliveries;
        int successRate = (totalTrackedDeliveries > 0) ? (completedCount * 100 / totalTrackedDeliveries) : 0;

        String successMessage =null;

        theModel.addAttribute("user", delivery);
        theModel.addAttribute("activeCount", activeCount);
        theModel.addAttribute("availableCount", availableCount);
        theModel.addAttribute("completedCount", completedCount);
        theModel.addAttribute("successRate", successRate);
        theModel.addAttribute("successMessage", successMessage);

        return "Delivery/index";
    }

    @GetMapping("/index")
    public String showIndex(Model theModel) {
        return showDashboard(theModel);
    }

    @GetMapping("/details")
    public String showActiveOrders(Model theModel) {
        int agentId = getLoggedInAgentId();
        if (agentId == 0) {
            throw new RuntimeException("User not found.");
        }

        Delivery delivery = dsdao.findById(agentId);
        List<Orders> activeOrders = orderService.getOrdersByFlagAndDEmail("2", delivery.getEmail());

        theModel.addAttribute("orders", activeOrders);
        return "Delivery/details";
    }

    @GetMapping("/orders")
    public String showAvailableOrders(Model theModel) {
        int agentId = getLoggedInAgentId();
        if (agentId == 0) {
            throw new RuntimeException("User not found.");
        }

        Delivery delivery = dsdao.findById(agentId);
        String agentEmail = delivery.getEmail();
        List<Orders> availableOrders = orderService.getAvailableOrdersExcludingAgent(List.of("1", "3"), agentEmail);
        theModel.addAttribute("orders", availableOrders);

        return "Delivery/orders";
    }

    @PostMapping("/delivery/accept-order")
    public String acceptOrder(@RequestParam("orderId") int orderId) {
        int agentId = getLoggedInAgentId();
        Delivery delivery = dsdao.findById(agentId);
        orderService.updateOrderDetails(orderId, "2", delivery.getEmail());

        return "redirect:/orders";
    }

    @PostMapping("/delivery/reject-order")
    public String rejectOrder(@RequestParam("orderId") int orderId) {
        int agentId = getLoggedInAgentId();
        Delivery delivery = dsdao.findById(agentId);
        orderService.updateOrderDetails(orderId, "3", delivery.getEmail());

        return "redirect:/orders";
    }
    
    @GetMapping("/delivery/reciveOrder")
    public String receiveOrder(@RequestParam("orderId") int orderId, RedirectAttributes redirectAttributes) {
        int agentId = getLoggedInAgentId();
        Delivery delivery = dsdao.findById(agentId);
        
        orderService.updateOrderDetails(orderId, "2", delivery.getEmail());
        orderService.updateOrderTFlag(orderId, "2");

        // Pass success message for the specific order
        Map<Integer, String> successMessageMap = new HashMap<>();
        successMessageMap.put(orderId, "✅ Order #" + orderId + " received successfully.");

        redirectAttributes.addFlashAttribute("successMessageMap", successMessageMap);

        return "redirect:/details";
    }



    @PostMapping("/delivery/complete-order")
    public String completeOrder(@RequestParam("orderId") int orderId, 
                                @RequestParam("otp") String otp, 
                                RedirectAttributes redirectAttributes) {
        Orders order = orderService.findOrderById(orderId);

        if (order == null) {
            Map<Integer, String> errorMessageMap = new HashMap<>();
            errorMessageMap.put(orderId, "Order not found.");
            redirectAttributes.addFlashAttribute("errorMessageMap", errorMessageMap);
            return "redirect:/details";
        }

        if (!order.getOtp().equals(otp)) {
            Map<Integer, String> errorMessageMap = new HashMap<>();
            errorMessageMap.put(orderId, "❌ Invalid OTP! Please try again.");
            redirectAttributes.addFlashAttribute("errorMessageMap", errorMessageMap);
            return "redirect:/details";
        }

        int agentId = getLoggedInAgentId();
        Delivery delivery = dsdao.findById(agentId);

        orderService.updateOrderDetails(orderId, "4", delivery.getEmail());
        orderService.updateOrderTFlag(orderId, "3");

        return "redirect:/details";
    }


    @GetMapping("/performance")
    public String showPerformance(Model model) {
        int agentId = getLoggedInAgentId();
        Delivery delivery = dsdao.findById(agentId);
        String agentEmail = delivery.getEmail();

        List<Orders> completedOrders = orderService.getOrdersByFlagAndDEmail("4", agentEmail);
        int totalCompletedDeliveries = completedOrders.size();

        List<Orders> rejectedOrders = orderService.getOrdersByFlagAndDEmail("3", agentEmail);
        int totalRejectedDeliveries = rejectedOrders.size();

        int totalTrackedDeliveries = totalCompletedDeliveries + totalRejectedDeliveries;
        int successRate = (totalTrackedDeliveries > 0) ? (totalCompletedDeliveries * 100 / totalTrackedDeliveries) : 0;

        String successMessage;
        if (successRate > 90) {
            successMessage = "You're an exceptional performer! Customers love your service.";
        } else if (successRate > 80) {
            successMessage = "You're doing great! Keep up the consistency.";
        } else if (successRate > 60) {
            successMessage = "Your performance is decent, but there's room for improvement.";
        } else if (successRate > 50) {
            successMessage = "Focus on accepting and completing more deliveries to improve your stats.";
        } else {
            successMessage = "Try to minimize rejected orders to boost your performance.";
        }

        model.addAttribute("totalDeliveries", totalCompletedDeliveries);
        model.addAttribute("successRate", successRate);
        model.addAttribute("successMessage", successMessage);

        return "Delivery/performance";
    }

    @GetMapping("/profile")
    public String showProfile(@RequestParam(value = "agentid", required = false) Integer agentId, Model theModel) {
        if (agentId == null || agentId == 0) {
            agentId = getLoggedInAgentId();
        }

        Delivery theDelivery = dsdao.findById(agentId);
        theModel.addAttribute("user", theDelivery);
        return "Delivery/profile";
    }

    @GetMapping("/update-profile")
    public String showUpdateProfile(@RequestParam("agentid") int theId, Model theModel) {
        Delivery theDelivery = dsdao.findById(theId);
        theModel.addAttribute("delivery", theDelivery);
        return "Delivery/update-profile"; 
    }

    @PostMapping("/save-profile")
    public String saveProfile(@ModelAttribute("delivery") Delivery theDelivery) {
        dsdao.save(theDelivery);
        return "redirect:/profile?agentid=" + theDelivery.getAgentid();
    }

    private int getLoggedInAgentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = (authentication != null) ? authentication.getName() : null;
        Delivery theDelivery = dsdao.findByEmail(loggedInUserEmail);
        return (theDelivery != null) ? theDelivery.getAgentid() : 0;
    }
}
