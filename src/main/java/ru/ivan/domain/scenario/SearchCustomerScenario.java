package ru.ivan.domain.scenario;

import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.usecase.GetCriteriaUseCase;
import ru.ivan.domain.usecase.GetCustomersUseCase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SearchCustomerScenario {
  private final GetCriteriaUseCase getCriteriaUseCase;
  private final GetCustomersUseCase getCustomersUseCase;

  public SearchCustomerScenario(GetCriteriaUseCase getCriteriaUseCase,
                                GetCustomersUseCase getCustomersUseCase) {
    this.getCriteriaUseCase = getCriteriaUseCase;
    this.getCustomersUseCase = getCustomersUseCase;
  }


  public Map<Criterion, List<Customer>> invoke(String filepath) throws IOException, SQLException {
    List<Criterion> criteria = getCriteriaUseCase.invoke(filepath);
    Map<Criterion, List<Customer>> results = getCustomersUseCase.invoke(criteria);
    return results;

  }


}