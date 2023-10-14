package ru.ivan.data.validator;

import ru.ivan.domain.entity.StatisticsDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

public class StatisticsDateValidator extends AbstractValidator<StatisticsDate> {
  private final DateTimeFormatter dateFormatter = DateTimeFormatter
          .ofPattern("uuuu-MM-dd", Locale.US)
          .withResolverStyle(ResolverStyle.STRICT);


  @Override
  protected boolean validation(StatisticsDate statisticsDate) {
    //statisticsDate.getEndDate().matches("([0-9]{4})-(0[1-9]|1[0-2])-(^0[1-9]|[12][0-9]|3[01])");

    try {
      TemporalAccessor temporalAccessor = this.dateFormatter.parse(statisticsDate.getStartDate());
      LocalDate localStartDate = temporalAccessor.query(LocalDate::from);
      temporalAccessor = this.dateFormatter.parse(statisticsDate.getEndDate());
      LocalDate localEndDate = temporalAccessor.query(LocalDate::from);
      long days = DAYS.between(localStartDate, localEndDate);
      if (days < 0) {
        msg = "Неверно введен параметр даты. (startDate должен быть меньше чем endDate)" +
                "Параметр 'startDate' - начальная дата, а параметр 'endDate' - конечная.";
        return false;
      }
    } catch (DateTimeParseException e) {
      msg = "Формат введенной даты неверный: правильный формат YYYY-MM-DD";
      return false;
    }
    return true;
  }
}
