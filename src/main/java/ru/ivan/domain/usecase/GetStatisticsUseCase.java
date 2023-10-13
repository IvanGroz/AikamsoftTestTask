package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.repository.StatisticsRepository;

import java.sql.SQLException;

public class GetStatisticsUseCase {
  private final StatisticsRepository statisticsRepository;

  public GetStatisticsUseCase(StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }
  public Statistics invoke(StatisticsDate statisticsDate) throws SQLException {
    return statisticsRepository.getStatistics(statisticsDate);
  }
}
