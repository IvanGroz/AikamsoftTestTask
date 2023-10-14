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

public class StatisticsDateRepositoryImpl implements StatisticsDateRepository {
  private final Gson gson;
  private final StatisticsDateConverter converter;

  public StatisticsDateRepositoryImpl(Gson gson, StatisticsDateConverter converter) {
    this.gson = gson;
    this.converter = converter;
  }

  @Override
  public StatisticsDate get(String filepath) throws FileNotFoundException {
    JsonReader jsonReader = new JsonReader(new FileReader(filepath));
    StatisticsDateModel model = gson.fromJson(jsonReader, StatisticsDateModel.class);
    
    StatisticsDateValidator statisticsDateValidator=new StatisticsDateValidator();
     StatisticsDate statisticsDate = converter.convert(model);
    statisticsDateValidator.validate(statisticsDate);
    return statisticsDate;
  }
}
