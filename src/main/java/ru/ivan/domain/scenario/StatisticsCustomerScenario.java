package ru.ivan.domain.scenario;

import ru.ivan.domain.entity.Statistics;
import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.usecase.GetStatisticsDateUseCase;
import ru.ivan.domain.usecase.GetStatisticsUseCase;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class StatisticsCustomerScenario {
  private final GetStatisticsDateUseCase getStatisticsDateUseCase;
  private final GetStatisticsUseCase getStatisticsUseCase;

  public StatisticsCustomerScenario(GetStatisticsDateUseCase getStatisticsDateUseCase,
                                    GetStatisticsUseCase getStatisticsUseCase) {
    this.getStatisticsDateUseCase = getStatisticsDateUseCase;
    this.getStatisticsUseCase = getStatisticsUseCase;
  }


  public Statistics invoke(String filepath) throws FileNotFoundException, SQLException {
    StatisticsDate statisticsDate = getStatisticsDateUseCase.invoke(filepath);
    return getStatisticsUseCase.invoke(statisticsDate);
  }
}
