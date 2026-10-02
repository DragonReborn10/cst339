package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.LoginModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private SecurityBusinessService security;

    @Autowired
    private OrdersBusinessServiceInterface service;

    @GetMapping("/")
    public String display(Model model)
    {
        // Display Login Form View
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());

        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
    {
        // Print the form values out
        System.out.println(String.format(
            "Form with Username of %s and Password of %s",
            loginModel.getUsername(),
            loginModel.getPassword()
        ));

    // Check for validation errors
    if (bindingResult.hasErrors())
    {
        model.addAttribute("title", "Login Form");
        return "login";
    }

    service.test();

    security.authenticate(
    loginModel.getUsername(),
    loginModel.getPassword());

        // gets a list of Orders
        model.addAttribute("orders", service.getOrders());

        // Navigate to the Orders View
        return "orders";
    }
}