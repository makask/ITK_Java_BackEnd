package test;

import models.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customers/form")
public class AddCustomersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static List<Customer> customersList = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().print("Test123!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        Customer customer = new Customer();

        String name = req.getParameter("name");

        customer.setFirstName(name);

        customersList.add(customer);

        System.out.println(customer.getFirstName());

    }
}
