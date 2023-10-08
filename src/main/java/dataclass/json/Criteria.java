package dataclass.json;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


public class Criteria {
  public ArrayList<Criterion> criteria; //Множество критерий

  public static class Criterion { //Класс являющимся одним критерием поиска
    public String lastName;
    public String productName;
    public int minTimes;
    public int minExpenses;
    public int maxExpenses;
    public int badCustomers;
  }
}

