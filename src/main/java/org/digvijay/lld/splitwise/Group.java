// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {
  private int id;
  private String name;
  private List<User> members;
  private List<Expense> expenses;

  public Group(int id, String name, List<User> members) {
    this.id = id;
    this.name = name;
    this.members = members;
    this.expenses = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<User> getMembers() {
    return members;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void addExpense(Expense expense) {
    expenses.add(expense);
  }
}
