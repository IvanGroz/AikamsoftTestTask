package ru.ivan.domain.repository;

import ru.ivan.domain.entity.StatisticsDate;

import java.io.FileNotFoundException;

public interface StatisticsDateRepository {
  public StatisticsDate get(String filepath) throws FileNotFoundException;
}
