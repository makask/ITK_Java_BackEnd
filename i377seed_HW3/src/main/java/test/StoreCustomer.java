package test;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class StoreCustomer {

    private static List<Customer> customers = new ArrayList<>();

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static List<Customer> getCustomer(){
        return customers;
    }

    public static void deleteCustomer(){
        customers.clear();
    }

}
