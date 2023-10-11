package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.repository.CustomerRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GetCustomersUseCase {
  private final CustomerRepository customerRepository;

  public GetCustomersUseCase(
          CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

  public Map<Criterion, List<Customer>> invoke(List<Criterion> criteria) throws SQLException {
    return customerRepository.getCustomers(criteria);
  }
}
