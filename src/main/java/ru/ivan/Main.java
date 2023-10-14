package ru.ivan;

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
import ru.ivan.presentation.ErrorReportPrinter;
import ru.ivan.presentation.SearchReportPrinter;
import ru.ivan.presentation.StatisticsReportPrinter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {
  private static final String SEARCH_COMMAND_NAME = "search";
  private static final String STAT_COMMAND_NAME = "stat";

  private static Database DATABASE;

  static {
    final String FILENAME = "db_prop.txt";
    try (BufferedReader bufferedReader =
                 new BufferedReader(
                         new FileReader(FILENAME,
                                        StandardCharsets.UTF_8
                         )
                 );
    ) {

      String user = bufferedReader.readLine();
      String passw = bufferedReader.readLine();
      String url = bufferedReader.readLine();
      DATABASE = new Database(user,
                              passw,
                              false,
                              url
      );
    } catch (FileNotFoundException e) {
      System.out.println(
              "Для подключения к БД небходимо чтобы рядом с jar-файлом лежал '" +FILENAME+"', " +
                      "где:\n1я строка - это имя пользователя\n2я - пароль\n3я - url подключения " +
                      "формата: 'jdbc:postgresql://localhost:5432/db_name' ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static final SearchCustomerScenario SEARCH_CUSTOMER_SCENARIO = new SearchCustomerScenario(
          new GetCriteriaUseCase(new CriterionRepositoryImpl(new GsonBuilder().disableHtmlEscaping()
                                                                     .create(),
                                                             new CriterionConverter()
          )),
          new GetCustomersUseCase(new CustomerRepositoryImpl(new CustomerDataSourceImpl(DATABASE)))
  );
  private static final StatisticsCustomerScenario STATISTICS_CUSTOMER_SCENARIO =
          new StatisticsCustomerScenario(
                  new GetStatisticsDateUseCase(new StatisticsDateRepositoryImpl(new GsonBuilder().disableHtmlEscaping()
                                                                                        .create(),
                                                                                new StatisticsDateConverter()
                  )),
                  new GetStatisticsUseCase(new StatisticsRepositoryImpl(new StatisticsDataSourceImpl(
                          DATABASE)))
          );
  private static final SearchReportPrinter SEARCH_REPORT_PRINTER =
          new SearchReportPrinter(new GsonBuilder().disableHtmlEscaping().create());

  private static final StatisticsReportPrinter STATISTICS_REPORT_PRINTER =
          new StatisticsReportPrinter(new GsonBuilder().disableHtmlEscaping().create());
  private static final ErrorReportPrinter ERROR_REPORT_PRINTER =
          new ErrorReportPrinter(new GsonBuilder().disableHtmlEscaping()
                                         .create());

  public static void main(String @NotNull [] args) {


    String jsonInput, jsonOutput, jsonInput2;
    try {//считываем режим работы
      jsonInput = args[1];
      jsonInput2 = args[1];
      jsonOutput = args[2];
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Не указан в аргументах программы путь к файлу JSON!");
      jsonInput = "src/main/resources/json.json";
      jsonOutput = "src/main/resources/out.json";
      jsonInput2 = "src/main/resources/json2.json";
    }


    switch (args[0]) {
      case SEARCH_COMMAND_NAME:
        try {
          DATABASE.connect();
          Map<Criterion, List<Customer>> searchResults = SEARCH_CUSTOMER_SCENARIO.invoke(jsonInput);
          SEARCH_REPORT_PRINTER.print(searchResults, jsonOutput);
          DATABASE.disconnect();
        } catch (Exception e) {
          try {
            ERROR_REPORT_PRINTER.print(jsonOutput, e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;

      case STAT_COMMAND_NAME:
        try {
          DATABASE.connect();
          Statistics statistics = STATISTICS_CUSTOMER_SCENARIO.invoke(jsonInput2);
          STATISTICS_REPORT_PRINTER.print(statistics, jsonOutput);
          DATABASE.disconnect();
        } catch (Exception e) {
          try {
            ERROR_REPORT_PRINTER.print(jsonOutput, e);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
        break;
      default:


        break;
    }

  }


}