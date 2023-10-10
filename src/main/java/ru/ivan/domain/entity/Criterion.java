package ru.ivan.domain.entity;

public class Criterion {
  public String lastName = null;
  public String productName = null;
  public Integer minTimes = null;
  public Integer minExpenses = null;
  public Integer maxExpenses = null;
  public Integer badCustomers = null;

  public Criterion(String lastName, String productName, int minTimes, int minExpenses,
                   int maxExpenses, int badCustomers) {
    this.lastName = lastName;
    this.productName = productName;
    this.minTimes = minTimes;
    this.minExpenses = minExpenses;
    this.maxExpenses = maxExpenses;
    this.badCustomers = badCustomers;
  }

}
