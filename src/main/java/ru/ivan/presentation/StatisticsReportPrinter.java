package ru.ivan.presentation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StatisticsReportPrinter {
  private final Gson gson;

  public StatisticsReportPrinter(Gson gson) {this.gson = gson;}

  public void print(Statistics report, String fileName) throws IOException {
    toJsonSearchResults(fileName, report);
  }
  private void toJsonSearchResults(String fileName, Statistics report) throws IOException {
    try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName))) {
      gson.toJson(report, Statistics.class, jsonWriter);

    }
  }
}
