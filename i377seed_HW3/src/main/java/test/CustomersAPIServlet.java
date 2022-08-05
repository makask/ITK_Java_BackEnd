package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static test.StoreCustomer.addCustomer;
import static test.StoreCustomer.deleteCustomer;

@WebServlet("/api/customers")
public class CustomersAPIServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = Util.asString(req.getInputStream());

        Customer customer = new ObjectMapper().readValue(input,Customer.class);

        addCustomer(customer);

        System.out.println(customer);

        resp.setContentType("application/json");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        new ObjectMapper().writeValue(resp.getOutputStream(),StoreCustomer.getCustomer());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        deleteCustomer();
    }
}
