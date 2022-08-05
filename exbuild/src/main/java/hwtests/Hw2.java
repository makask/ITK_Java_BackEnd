package hwtests;

import hwtests.util.PenaltyOnTestFailure;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


public class Hw2 extends AbstractHw {

    private final String BASE_URL = "http://mkalmo.xyz/i377/examples/hw2/nowhere";

    @Test
    @PenaltyOnTestFailure(3)
    public void insertsFromForm() {
        postForm("customers/form", getForm("name", "Jack"));
        postForm("customers/form", getForm("name", "Jill"));

        List<Customer> customers = getList("api/customers");

        assertThat(customers.size(), greaterThanOrEqualTo(2));
        assertThat(getFirstNames(customers), hasItem("Jack"));
        assertThat(getFirstNames(customers), hasItem("John"));
    }

    @Test
    @PenaltyOnTestFailure(4)
    public void insertFromJson() {
        delete("api/customers");

        postJson("api/customers", getCustomer("Jack", "Smith", "C1"));
        postJson("api/customers", getCustomer("Jane", "Smith", "C2"));

        List<Customer> customers = getList("api/customers");

        assertThat(customers.size(), is(3));
        assertThat(customers.get(0).getFirstName(), is("Jane"));
        assertThat(customers.get(1).getKood(), is("C2"));
    }

    private Form getForm(String name, String value) {
        Form form = new Form();
        form.param(name, value);
        return form;
    }

    private void postForm(String path, Form form) {
        getTarget()
            .path(path)
            .request()
            .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
    }

    private List<String> getFirstNames(List<Customer> customers) {
        return customers.stream()
                .map(n -> n.getFirstName())
                .collect(Collectors.toList());
    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

}
