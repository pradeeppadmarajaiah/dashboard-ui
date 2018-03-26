package com.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
class WebSiteController {
	
	@Autowired
	DashboardController dashboardController;

    // DispatcherServlet calls action and passes model object to action
    // The same model object is also passed to the view by the DispatcherServlet
    @RequestMapping("/")
    public String welcome(Model model) {

        return "welcome" ; // returning the view file name to DispatcherServlet

    }
}