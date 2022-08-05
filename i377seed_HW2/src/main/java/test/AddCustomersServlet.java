package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CustomerDAO;
import models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * https://www.youtube.com/watch?v=z9bJRrS2xr8&feature=youtu.be
 */

@WebServlet("/customers/form")
public class AddCustomersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CustomerDAO dao = new CustomerDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().print("Test123!");
    }


    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        Customer customer = new Customer();


        //String name = Util.asString(req.getInputStream());
        String name = req.getParameter("name");

        //customer.setFirstName(name);

        //customersList.add(customer);

        //System.out.println(customer.getFirstName());
        dao.addCustomer(customer);
        res.setContentType("application/json");
        new ObjectMapper().writeValue(res.getOutputStream(), customer);

    }





}