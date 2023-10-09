package ru.ivan.domain.usecase;

import ru.ivan.domain.entity.Consumer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.ConsumerRepository;
import ru.ivan.domain.repository.CriterionRepository;

import java.io.FileNotFoundException;
import java.util.List;

public class SearchConsumerUseCase {
  private final CriterionRepository criterionRepository;
  private final ConsumerRepository consumerRepository;

  public SearchConsumerUseCase(CriterionRepository criterionRepository,
                               ConsumerRepository consumerRepository) {
    this.criterionRepository = criterionRepository;
    this.consumerRepository = consumerRepository;
  }

  public List<Consumer> invoke(String filepath) throws FileNotFoundException {
    List<Criterion> criteria = criterionRepository.getAll(filepath);
    return consumerRepository.get(criteria);
  }
}
