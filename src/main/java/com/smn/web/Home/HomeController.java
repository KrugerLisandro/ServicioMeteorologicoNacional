package com.smn.web.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/"})
	public String mostrarHome() {
		return "Home";
	}
}
