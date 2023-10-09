package ru.ivan.domain.entity;

public class Criterion {
  public String lastName;
  public String productName;
  public int minTimes;
  public int minExpenses;
  public int maxExpenses;
  public int badCustomers;

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
