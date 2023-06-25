// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseManager {
  private List<User> users;
  private List<Group> groups;
  private Map<String, Group> groupMap;
  private int nextUserId;
  private int nextExpenseId;
  private int nextGroupId;

  public SplitwiseManager() {
    this.users = new ArrayList<>();
    this.groups = new ArrayList<>();
    this.groupMap = new HashMap<>();
    this.nextUserId = 1;
    this.nextExpenseId = 1;
    this.nextGroupId = 1;
  }

  public User addUser(String name, String email) {
    User user = new User(nextUserId++, name, email);
    users.add(user);
    return user;
  }

  public Group createGroup(String groupName, List<User> members) {
    Group group = new Group(nextGroupId++, groupName, members);
    groups.add(group);
    groupMap.put(groupName, group);
    return group;
  }

  // TODO: Merge two constructors
  // TODO: Implement Percentage and absolute expense.
  public Expense addExpense(double amount, String description, User payer, Group group) {
    Expense expense = new Expense(nextExpenseId++, amount, description, payer, group);
    payer.addExpense(expense);
    if (group != null) {
      group.addExpense(expense);
    }
    return expense;
  }

  public Expense addExpense(double amount, String description, User payer, List<User> participants) {
    Expense expense = new Expense(nextExpenseId++, amount, description, payer, participants);
    payer.addExpense(expense);
    for (User participant : participants) {
      participant.addExpense(expense);
    }
    return expense;
  }

  public Group getGroupByName(String groupName) {
    return groupMap.get(groupName);
  }
}
