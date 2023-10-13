package ru.ivan.data.converter;

import ru.ivan.data.model.StatisticsDateModel;
import ru.ivan.domain.entity.StatisticsDate;

public class StatisticsDateConverter {
  public StatisticsDate convert(StatisticsDateModel from){
    return new StatisticsDate(from.startDate,from.endDate);
  }
}
