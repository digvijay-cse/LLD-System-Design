// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.cricbuzz;

import org.digvijay.lld.cricbuzz.Constants.MatchType;
import org.digvijay.lld.cricbuzz.Constants.TeamName;

import java.util.List;

public class Driver {
  public static void main(String[] args) {
    // Creating teams
    Team team1 = new Team(TeamName.INDIA);
    Team team2 = new Team(TeamName.ENGLAND);

    // Adding players to teams
    Player player1 = new Player("Virat Kohli");
    Player player2 = new Player("Joe Root");
    team1.addPlayer(player1);
    team2.addPlayer(player2);

    // Creating a match
    Match match = new Match(1, team1, team2, MatchType.TEST, "Edgbaston", "India vs England");

    // Adding the match to Cricbuzz
    Cricbuzz cricbuzz = new Cricbuzz();
    cricbuzz.addMatch(match);

    // Updating scores and commentary
    cricbuzz.updateScore(1, team1, 10, "Virat Kohli hits a boundary!");
    cricbuzz.updateScore(1, team2, 5, "Joe Root takes a quick single.");

    // Retrieving match summary, commentary, and scores
    String matchSummary = cricbuzz.getMatchSummary(1);
    List<String> commentary = cricbuzz.getMatchCommentary(1);
    int team1Score = cricbuzz.getScore(1, TeamName.INDIA);
    int team2Score = cricbuzz.getScore(1, TeamName.ENGLAND);

    // Printing the results
    System.out.println(matchSummary);
    System.out.println("Commentary:");
    for (String comment : commentary) {
      System.out.println(comment);
    }
    System.out.println("Team 1 score: " + team1Score);
    System.out.println("Team 2 score: " + team2Score);
  }
}
