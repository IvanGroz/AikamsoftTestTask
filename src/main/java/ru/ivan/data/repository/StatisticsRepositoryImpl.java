package ru.ivan.data.repository;

import ru.ivan.data.datasource.StatisticsDataSource;
import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.repository.StatisticsRepository;

import java.sql.SQLException;

public class StatisticsRepositoryImpl implements StatisticsRepository {
  private final StatisticsDataSource dataSource;


  public StatisticsRepositoryImpl(StatisticsDataSource dataSource) {
    this.dataSource = dataSource;

  }

  @Override
  public Statistics getStatistics(StatisticsDate statisticsDate) throws SQLException {
    return dataSource.search(statisticsDate);
  }


}
