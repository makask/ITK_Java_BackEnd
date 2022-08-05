package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private String code;

    public Customer(long id,String firstName,String lastName,String code)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.code = code;
    }

    public Customer(String firstName,String lastName,String code)
    {

    }


}
