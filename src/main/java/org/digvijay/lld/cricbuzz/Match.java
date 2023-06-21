// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.cricbuzz;

import org.digvijay.lld.cricbuzz.Constants.MatchType;
import org.digvijay.lld.cricbuzz.Constants.TeamName;

import java.util.ArrayList;
import java.util.List;

public class Match {
  private int matchNumber;
  private Team team1;
  private Team team2;
  private int score1;
  private int score2;
  private List<String> commentary;
  private List<String> ballByBallCommentary;
  private MatchType matchType;
  private String venue;
  private String seriesName;

  public Match(int matchNumber, Team team1, Team team2, MatchType matchType, String venue, String seriesName) {
    this.matchNumber = matchNumber;
    this.team1 = team1;
    this.team2 = team2;
    this.matchType = matchType;
    this.venue = venue;
    this.seriesName = seriesName;
    this.score1 = 0;
    this.score2 = 0;
    this.commentary = new ArrayList<>();
    this.ballByBallCommentary = new ArrayList<>();
  }

  public int getMatchNumber() {
    return matchNumber;
  }

  public Team getTeam1() {
    return team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public MatchType getMatchType() {
    return matchType;
  }

  public String getVenue() {
    return venue;
  }

  public String getSeriesName() {
    return seriesName;
  }

  public int getScore(TeamName teamName) {
    if (team1.getTeamName() == teamName) {
      return score1;
    } else if (team2.getTeamName() == teamName) {
      return score2;
    } else {
      throw new IllegalArgumentException("Invalid team name");
    }
  }

  public void updateScore(Team team, int runs, String comment) {
    if (team.equals(team1)) {
      score1 += runs;
    } else if (team.equals(team2)) {
      score2 += runs;
    } else {
      throw new IllegalArgumentException("Invalid team");
    }
    commentary.add(comment);
    ballByBallCommentary.add(comment + " - " + runs + " runs");
  }

  public String getSummary() {
    return "Match " + matchNumber + ": " + team1 + ": " + score1 + " vs " + team2 + ": " + score2 +
            " (" + matchType + ") at " + venue + ", " + seriesName;
  }

  public List<String> getCommentary() {
    return commentary;
  }

  public List<String> getBallByBallCommentary() {
    return ballByBallCommentary;
  }
}