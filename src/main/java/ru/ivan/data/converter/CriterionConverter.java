package ru.ivan.data.converter;

import ru.ivan.data.model.CriteriaModel.CriterionModel;
import ru.ivan.domain.entity.Criterion;

public class CriterionConverter {

  public Criterion convert(CriterionModel from) {
    return new Criterion(
            from.lastName,
            from.productName,
            from.minTimes,
            from.minExpenses,
            from.maxExpenses,
            from.badCustomers
    );
  }
}