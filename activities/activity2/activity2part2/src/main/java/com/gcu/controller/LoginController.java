package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController
{
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

        // Create a list of Orders
        List<OrderModel> orders = new ArrayList<>();

        orders.add(new OrderModel(0L, "0000000001", "Product 1", 1.00f, 1));
        orders.add(new OrderModel(1L, "0000000002", "Product 2", 2.00f, 2));
        orders.add(new OrderModel(2L, "0000000003", "Product 3", 3.00f, 3));
        orders.add(new OrderModel(3L, "0000000004", "Product 4", 4.00f, 4));
        orders.add(new OrderModel(4L, "0000000005", "Product 5", 5.00f, 5));

        // Add Orders to the Model
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", orders);

        // Navigate to the Orders View
        return "orders";
    }
}