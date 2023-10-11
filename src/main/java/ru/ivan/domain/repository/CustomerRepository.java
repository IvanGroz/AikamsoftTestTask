package ru.ivan.domain.repository;

import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerRepository {
  public Map<Criterion, List<Customer>> getCustomers(List<Criterion> criteria) throws SQLException;
}
