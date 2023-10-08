import dataclass.json.Criteria;
import dataclass.json.Statistics;
import jsonprocessing.JsonProcessing;

import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) {


//    Consumer con = new Consumer(1,"Vasya","Ebalov");
//    Consumer con2 = new Consumer(2,"Vasya2","Ebalov2");
    String jsonInput;
    try {
      jsonInput = args[1];
    }
    catch (ArrayIndexOutOfBoundsException e){
      System.out.println("Не указан в аргументах программы путь к файлу JSON!");
    }
    jsonInput = "src/main/resources/json.json";
    String jsonInput2 = "src/main/resources/json2.json";
    switch (args[0]) {
      case "search":
        try {
          JsonProcessing json = new JsonProcessing(jsonInput);
          Criteria criteria = json.getReadResults(Criteria.class);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }

        break;
      case "stat":
        try {
          JsonProcessing json = new JsonProcessing(jsonInput2);
          Statistics statistics =  json.getReadResults(Statistics.class);
          System.out.println(statistics);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        break;
      default: {

      }
      break;
    }

  }
}