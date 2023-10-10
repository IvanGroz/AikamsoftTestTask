package ru.ivan.data.repository;

import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Customer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CustomerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
  private final Map<String, String> sqlMap = Map.of(
          "surname", "SELECT * FROM customer WHERE surname = '%s'",
          "product", "SELECT * FROM customer" +
                  " WHERE id IN (SELECT id" +
                  " FROM (SELECT c.id, count(*)" +
                  " FROM purchase" +
                  " JOIN product p on p.id = purchase.product_id" +
                  " JOIN customer c on c.id = purchase.customer_id" +
                  " WHERE title = '%s'" +
                  " group by c.id) as ic" +
                  " WHERE ic.count >= %d);",
          "expenses", "SELECT * FROM customer" +
                  " WHERE id IN (SELECT id from (SELECT c.id, sum(price)" +
                  " FROM purchase" +
                  " JOIN product p on p.id = purchase.product_id" +
                  " JOIN customer c on c.id = purchase.customer_id" +
                  " GROUP BY c.id) as sums" +
                  " WHERE sums.sum BETWEEN %d AND %d)",
          "badCustomers", "SELECT * FROM customer" +
                  " WHERE id IN (Select bads.customer_id" +
                  " from (SELECT customer_id, count(*) as buys" +
                  " FROM purchase" +
                  " GROUP BY customer_id" +
                  " ORDER BY buys" +
                  " limit %d) as bads)"
  );

  @Override
  public Map<Criterion, List<Customer>> getCustomerByCriteria(List<Criterion> criteria,
                                                              Database db) throws SQLException {
    // TODO: 09.10.2023 чтение из БД и конвертация в domain
    Map<Criterion, List<Customer>> result = new HashMap<Criterion, List<Customer>>();

    for (Criterion criterion : criteria) {
      ResultSet rs = db.execute(getSQL(criterion));
      result.put(criterion,convertResultSetToCostumerList(rs));
    }

    return result;
  }

  private List<Customer> convertResultSetToCostumerList(ResultSet rs){

    return null;
  }
  private String getSQL(Criterion criterion) {
    if (criterion.lastName != null) {
      return String.format(this.sqlMap.get("surname"), criterion.lastName);
    }
    if (criterion.productName != null && criterion.minTimes != null) {
      return String.format(this.sqlMap.get("product"), criterion.productName, criterion.minTimes);
    }
    if (criterion.minExpenses != null && criterion.maxExpenses != null) {
      return String.format(this.sqlMap.get("expenses"),
                           criterion.minExpenses,
                           criterion.maxExpenses
      );
    }
    if (criterion.badCustomers != null) {
      return String.format(this.sqlMap.get("badCustomers"), criterion.badCustomers);
    }
    return null;
  }
}
