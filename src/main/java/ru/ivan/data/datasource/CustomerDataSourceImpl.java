package ru.ivan.data.datasource;


import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerDataSourceImpl implements CustomerDataSource {
  private final Database database;
  private final static String SURNAME = "surname";
  private final static String PRODUCT = "product";
  private final static String EXPENSES = "expenses";
  private final static String BAD_CUSTOMERS = "badCustomers";
  private final Map<String, String> sqlMap = Map.of(
          "surname", "SELECT * FROM customer WHERE surname = '%s'",
          "product", "SELECT * FROM customer" +
                  " WHERE id IN (SELECT id" +
                  " FROM (SELECT c.id, count(*)" +
                  " FROM purchase" +
                  " JOIN product p on p.id = purchase.product_id" +
                  " JOIN customer c on c.id = purchase.customer_id" +
                  " WHERE title = '%s'" +
                  " GROUP BY c.id) AS ic" +
                  " WHERE ic.count >= %d);",
          "expenses", "SELECT * FROM customer" +
                  " WHERE id IN (SELECT id from (SELECT c.id, sum(price)" +
                  " FROM purchase" +
                  " JOIN product p ON p.id = purchase.product_id" +
                  " JOIN customer c ON c.id = purchase.customer_id" +
                  " GROUP BY c.id) AS sums" +
                  " WHERE sums.sum BETWEEN %d AND %d)",
          "badCustomers", "SELECT * FROM customer" +
                  " WHERE id IN (Select bads.customer_id" +
                  " FROM (SELECT customer_id, count(*) AS buys" +
                  " FROM purchase" +
                  " GROUP BY customer_id" +
                  " ORDER BY buys" +
                  " LIMIT %d) as bads)"
  );

  public CustomerDataSourceImpl(Database database) {this.database = database;}

  @Override
  public List<Customer> search(Criterion criterion) throws SQLException {
    String sqlRequest = getSqlRequest(criterion);
    ResultSet results = database.execute(sqlRequest);
    return convertToCustomers(results);
  }

  private String getSqlRequest(Criterion criterion) {
    if (criterion.lastName != null) {
      return String.format(this.sqlMap.get(SURNAME), criterion.lastName);
    }
    if (criterion.productName != null && criterion.minTimes != null) {
      return String.format(this.sqlMap.get(PRODUCT), criterion.productName, criterion.minTimes);
    }
    if (criterion.minExpenses != null && criterion.maxExpenses != null) {
      return String.format(this.sqlMap.get(EXPENSES),
                           criterion.minExpenses,
                           criterion.maxExpenses
      );
    }
    if (criterion.badCustomers != null) {
      return String.format(this.sqlMap.get(BAD_CUSTOMERS), criterion.badCustomers);
    }
    return null;
  }
  private List<Customer> convertToCustomers(ResultSet rs) throws SQLException {
    List<Customer> customerList;
    customerList = new ArrayList<>();

    while (rs.next()) {
      String name = rs.getString("name");
      String surname = rs.getString("surname");
      customerList.add(new Customer(name, surname));
    }
    return customerList;
  }
}
