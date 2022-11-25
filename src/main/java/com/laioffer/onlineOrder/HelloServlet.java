package com.laioffer.onlineOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.onlineOrder.entity.Customer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义返回type -> html页面
       //response.setContentType("text/html");
        //2。后端传前端
        response.setContentType("application/json");
        Customer c = new Customer();


        c.setEmail("rick.sun@laioffer.com");
        c.setPassword("123");
        c.setFirstName("rick");
        c.setLastName("sun");
        c.setEnable(true);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(c));

//        JSONObject customer = new JSONObject();
//        customer.put("email", c.getEmail());
//        customer.put("first_name", "rick");
//        customer.put("last_name", "sun");
//        customer.put("age", 50);
//        response.getWriter().print(customer);



//        // Hello
        //1。 动态传输
//        String username = request.getParameter("username");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Hello " + username + "</h1>");
//        out.println("</body></html>");
    }
    //3。 前端-> 后端
    // 读取json类别的request
    //getreader
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read customer information from request body
        //把json内容转化成string
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");

        // Print customer information to IDE console
        //输出：输出到stdout，标准输出
        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is: " + age);
        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }


    public void destroy() {
    }
}