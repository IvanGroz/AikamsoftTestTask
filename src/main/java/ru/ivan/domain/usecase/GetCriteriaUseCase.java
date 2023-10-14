package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.CriterionRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GetCriteriaUseCase {
  private final CriterionRepository criterionRepository;

  public GetCriteriaUseCase(
          CriterionRepository criterionRepository) {this.criterionRepository = criterionRepository;}

  public List<Criterion> invoke(String filepath) throws IOException {
    return criterionRepository.getAll(filepath);
  }
}
