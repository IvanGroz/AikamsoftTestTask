package ru.ivan.presentation;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ErrorReportPrinter {
  private final Gson gson;
  private static class ErrorReport{
    private final String type = "error";
    private final String message;
    private ErrorReport(String message) {this.message = message;}

    public String getMessage() {
      return message;
    }
  }
  public ErrorReportPrinter(Gson gson) {this.gson = gson;}
  public void print(String fileName,Exception e) throws IOException {
    try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName,StandardCharsets.UTF_8 ))) {
      ErrorReport errorReport = new ErrorReport(e.getMessage());
      gson.toJson(errorReport, ErrorReport.class, jsonWriter);

    }
  }
}
