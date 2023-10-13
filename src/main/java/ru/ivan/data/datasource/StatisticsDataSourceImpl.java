package ru.ivan.data.datasource;

import ru.ivan.data.database.Database;
import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.Statistics.CustomerPurchases;
import ru.ivan.domain.entity.Statistics.CustomerPurchases.Purchase;
import ru.ivan.domain.entity.StatisticsDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatisticsDataSourceImpl implements StatisticsDataSource {
  private final Database database;
  private final String SQL_QUERY_STAT =
          "SELECT concat(name, ' ', surname) as name, title, sum(price)" +
                  " FROM purchase" +
                  " JOIN product p on p.id = purchase.product_id" +
                  " JOIN customer c on c.id = purchase.customer_id" +
                  " WHERE (purchase_date BETWEEN '%s' and '%s')" +
                  " AND extract('ISODOW' FROM purchase_date) < 6" +
                  " GROUP BY title, concat(name, ' ', surname) ORDER BY name;";

  private final String SQL_QUERY_DAYS =
          "SELECT count(*) AS days FROM" +
                  " generate_series(timestamp '%s', timestamp '%s',interval  '1 day') the_day" +
                  " WHERE  extract('ISODOW' FROM the_day) < 6;";

  public StatisticsDataSourceImpl(Database database) {this.database = database;}

  @Override
  public Statistics search(StatisticsDate statisticsDate) throws SQLException {
    ResultSet statisticsResult = database.execute(getSqlRequest(statisticsDate, SQL_QUERY_STAT));
    ResultSet statisticsDays = database.execute(getSqlRequest(statisticsDate, SQL_QUERY_DAYS));
    return convertToStatistics(statisticsResult, statisticsDays);
  }

  private String getSqlRequest(StatisticsDate statisticsDate, String request) {
    return String.format(request, statisticsDate.getStartDate(), statisticsDate.getEndDate());
  }

  private Statistics convertToStatistics(ResultSet statistics,
                                         ResultSet statisticsDays) throws SQLException {
    Statistics result;
    ArrayList<CustomerPurchases> customerPurchases = new ArrayList<>();
    ArrayList<Purchase> purchases = new ArrayList<>();
    String customerName = "0";
    while (statistics.next()) {
      String nameTemp = statistics.getString("name");
      if (!customerName.equals(nameTemp)) {
        if (!purchases.isEmpty()) {
          customerPurchases.add(new CustomerPurchases(customerName,
                                                      purchases.toArray(Purchase[]::new)
          ));
          purchases = new ArrayList<>();
        }
        customerName = nameTemp;
      }

      String title = statistics.getString("title");
      Float sum = statistics.getFloat("sum");
      purchases.add(new Purchase(title, sum));
      //customerList.add(new Customer(name, surname));
    }
    customerPurchases.add(new CustomerPurchases(customerName,
                                                purchases.toArray(Purchase[]::new)
    ));
    statisticsDays.next();
    return new Statistics(statisticsDays.getInt("days"),
                          customerPurchases.toArray(CustomerPurchases[]::new)
    );


  }
}
