package ru.ivan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import ru.ivan.data.converter.CriterionConverter;
import ru.ivan.data.converter.StatisticsDateConverter;
import ru.ivan.data.database.Database;
import ru.ivan.data.datasource.CustomerDataSourceImpl;
import ru.ivan.data.datasource.StatisticsDataSourceImpl;
import ru.ivan.data.repository.CriterionRepositoryImpl;
import ru.ivan.data.repository.CustomerRepositoryImpl;
import ru.ivan.data.repository.StatisticsDateRepositoryImpl;
import ru.ivan.data.repository.StatisticsRepositoryImpl;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.scenario.SearchCustomerScenario;
import ru.ivan.domain.scenario.StatisticsCustomerScenario;
import ru.ivan.domain.usecase.GetCriteriaUseCase;
import ru.ivan.domain.usecase.GetCustomersUseCase;
import ru.ivan.domain.usecase.GetStatisticsDateUseCase;
import ru.ivan.domain.usecase.GetStatisticsUseCase;
import ru.ivan.presentation.SearchReportPrinter;
import ru.ivan.presentation.StatisticsReportPrinter;

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
  private static final StatisticsCustomerScenario STATISTICS_CUSTOMER_SCENARIO =
          new StatisticsCustomerScenario(
          new GetStatisticsDateUseCase(new StatisticsDateRepositoryImpl(new Gson(),
                                                                        new StatisticsDateConverter()
          )),
          new GetStatisticsUseCase(new StatisticsRepositoryImpl(new StatisticsDataSourceImpl(
                  DATABASE)))
  );
  private static final SearchReportPrinter SEARCH_REPORT_PRINTER =
          new SearchReportPrinter(new Gson());

  private static final StatisticsReportPrinter STATISTICS_REPORT_PRINTER =
          new StatisticsReportPrinter(new Gson());

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
        try {
          DATABASE.connect();
          Statistics statistics = STATISTICS_CUSTOMER_SCENARIO.invoke(jsonInput2);
          STATISTICS_REPORT_PRINTER.print(statistics,jsonOutput);
          DATABASE.disconnect();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          throw new RuntimeException(e);

        }
        break;
      default:


        break;
    }

  }


}