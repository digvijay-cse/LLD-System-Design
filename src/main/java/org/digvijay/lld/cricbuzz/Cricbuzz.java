// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.cricbuzz;

import org.digvijay.lld.cricbuzz.Constants.TeamName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cricbuzz {
  private Map<Integer, Match> matches;

  public Cricbuzz() {
    this.matches = new HashMap<>();
  }

  public void addMatch(Match match) {
    matches.put(match.getMatchNumber(), match);
  }

  public void updateScore(int matchNumber, Team team, int runs, String comment) {
    Match match = matches.get(matchNumber);
    if (match != null) {
      match.updateScore(team, runs, comment);
    }
  }

  public String getMatchSummary(int matchNumber) {
    Match match = matches.get(matchNumber);
    if (match != null) {
      return match.getSummary();
    }
    return "Match not found!";
  }

  public List<String> getMatchCommentary(int matchNumber) {
    Match match = matches.get(matchNumber);
    if (match != null) {
      return match.getCommentary();
    }
    return new ArrayList<>();
  }

  public int getScore(int matchNumber, TeamName teamName) {
    Match match = matches.get(matchNumber);
    if (match != null) {
      return match.getScore(teamName);
    }
    return 0;
  }
}

