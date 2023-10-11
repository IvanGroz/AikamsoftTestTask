package ru.ivan.data.datasource;

import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public  interface CustomerDataSource {
  public List<Customer> search(Criterion criterion) throws SQLException;
}
