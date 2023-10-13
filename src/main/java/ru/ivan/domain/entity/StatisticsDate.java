package ru.ivan.domain.entity;

public class StatisticsDate {
  private String startDate;
  private String endDate;

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public StatisticsDate(String startDate, String endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
