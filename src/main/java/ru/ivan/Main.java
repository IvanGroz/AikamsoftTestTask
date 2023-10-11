package ru.ivan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import ru.ivan.data.converter.CriterionConverter;
import ru.ivan.data.database.Database;
import ru.ivan.data.datasource.CustomerDataSourceImpl;
import ru.ivan.data.repository.CriterionRepositoryImpl;
import ru.ivan.data.repository.CustomerRepositoryImpl;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.repository.CriterionRepository;
import ru.ivan.domain.scenario.SearchCustomerScenario;
import ru.ivan.domain.usecase.GetCriteriaUseCase;
import ru.ivan.domain.usecase.GetCustomersUseCase;
import ru.ivan.presentation.SearchReportPrinter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
  private static final String SEARCH_COMMAND_NAME = "search";
  private static final String STAT_COMMAND_NAME = "stat";
  private static final Database DATABASE = new Database("postgres",
                                                        "1824",
                                                        false,
                                                        "jdbc:postgresql://localhost:5432/purchases"
  );
  private static final SearchCustomerScenario SEARCH_CUSTOMER_SCENARIO = new SearchCustomerScenario(
          new GetCriteriaUseCase(new CriterionRepositoryImpl(new GsonBuilder().serializeNulls()
                                                                     .create(),
                                                             new CriterionConverter()
          )),
          new GetCustomersUseCase(new CustomerRepositoryImpl(new CustomerDataSourceImpl(DATABASE)))
  );
  private static final SearchReportPrinter SEARCH_REPORT_PRINTER =
          new SearchReportPrinter(new Gson());

  public static void main(String @NotNull [] args) {


    String jsonInput, jsonOutput;
    try {//считываем режим работы
      jsonInput = args[1];
      jsonOutput = args[2];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Не указан в аргументах программы путь к файлу JSON!");
    }
    jsonInput = "src/main/resources/json.json";
    jsonOutput = "src/main/resources/out.json";
    String jsonInput2 = "src/main/resources/json2.json";

    switch (args[0]) {
      case SEARCH_COMMAND_NAME:

        //getCriteria(jsonInput);
        try {
          DATABASE.connect();
          Map<Criterion, List<Customer>> searchResults = SEARCH_CUSTOMER_SCENARIO.invoke(jsonInput);
          SEARCH_REPORT_PRINTER.print(searchResults, jsonOutput);
          DATABASE.disconnect();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (SQLException e) {
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