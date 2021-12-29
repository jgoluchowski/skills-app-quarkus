package org.altimetrik.skills.model.user;

public enum ExperienceLevel {
  NO_EXPERIENCE("I've never done anything in it"),
  BEGINNER("Very simple tutorials"),
  INTERMEDIATE("I worked in it commercially"),
  ADVANCED("Advance knowledge/independent"),
  COACH_CONSULTANT("I can teach/help with this");

  public final String explanation;

  ExperienceLevel(String explanation) {
    this.explanation = explanation;
  }
}
