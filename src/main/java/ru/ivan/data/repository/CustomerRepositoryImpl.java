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
//  private final Map<String, String> sqlMap = Map.of(
//          "surname", "SELECT * FROM customer WHERE surname = '%s'",
//          "product", "SELECT * FROM customer" +
//                  " WHERE id IN (SELECT id" +
//                  " FROM (SELECT c.id, count(*)" +
//                  " FROM purchase" +
//                  " JOIN product p on p.id = purchase.product_id" +
//                  " JOIN customer c on c.id = purchase.customer_id" +
//                  " WHERE title = '%s'" +
//                  " group by c.id) as ic" +
//                  " WHERE ic.count >= %d);",
//          "expenses", "SELECT * FROM customer" +
//                  " WHERE id IN (SELECT id from (SELECT c.id, sum(price)" +
//                  " FROM purchase" +
//                  " JOIN product p on p.id = purchase.product_id" +
//                  " JOIN customer c on c.id = purchase.customer_id" +
//                  " GROUP BY c.id) as sums" +
//                  " WHERE sums.sum BETWEEN %d AND %d)",
//          "badCustomers", "SELECT * FROM customer" +
//                  " WHERE id IN (Select bads.customer_id" +
//                  " from (SELECT customer_id, count(*) as buys" +
//                  " FROM purchase" +
//                  " GROUP BY customer_id" +
//                  " ORDER BY buys" +
//                  " limit %d) as bads)"
//  );
//  private List<Customer> convertResultSetToCostumerList(ResultSet rs) throws SQLException {
//    List<Customer> customerList;
//    customerList = new ArrayList<>();
//
//    while (rs.next()){
//      String name = rs.getString("name");
//      String surname = rs.getString("surname");
//      customerList.add(new Customer(name,surname));
//    }
//    return customerList;
//  }
//  private String getSQL(Criterion criterion) {
//    if (criterion.lastName != null) {
//      return String.format(this.sqlMap.get("surname"), criterion.lastName);
//    }
//    if (criterion.productName != null && criterion.minTimes != null) {
//      return String.format(this.sqlMap.get("product"), criterion.productName, criterion.minTimes);
//    }
//    if (criterion.minExpenses != null && criterion.maxExpenses != null) {
//      return String.format(this.sqlMap.get("expenses"),
//                           criterion.minExpenses,
//                           criterion.maxExpenses
//      );
//    }
//    if (criterion.badCustomers != null) {
//      return String.format(this.sqlMap.get("badCustomers"), criterion.badCustomers);
//    }
//    return null;
//  }
}
