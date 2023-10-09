package ru.ivan.domain.repository;

import ru.ivan.domain.entity.Consumer;
import ru.ivan.domain.entity.Criterion;

import java.util.List;

public interface ConsumerRepository {
  public List<Consumer> get(List<Criterion> criteria);
}
