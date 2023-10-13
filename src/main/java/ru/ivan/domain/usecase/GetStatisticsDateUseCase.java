package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.repository.StatisticsDateRepository;

import java.io.FileNotFoundException;

public class GetStatisticsDateUseCase {
  private final StatisticsDateRepository statisticsDateRepository;

  public GetStatisticsDateUseCase(
          StatisticsDateRepository statisticsDateRepository) {
    this.statisticsDateRepository = statisticsDateRepository;
  }
  public StatisticsDate invoke(String filepath) throws FileNotFoundException {
    return statisticsDateRepository.get(filepath);
  }
}
