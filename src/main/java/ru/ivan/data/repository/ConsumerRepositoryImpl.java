package ru.ivan.data.repository;

import ru.ivan.domain.entity.Consumer;
import ru.ivan.domain.entity.Criterion;
import ru.ivan.domain.repository.ConsumerRepository;

import java.util.List;

public class ConsumerRepositoryImpl implements ConsumerRepository {
  @Override
  public List<Consumer> get(List<Criterion> criteria) {
    // TODO: 09.10.2023 чтение из БД и конвертация в domain
    return null;
  }
}
