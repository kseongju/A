package edu.MVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value="/Board")
@Controller
public class BoardController {
	
	@RequestMapping(value="Nboard.do", method=RequestMethod.GET)
	public String Nboard() {
		
		return "Board/Nboard";
	}

	@RequestMapping(value="Nboardwrite.do", method=RequestMethod.GET)
	public String Nboardwrite() {
		
		return "Board/Nboardwrite";
	}
}
