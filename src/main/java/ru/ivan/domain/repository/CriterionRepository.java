package ru.ivan.domain.repository;

import ru.ivan.domain.entity.Criterion;

import java.io.FileNotFoundException;
import java.util.List;

public interface CriterionRepository {
  public List<Criterion> getAll(String filepath) throws FileNotFoundException;
}
