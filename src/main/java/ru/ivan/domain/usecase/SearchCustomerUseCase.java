package ru.ivan.domain.usecase;

import com.google.gson.Gson;
import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.SearchResults;
import ru.ivan.domain.repository.CustomerRepository;
import ru.ivan.domain.repository.CriterionRepository;
import ru.ivan.domain.repository.SearchResultsRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchCustomerUseCase {
  private final CriterionRepository criterionRepository;
  private final CustomerRepository customerRepository;
  private final SearchResultsRepository searchResultsRepository;

  public SearchCustomerUseCase(CriterionRepository criterionRepository,
                               CustomerRepository customerRepository,
                               SearchResultsRepository searchResultsRepository) {
    this.criterionRepository = criterionRepository;
    this.customerRepository = customerRepository;
    this.searchResultsRepository = searchResultsRepository;
  }

  public SearchResults invoke(String filepath, String fileName,
                              Database db) throws IOException, SQLException {
    List<Criterion> criteria = criterionRepository.getAll(filepath);
    // TODO: 10.10.2023 вывод объекта на json
    Map<Criterion, List<Customer>> results = customerRepository.getCustomerByCriteria(criteria, db);
    SearchResults searchResults = searchResultsRepository.getSearchResults(results);
    searchResultsRepository.toJsonSearchResults(fileName, searchResults);
    return searchResults;

  }


}