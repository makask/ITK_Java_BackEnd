package controller;

import dao.CustomerDAO;
import models.Customer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class AppController {

    @Resource
    private CustomerDAO dao = new CustomerDAO();

    @GetMapping("/customers")
    public List<Customer> list(){
        return dao.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer customer(@PathVariable() Long id){
        return dao.getCustomerById(id);
    }

    @PostMapping("/customers")
    public void save(@RequestBody Customer customer){
        dao.addCustomer(customer);
    }

    @DeleteMapping("/customers")
    public void deleteAll(){
        dao.deleteCustomers();
    }

    @DeleteMapping("customers/{id}")
    public void deleteCustomers(@PathVariable Long id){
        dao.deleteCustomerById(id);
    }
}