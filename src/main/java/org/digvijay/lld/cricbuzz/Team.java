// Author: Digvijay Pandey (digvijay_cse@icloud.com)
// Date  : June 2023
package org.digvijay.lld.cricbuzz;

import org.digvijay.lld.cricbuzz.Constants.TeamName;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private TeamName teamName;
  private List<Player> playingEleven;

  public Team(TeamName teamName) {
    this.teamName = teamName;
    this.playingEleven = new ArrayList<>();
  }

  public TeamName getTeamName() {
    return teamName;
  }

  public void addPlayer(Player player) {
    playingEleven.add(player);
  }

  public List<Player> getPlayingEleven() {
    return playingEleven;
  }

  @Override
  public String toString() {
    return teamName.toString();
  }
}