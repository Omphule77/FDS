package com.fds.foodiexpress.Controller;import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fds.foodiexpress.Service.DeliveryServiceDAO;
import com.fds.foodiexpress.Service.DeliveryServiceDaoImpl;
import com.fds.foodiexpress.entity.Delivery;


@Controller
public class DeliveryController {
	private DeliveryServiceDAO dsdao;
	
	public DeliveryController(DeliveryServiceDAO dsdao) {
		this.dsdao=dsdao;
	}
	

   @GetMapping("/delivery-dashboard")
   public String showDashboard() {
	   return "Delivery/index";
   }
    
    @GetMapping("/index")
    public String showIndex() {
        return "Delivery/index"; // Must match index.html inside /templates/Delivery/
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
    public String showProfile() {
        return "Delivery/profile"; // Must match profile.html inside /templates/Delivery/
        
    }
  
}
