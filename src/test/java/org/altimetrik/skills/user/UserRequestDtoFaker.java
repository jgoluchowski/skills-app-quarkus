package org.altimetrik.skills.user;

import static org.altimetrik.skills.model.user.UserRole.ADMIN;
import static org.altimetrik.skills.model.user.UserRole.EMPLOYEE;

import org.altimetrik.skills.model.user.UserRequestDto;

public class UserRequestDtoFaker {

  public static UserRequestDto TEST_USER_REQUEST_DTO = UserRequestDto.builder()
      .privateEmail("test@mail.com")
      .companyEmail("test@mail.com")
      .name("test")
      .surname("test")
      .role(ADMIN)
      .build();

  public static UserRequestDto TEST_USER_REQUEST_DTO_2 = UserRequestDto.builder()
      .privateEmail("test2@mail.com")
      .companyEmail("test2@mail.com")
      .name("test2")
      .surname("test2")
      .role(EMPLOYEE)
      .build();
}
