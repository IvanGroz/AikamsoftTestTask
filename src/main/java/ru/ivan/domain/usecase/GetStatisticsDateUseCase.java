package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.repository.StatisticsDateRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GetStatisticsDateUseCase {
  private final StatisticsDateRepository statisticsDateRepository;

  public GetStatisticsDateUseCase(
          StatisticsDateRepository statisticsDateRepository) {
    this.statisticsDateRepository = statisticsDateRepository;
  }
  public StatisticsDate invoke(String filepath) throws IOException {
    return statisticsDateRepository.get(filepath);
  }
}
