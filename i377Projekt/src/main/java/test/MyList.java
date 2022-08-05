package test;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class MyList
{
    private static List<Customer> customerList = new ArrayList<>();

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(List<Customer> customerList) {
        MyList.customerList = customerList;
    }
}