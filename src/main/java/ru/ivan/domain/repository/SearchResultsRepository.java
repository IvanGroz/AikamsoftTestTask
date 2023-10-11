package ru.ivan.domain.repository;

import com.google.gson.Gson;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.SearchResults;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SearchResultsRepository {
  public SearchResults getSearchResults(Map<Criterion, List<Customer>> results);
  public void toJsonSearchResults(String fileName,SearchResults searchResults) throws IOException;
}
