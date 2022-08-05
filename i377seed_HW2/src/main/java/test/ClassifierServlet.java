package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CustomerDAO;
import models.ClassifierInfo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/api/classifiers")
public class ClassifierServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        ClassifierInfo info = new ClassifierInfo(dao.getCustomerTypes(), dao.getPhoneTypes());
        new ObjectMapper().writeValue(
                resp.getOutputStream(), info);

    }
}