package org.digvijay.lld.cricbuzz;

import org.digvijay.lld.cricbuzz.Constants.TeamName;

public class Score {
  TeamName teamName;
  double over;
  int run;
  int wicket;
  int extra;

  Score(TeamName teamName, double over, int run, int wicket, int extra) {
    this.teamName = teamName;
    this.run = run;
    this.wicket = wicket;
    this.over = over;
    this.extra = extra;
  }

  public TeamName getTeamName () {
    return teamName;
  }

  public void setTeamName (TeamName teamName) {
    this.teamName = teamName;
  }

  public double getOver () {
    return over;
  }

  public void setOver (double over) {
    this.over = over;
  }

  public int getRun () {
    return run;
  }

  public void setRun (int run) {
    this.run = run;
  }

  public int getWicket () {
    return wicket;
  }

  public void setWicket (int wicket) {
    this.wicket = wicket;
  }

  public int getExtra () {
    return extra;
  }

  public void setExtra (int extra) {
    this.extra = extra;
  }

  @Override
  public String toString () {
    return "Score {" +
            "Team: " + teamName +
            ", over: " + over +
            ", run: " + run +
            ", wicket: " + wicket +
            ", extras: " + extra +
            '}';
  }
}
