package org.altimetrik.skills.model.user;

public enum Hype {
  LOW("Not Interested"),
  AVG("Interested"),
  HIGH("Hyped");

  public final String explanation;

  Hype(String explanation) {
    this.explanation = explanation;
  }
}
