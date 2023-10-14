package ru.ivan.domain.repository;

import ru.ivan.domain.entity.StatisticsDate;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StatisticsDateRepository {
  public StatisticsDate get(String filepath) throws IOException;
}
