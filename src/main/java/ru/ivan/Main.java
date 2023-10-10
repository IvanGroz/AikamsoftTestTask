package ru.ivan;

import com.google.gson.Gson;
import ru.ivan.data.converter.CriterionConverter;
import ru.ivan.data.database.Database;
import ru.ivan.data.repository.CustomerRepositoryImpl;
import ru.ivan.data.repository.CriterionRepositoryImpl;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CriterionRepository;
import ru.ivan.domain.usecase.SearchCustomerUseCase;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Main {
  private static final String SEARCH_COMMAND_NAME = "search";
  private static final String STAT_COMMAND_NAME = "stat";
  private static final SearchCustomerUseCase searchCustomerUseCase =
          new SearchCustomerUseCase(new CriterionRepositoryImpl(
          new Gson(),
          new CriterionConverter()
  ), new CustomerRepositoryImpl());
  private static final Database database = new Database("postgres",
                                                        "1824",
                                                        false,
                                                        "jdbc:postgresql://localhost:5432/purchases"
  );

  public static void main(String @NotNull [] args) {


    String jsonInput;
    try {//считываем режим работы
      jsonInput = args[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Не указан в аргументах программы путь к файлу JSON!");
    }
    jsonInput = "src/main/resources/json.json";
    String jsonInput2 = "src/main/resources/json2.json";

    switch (args[0]) {
      case SEARCH_COMMAND_NAME:

        //getCriteria(jsonInput);
        try {
          database.connect();
          searchCustomerUseCase.invoke(jsonInput, database);
        } catch (FileNotFoundException f)
        {
          f.printStackTrace();
        }
        catch (SQLException e) {
          throw new RuntimeException(e);

        }

        break;
      case STAT_COMMAND_NAME:
        /*
        *
        try {
          JsonProcessing json = new JsonProcessing(jsonInput2);
          Statistics statistics =  json.getReadResults(Statistics.class);
          System.out.println(statistics);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }

        * */
        break;
      default:


        break;
    }

  }

  private static void getCriteria(String jsonInput) {
    CriterionRepository repository = new CriterionRepositoryImpl(new Gson(),
                                                                 new CriterionConverter()
    );
    try {
      List<Criterion> all = repository.getAll(jsonInput);
      System.out.println(all);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}