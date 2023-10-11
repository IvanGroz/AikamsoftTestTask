package ru.ivan.domain.entity;


import java.util.Map;

public class SearchResults {
  public final String type = "search";
  public ResultsList[] results;

  public SearchResults(ResultsList[] results) {
    this.results = results;
  }

  public static class ResultsList{
    public ResultsList(Criterion criteria, Customer[] results) {
      this.criteria = criteria;
      this.results = results;
    }

    public Criterion criteria;
    public Customer[] results;
  }
}
