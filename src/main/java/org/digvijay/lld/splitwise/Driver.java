// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.splitwise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {
  public static void main(String[] args) {
    SplitwiseManager splitwiseManager = new SplitwiseManager();

    // Add users and create a group
    User user1 = splitwiseManager.addUser("John", "john@example.com");
    User user2 = splitwiseManager.addUser("Alice", "alice@example.com");
    User user3 = splitwiseManager.addUser("Bob", "bob@example.com");
    User user4 = splitwiseManager.addUser("Emily", "emily@example.com");
    User user5 = splitwiseManager.addUser("Digvijay", "digvijay@example.com");

    List<User> groupMembers = Arrays.asList(user1, user2, user3);
    Group group = splitwiseManager.createGroup("Group1", groupMembers);

    // Add group expense
    Expense expense1 = splitwiseManager.addExpense(100.0, "Dinner", user1, group);
    Expense expense2 = splitwiseManager.addExpense(200.0, "Dinner", user2, group);

    // Add individual expenses
    Expense expense4 = splitwiseManager.addExpense(50.0, "Movie", user5, new ArrayList());
    Expense expense3 = splitwiseManager.addExpense(30.0, "Lunch", user3, Arrays.asList(user1, user3, user4));

    // Get expense history for a group
    String groupName = "Group1";
    Group groupByName = splitwiseManager.getGroupByName(groupName);
    if (groupByName != null) {
      List<Expense> groupExpenseHistory = groupByName.getExpenses();
      System.out.println("Expense History for Group " + groupName + ":");
      for (Expense expense : groupExpenseHistory) {
        displayExpenseDetails(expense);
      }
    } else {
      System.out.println("Group not found with name: " + groupName);
    }

    // Get expense history for an individual user
    System.out.println("Expense History for User " + user5.getName() + ":");
    List<Expense> userExpenseHistory = user5.getExpenseHistory();
    for (Expense expense : userExpenseHistory) {
      displayExpenseDetails(expense);
    }
  }

  private static void displayExpenseDetails(Expense expense) {
    System.out.println("Expense ID: " + expense.getId());
    System.out.println("Amount: " + expense.getAmount());
    System.out.println("Description: " + expense.getDescription());
    System.out.println("Payer: " + expense.getPayer().getName());
    if (expense.getGroup() != null) {
      System.out.println("Group: " + expense.getGroup().getName());
    }
    if (!expense.getParticipants().isEmpty()) {
      System.out.println("Participants: ");
      for (User participant : expense.getParticipants()) {
        System.out.println("- " + participant.getName() + " (Individual Amount: " + expense.getIndividualAmounts().get(participant) + ")");
      }
    }
    System.out.println("------");
  }
}