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


@WebServlet("/api/customers")
public class CustomersAPIServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CustomerDAO dao = new CustomerDAO();

    //public static List<Customer> customersList = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        String input = Util.asString(req.getInputStream());

        resp.setContentType("appication/json");

        Customer customer = new ObjectMapper().readValue(input, Customer.class);

        dao.addCustomer(customer);

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        resp.setContentType("application/json");

        if(req.getParameter("id") == null){
            new ObjectMapper().writeValue(resp.getOutputStream(),dao.getAllCustomers());
        }else{
            Long id = Long.valueOf(req.getParameter("id"));
            new ObjectMapper().writeValue(resp.getOutputStream(),dao.getCustomerById(id));
        }


    }//doGet

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{

        if (req.getParameter("id")== null){
            CustomerDAO.deleteCustomers();
        } else {
            Long id = Long.valueOf(req.getParameter("id"));
            CustomerDAO.deleteCustomerById(id);
        }

    }

}