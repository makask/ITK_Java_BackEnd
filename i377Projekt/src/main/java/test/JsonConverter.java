package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Person;
import models.Post;

import java.util.Arrays;

public class JsonConverter {

    public static void main(String[] args) throws Exception {

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Convert Json");
        post.setHidden(false);
        post.setTags(Arrays.asList("java", "json"));

        String json = new ObjectMapper()
                .writeValueAsString(Arrays.asList(post,post));

        String input = Util.readFileFromClasspath("people.json");

        Person person = new ObjectMapper().readValue(input, Person.class);

        System.out.println(person);
    }


}
