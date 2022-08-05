package dao;

import model.Person;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDao {

    public void insertPerson(String name) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.getTransaction().commit();

            Person person = new Person();
            person.setName(name);

            em.persist(person);

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    public List<Person> findAllPersons() {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select distinct p from Person p left join fetch p.phones",
                    Person.class);



            return query.getResultList();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    public Person findPersonByName(String name) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p left join fetch p.phones where name =:name",Person.class);

            query.setParameter("name", name);

            return query.getSingleResult();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    public void savePerson(Person person) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.merge(person);

            em.getTransaction().commit();


        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
}
