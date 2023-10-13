package ru.ivan.domain.repository;

import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.StatisticsDate;

import java.sql.SQLException;

public interface StatisticsRepository {
  public Statistics getStatistics(StatisticsDate statisticsDate) throws SQLException;
}
