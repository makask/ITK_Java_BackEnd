package models;

import lombok.Data;

import java.util.List;

@Data
public class ClassifierInfo {

    private List<String> phoneTypes;
    private List<String> customerTypes;

    public ClassifierInfo(List customerTypes, List phoneTypes){
        this.customerTypes = customerTypes;
        this.phoneTypes = phoneTypes;
    }

}