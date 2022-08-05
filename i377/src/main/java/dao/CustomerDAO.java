package dao;

import models.Customer;
import org.springframework.stereotype.Repository;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

// Used sources: https://www.youtube.com/watch?v=cdHi1ZRy6tE
//               https://www.youtube.com/watch?v=7dfN4ThXYEM
//               https://www.youtube.com/watch?v=KtFaBvteN24

@Repository
public class CustomerDAO {

    public static String URL = "jdbc:hsqldb:mem:db1";

    public static void addCustomer(Customer customer){

        EntityManager em = null;

        try{
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.persist(customer);

            em.getTransaction().commit();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    public void saveCustomer(Customer customer) {
        EntityManager em = null;

        try{
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.merge(customer);

            em.getTransaction().commit();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }


    //get a list of all customers in the DB
    public static List<Customer> getAllCustomers(){

        EntityManager em = null;

        try{
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Customer> query = em.createQuery(
                    "select distinct p from Customer p left join fetch p.phones", Customer.class);

            return query.getResultList();

        } finally {
            JpaUtil.closeQuietly(em);
        }

    }

    //delete all customers from DB
    public static void deleteCustomers(){

        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery("TRUNCATE SCHEMA public AND COMMIT").executeUpdate();
            em.getTransaction().commit();
        } finally {
            JpaUtil.closeQuietly(em);
        }


    }

    public static Customer getCustomerById(Long id) {

        EntityManager em = null;

        try{
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Customer> query = em.createQuery("" +
                    "select p from Customer p left join fetch p.phones where id = :id", Customer.class);

            query.setParameter("id", id);

            return query.getSingleResult();

        } finally {
            JpaUtil.closeQuietly(em);
        }

    }

    public static void deleteCustomerById(Long id) {

        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Customer p where p.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();

            em.getTransaction().commit();

        } finally {
            JpaUtil.closeQuietly(em);
        }

    }

    public List<String> getCustomerTypes() {
        List<String> customerTypes = new LinkedList<>();
        customerTypes.add("customer_type.private");
        customerTypes.add("customer_type.corporate");

        return customerTypes;

    }

    public List<String> getPhoneTypes() {
        List<String> phoneTypes = new LinkedList<>();
        phoneTypes.add("phone_type.fixed");
        phoneTypes.add("phone_type.mobile");

        return phoneTypes;

    }


}
