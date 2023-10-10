package ru.ivan.domain.usecase;

import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CustomerRepository;
import ru.ivan.domain.repository.CriterionRepository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchCustomerUseCase {
  private final CriterionRepository criterionRepository;
  private final CustomerRepository customerRepository;

  public SearchCustomerUseCase(CriterionRepository criterionRepository,
                               CustomerRepository customerRepository) {
    this.criterionRepository = criterionRepository;
    this.customerRepository = customerRepository;
  }

  public Map<Criterion, List<Customer>> invoke(String filepath, Database db) throws FileNotFoundException, SQLException {
    List<Criterion> criteria = criterionRepository.getAll(filepath);
    return customerRepository.getCustomerByCriteria(criteria,db);
  }
}
