package exservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import exservlet.model.Person;
import exservlet.model.Post;

import java.util.Arrays;

public class JsonConverter {

    public static void main(String[] args) throws Exception {

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Covert Json");
        post.setHidden(false);
        post.setTags(Arrays.asList("java", "json"));

        //Objekt jsoniks
        String json = new ObjectMapper().writeValueAsString(Arrays.asList(post,post));

        String input = Util.readFileFromClasspath("people.json");

        //Json java objektiks
        Person person = new ObjectMapper().readValue(input, Person.class);

        System.out.println(person);

    }


}
