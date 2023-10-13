package ru.ivan.data.datasource;

import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.StatisticsDate;

import java.sql.SQLException;

public interface StatisticsDataSource {
  public Statistics search(StatisticsDate statisticsDate) throws SQLException;
}
