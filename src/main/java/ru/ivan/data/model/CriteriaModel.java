package ru.ivan.data.model;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


public class CriteriaModel {
  public ArrayList<CriterionModel> criterias; //Множество критерий

  public static class CriterionModel { //Класс являющимся одним критерием поиска
    public String lastName;
    public String productName;
    public Integer minTimes;
    public Integer minExpenses;
    public Integer maxExpenses;
    public Integer badCustomers;
  }
}

