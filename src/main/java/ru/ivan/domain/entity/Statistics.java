package ru.ivan.domain.entity;

public class Statistics {
  private final String type = "stat";
  private Integer totalDays;
  private CustomerPurchases[] customers;

  public Statistics(Integer totalDays, CustomerPurchases[] customers) {
    this.totalDays = totalDays;
    this.customers = customers;
  }

  public static class CustomerPurchases{
    private String name;
    private Purchase[] purchases;

    public CustomerPurchases(String name, Purchase[] purchases) {
      this.name = name;
      this.purchases = purchases;
    }

    public String getName() {
      return name;
    }

    public Purchase[] getPurchases() {
      return purchases;
    }

    public static class Purchase {
      private final String name;
      private final Long expenses;

      public Purchase(String name, Long expenses) {
        this.name = name;
        this.expenses = expenses;
      }

      public String getName() {
        return name;
      }

      public Long getExpenses() {
        return expenses;
      }
    }
  }

  public String getType() {
    return type;
  }

  public Integer getTotalDays() {
    return totalDays;
  }

  public CustomerPurchases[] getCustomers() {
    return customers;
  }
}
