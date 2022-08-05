package hwtests;

import hwtests.util.LoggingFilter;
import hwtests.util.Parameter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.List;

public abstract class AbstractHw {

    protected abstract String getBaseUrl();

    private static boolean isDebug = false;

    protected static void setDebug(boolean debug) {
        isDebug = debug;
    }

    private static Client getClient() {
        try {
            return ClientBuilder.newBuilder()
                    .register(new LoggingFilter(isDebug)).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebTarget getTarget() {
        return getClient().target(getBaseUrl());
    }

    protected Customer getCustomer(String firstName, String lastName, String code) {
        return new Customer(null, firstName, lastName, code);
    }

    protected Customer getCustomer(String firstName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        return customer;
    }

    protected List<Customer> getList(String path) {
        return getTarget()
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Customer>>() {});
    }

    protected Customer getOne(String path, Parameter ... parameters) {
        return getOne(path, Customer.class, parameters);
    }

    protected <T> T getOne(String path, Class<T> clazz, Parameter ... parameters) {
        WebTarget target = getTarget().path(path);

        for (Parameter p : parameters) {
            target = target.queryParam(p.getKey(), p.getValue());
        }

        return target
                .request(MediaType.APPLICATION_JSON)
                .get(clazz);
    }

    protected Parameter param(String key, Object value) {
        return new Parameter(key, String.valueOf(value));
    }

    protected void postJson(String path, Customer data) {
        Response response = getTarget()
                .path(path)
                .request()
                .post(Entity.entity(data, MediaType.APPLICATION_JSON));

        ensureSuccessfulResponse(response);
    }

    private void ensureSuccessfulResponse(Response response) {
        int responseCode = response.getStatus();

        if (responseCode < 200 || responseCode >= 300) {

            String message = MessageFormat.format("Response code: {0}. Content: {1}",
                    responseCode, response.readEntity(String.class));

            throw new RuntimeException(message);
        }
    }

    protected void delete(String path, Parameter ... parameters) {
        WebTarget target = getTarget().path(path);

        for (Parameter p : parameters) {
            target = target.queryParam(p.getKey(), p.getValue());
        }

        ensureSuccessfulResponse(target.request().delete());
    }

}


