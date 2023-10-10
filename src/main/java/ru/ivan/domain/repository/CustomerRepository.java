package ru.ivan.domain.repository;

import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerRepository {
  public Map<Criterion, List<Customer>> getCustomerByCriteria(List<Criterion> criteria, Database db) throws SQLException;
}
