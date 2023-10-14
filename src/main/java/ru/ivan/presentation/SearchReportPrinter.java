package ru.ivan.presentation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchReportPrinter {
  private final Gson gson;

  public SearchReportPrinter(Gson gson) {this.gson = gson;}

  private static class Report {
    public final String type = "search";
    public ReportElement[] results;

    public Report(ReportElement[] results) {
      this.results = results;
    }

    public static class ReportElement {
      public Criterion criteria;
      public Customer[] results;

      public ReportElement(Criterion criteria, Customer[] results) {
        this.criteria = criteria;
        this.results = results;
      }
    }
  }

  public void print(Map<Criterion, List<Customer>> results, String fileName) throws IOException {
    Report report = convertSearchResults(results);
    toJsonSearchResults(fileName, report);
  }

  private Report convertSearchResults(Map<Criterion, List<Customer>> results) {
    ArrayList<Report.ReportElement> resultsLists = new ArrayList<>();
    for (Map.Entry<Criterion, List<Customer>> entry : results.entrySet()) {
      Criterion key = entry.getKey();
      List<Customer> value = entry.getValue();
      try {
        resultsLists.add(new Report.ReportElement(key, value.toArray(Customer[]::new)));
      } catch (ClassCastException e) {e.printStackTrace();}
    }


    return new Report(resultsLists.toArray(Report.ReportElement[]::new));
  }

  private void toJsonSearchResults(String fileName, Report report) throws IOException {
    try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
      gson.toJson(report, Report.class, jsonWriter);

    }
  }
}
