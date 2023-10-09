package ru.ivan.data.repository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import ru.ivan.data.converter.CriterionConverter;
import ru.ivan.data.model.CriteriaModel;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CriterionRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class CriterionRepositoryImpl implements CriterionRepository {
  private final Gson gson;
  private final CriterionConverter converter;

  public CriterionRepositoryImpl(Gson gson, CriterionConverter converter) {
    this.gson = gson;
    this.converter = converter;
  }


  @Override
  public List<Criterion> getAll(String filepath) throws FileNotFoundException {
    JsonReader jsonReader = new JsonReader(new FileReader(filepath));
    CriteriaModel model = gson.fromJson(jsonReader, CriteriaModel.class);
    List<Criterion> criteria = model.criterias.stream()
                                              .map(converter::convert)
                                              .collect(Collectors.toList());
    return criteria;
  }
}
