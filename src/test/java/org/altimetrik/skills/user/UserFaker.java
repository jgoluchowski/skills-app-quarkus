package org.altimetrik.skills.user;

import static org.altimetrik.skills.model.user.UserRole.ADMIN;
import static org.altimetrik.skills.model.user.UserRole.EMPLOYEE;

import org.altimetrik.skills.model.user.User;

public class UserFaker {

  public static User TEST_USER = User.builder()
      .privateEmail("test@mail.com")
      .companyEmail("test@mail.com")
      .name("test")
      .surname("test")
      .role(ADMIN)
      .build();

  public static User TEST_USER_2 = User.builder()
      .privateEmail("test2@mail.com")
      .companyEmail("test2@mail.com")
      .name("test2")
      .surname("test2")
      .role(EMPLOYEE)
      .build();

}
