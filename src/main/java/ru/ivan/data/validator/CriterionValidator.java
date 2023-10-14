package ru.ivan.data.validator;

import ru.ivan.domain.entity.Criterion;

public class CriterionValidator extends AbstractValidator<Criterion> {


  @Override
  protected boolean validation(Criterion criterion) {

    if (criterion.minExpenses != null && criterion.maxExpenses != null) {
      if(criterion.minExpenses < 0 || criterion.maxExpenses < 0){
        msg = "Сумма покупок 'minExpenses' или 'maxExpenses' не может быть отрицательной ";
        return false;
      }
      if (criterion.minExpenses > criterion.maxExpenses) {
        msg = "Ошибка заполения полей критерия поиска. 'minExpenses' не может быть больше 'maxExpenses'";
        return false;
      }
    }
    if(criterion.badCustomers != null){
      if (criterion.badCustomers < 0){
        msg = "Число пассивных покупателей 'badCustomers' не может быть меньше 0";
        return false;
      }
    }
    if (criterion.minTimes != null){
      if (criterion.minTimes < 0){
        msg = "Число кол-ва минимальных покупок 'minTimes' не может быть меньше 0";
        return false;
      }
    }


    return true;
  }
}
