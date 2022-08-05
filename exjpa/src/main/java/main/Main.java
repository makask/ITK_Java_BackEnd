package main;

import dao.PersonDao;
import dao.SetupDao;
import model.Address;
import model.Person;
import model.Phone;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } finally {
            JpaUtil.closeFactory();
        }
    }

    private void run() {
        new SetupDao().createSchema();

        PersonDao dao = new PersonDao();

        dao.insertPerson("Jill");
        dao.insertPerson("Jack");

        Person person = dao.findPersonByName("Jill");

        person.setName("Jillian");
        person.setAddress(new Address("Oak street 1"));
        person.getPhones().addAll(Arrays.asList(new Phone("123"),new Phone("456")));


        dao.savePerson(person);

        System.out.println(dao.findAllPersons().size());
        System.out.println(dao.findAllPersons());
    }








}
