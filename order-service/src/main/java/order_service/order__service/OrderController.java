package order_service.order__service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
	
	@GetMapping("/hello-order")
	public String hello()
	{
		return "hello order";
	}

}
