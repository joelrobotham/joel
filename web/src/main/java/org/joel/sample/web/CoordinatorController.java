package org.joel.sample.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CoordinatorController {

	@RequestMapping
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //response.getWriter().print("<html><h1>" + value + "</h1></html>");
        //response.getWriter().close();
        //ModelMap model = new ModelMap();
        //model.addAttribute("value", request.getParameter("value")); 
        //return model;
		return "index";
    }

}
