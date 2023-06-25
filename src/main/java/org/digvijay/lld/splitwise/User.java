// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.splitwise;

import java.util.ArrayList;
import java.util.List;

public class User {
  private int id;
  private String name;
  private String email;
  private List<User> friends;
  private List<Expense> expenses;

  public User(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.friends = new ArrayList<>();
    this.expenses = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public List<User> getFriends() {
    return friends;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void addFriend(User friend) {
    friends.add(friend);
  }

  public void addExpense(Expense expense) {
    expenses.add(expense);
  }

  public List<Expense> getExpenseHistory(Group group) {
    List<Expense> expenseHistory = new ArrayList<>();
    for (Expense expense : expenses) {
      if (expense.getGroup() != null && expense.getGroup().equals(group)) {
        expenseHistory.add(expense);
      }
    }
    return expenseHistory;
  }

  public List<Expense> getExpenseHistory() {
    return expenses;
  }
}