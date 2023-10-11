package ru.ivan.data.repository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.SearchResults;
import ru.ivan.domain.entity.SearchResults.ResultsList;
import ru.ivan.domain.repository.SearchResultsRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultsRepositoryImpl implements SearchResultsRepository {
  private final Gson gson;

  public SearchResultsRepositoryImpl(Gson gson) {
    this.gson = gson;
  }

  @Override
  public void toJsonSearchResults(String fileName, SearchResults searchResults) throws IOException {
    try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(fileName))) {
      gson.toJson(searchResults, SearchResults.class, jsonWriter);
    }
  }

  @Override
  public SearchResults getSearchResults(Map<Criterion, List<Customer>> results) {
    ArrayList<ResultsList> resultsLists = new ArrayList<>();
    for (Map.Entry<Criterion, List<Customer>> entry : results.entrySet()) {
      Criterion key = entry.getKey();
      List<Customer> value = entry.getValue();
      try {resultsLists.add(new ResultsList(key, value.toArray(Customer[]::new)));} catch (
              ClassCastException e) {e.printStackTrace();}
    }


    return new SearchResults(resultsLists.toArray(ResultsList[]::new));
  }


}
