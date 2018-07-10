package com.MyApp.MyApp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebAppController {
	
	@RequestMapping("/")
	public String Trades(Model model) 
	{
		return "index";
	}
	
}
