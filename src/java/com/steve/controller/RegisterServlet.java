/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.controller;

import com.steve.model.User;
import com.steve.service.RegisterService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author USER
 */
public class RegisterServlet extends HttpServlet{
    
        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         String firstName = request.getParameter("firstName");
         String middleName = request.getParameter("middleName");
         String lastName = request.getParameter("lastName");
         String email = request.getParameter("email");
         String userId = request.getParameter("userId");
         String password = request.getParameter("password");
         User user = new User(firstName,middleName,lastName, email,userId, password);

         try { 
             RegisterService registerService = new RegisterService();
             boolean result = registerService.register(user);      
             out.println("<html>");
             out.println("<head>");      
             out.println("<title>Registration Successful</title>");    
             out.println("</head>");
             out.println("<body>");
             out.println("<center>");
             if(result){
                 out.println("<h1>Thanks for Registering with us :</h1>");
                 out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");
             }else{
                 out.println("<h1>Registration Failed</h1>");
                 out.println("To try again<a href=register.jsp>Click here</a>");
             }
             out.println("</center>");
             out.println("</body>");
             out.println("</html>");
         } finally {       
             out.close();
         }
    }
 
}
