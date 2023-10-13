package ru.ivan.data.repository;

import ru.ivan.data.datasource.CustomerDataSource;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
  private final CustomerDataSource dataSource;

  public CustomerRepositoryImpl(CustomerDataSource dataSource) {this.dataSource = dataSource;}


  @Override
  public Map<Criterion, List<Customer>> getCustomers(List<Criterion> criteria) throws SQLException {

    Map<Criterion, List<Customer>> result = new HashMap<>();

    for (Criterion criterion : criteria) {
      List<Customer> customers = dataSource.search(criterion);
      result.put(criterion,customers);
    }

    return result;
  }

}
