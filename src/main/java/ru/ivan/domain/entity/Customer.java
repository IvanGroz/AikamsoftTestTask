package ru.ivan.domain.entity;

public class Customer {
  private int id;
  private  String name;
  private  String surname;

  public Customer(int id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }
}
