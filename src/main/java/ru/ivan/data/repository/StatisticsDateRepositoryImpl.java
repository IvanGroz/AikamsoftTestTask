package ru.ivan.data.repository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import ru.ivan.data.converter.StatisticsDateConverter;
import ru.ivan.data.model.CriteriaModel;
import ru.ivan.data.model.StatisticsDateModel;
import ru.ivan.data.validator.StatisticsDateValidator;
import ru.ivan.domain.entity.StatisticsDate;
import ru.ivan.domain.repository.StatisticsDateRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StatisticsDateRepositoryImpl implements StatisticsDateRepository {
  private final Gson gson;
  private final StatisticsDateConverter converter;

  public StatisticsDateRepositoryImpl(Gson gson, StatisticsDateConverter converter) {
    this.gson = gson;
    this.converter = converter;
  }

  @Override
  public StatisticsDate get(String filepath) throws IOException {
    JsonReader jsonReader = new JsonReader(new FileReader(filepath, StandardCharsets.UTF_8));
    StatisticsDateModel model = gson.fromJson(jsonReader, StatisticsDateModel.class);

    StatisticsDateValidator statisticsDateValidator=new StatisticsDateValidator();
     StatisticsDate statisticsDate = converter.convert(model);
    statisticsDateValidator.validate(statisticsDate);
    return statisticsDate;
  }
}
