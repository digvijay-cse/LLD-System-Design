// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Expense {
  private int id;
  private double amount;
  private String description;
  private User payer;
  private Group group;
  private List<User> participants;
  private Map<User, Double> individualAmounts;

  //TODO: Merge these two constructors.
  public Expense(int id, double amount, String description, User payer, Group group) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.payer = payer;
    this.group = group;
    this.participants = group.getMembers();
    this.individualAmounts = new HashMap<>();
    calculateIndividualAmounts();
  }

  public Expense(int id, double amount, String description, User payer, List<User> participants) {
    this.id = id;
    this.amount = amount;
    this.description = description;
    this.payer = payer;
    this.group = null;
    this.participants = participants;
    this.individualAmounts = new HashMap<>();
    calculateIndividualAmounts();
  }

  public int getId() {
    return id;
  }

  public double getAmount() {
    return amount;
  }

  public String getDescription() {
    return description;
  }

  public User getPayer() {
    return payer;
  }

  public Group getGroup() {
    return group;
  }

  public List<User> getParticipants() {
    return participants;
  }

  public Map<User, Double> getIndividualAmounts() {
    return individualAmounts;
  }

  private void calculateIndividualAmounts() {
    int numParticipants = participants.size();
    if (numParticipants > 0) {
      double individualAmount = amount / numParticipants;
      for (User participant : participants) {
        if (participant.equals(payer)) {
          individualAmounts.put(participant, individualAmount);
        } else {
          individualAmounts.put(participant, -1*individualAmount);
        }
      }
    }
  }
}
