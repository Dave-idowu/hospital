/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.controller;

import com.steve.model.User;
import com.steve.service.LoginService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class LoginServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
     String userId = request.getParameter("userId");   
     String password = request.getParameter("password");
     LoginService loginService = new LoginService();
     boolean result = loginService.authenticateUser(userId, password);
     User user = loginService.getUserByUserId(userId);
     if(result == true){
         request.getSession().setAttribute("user", user);      
         response.sendRedirect("home.jsp");
     }
     else{
         response.sendRedirect("error.jsp");
     }
}
 
}