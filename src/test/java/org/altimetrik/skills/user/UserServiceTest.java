package org.altimetrik.skills.user;

import static org.altimetrik.skills.user.UserFaker.TEST_USER;
import static org.altimetrik.skills.user.UserFaker.TEST_USER_2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import java.util.Optional;
import org.altimetrik.skills.ApplicationBaseFT;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.user.User;
import org.altimetrik.skills.service.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
class UserServiceTest extends ApplicationBaseFT {

  @Autowired
  UserService userService;

  @Test
  void saveUserAndGetItById() {
    User user = userService.saveUser(TEST_USER, Optional.empty()).await().indefinitely();

    User userById = getUser(user.getId());

    assertAll(
        () -> assertEquals(userById.getCompanyEmail(), TEST_USER.getCompanyEmail()),
        () -> assertEquals(userById.getPrivateEmail(), TEST_USER.getPrivateEmail()),
        () -> assertEquals(userById.getName(), TEST_USER.getName()),
        () -> assertEquals(userById.getSurname(), TEST_USER.getSurname()),
        () -> assertEquals(userById.getRole(), TEST_USER.getRole())
    );
  }

  @Test
  void saveMultipleUsersAndGetList() {
    userService.saveUser(TEST_USER, Optional.empty()).await().indefinitely();
    userService.saveUser(TEST_USER_2, Optional.empty()).await().indefinitely();

    List<User> users = userService.getAllUsers().collect().asList().await().indefinitely();

    assertEquals(2, users.size());
  }

  @Test
  void saveAndUpdateUser() {
    User user = userService.saveUser(TEST_USER, Optional.empty()).await().indefinitely();
    User updatedUser = userService.updateUser(user.getId(), TEST_USER_2).await().indefinitely();

    assertAll(
        () -> assertEquals(updatedUser.getCompanyEmail(), TEST_USER_2.getCompanyEmail()),
        () -> assertEquals(updatedUser.getPrivateEmail(), TEST_USER_2.getPrivateEmail()),
        () -> assertEquals(updatedUser.getName(), TEST_USER_2.getName()),
        () -> assertEquals(updatedUser.getSurname(), TEST_USER_2.getSurname()),
        () -> assertEquals(updatedUser.getRole(), TEST_USER_2.getRole())
    );
  }

  @Test
  void saveAndDeleteUser() {
    ObjectId id = userService.saveUser(TEST_USER, Optional.empty()).await().indefinitely().getId();

    userService.deleteUser(id).await().indefinitely();

    assertAll(
        () -> assertEquals(0,
            userService.getAllUsers().collect().asList().await().indefinitely().size()),
        () -> assertThrows(ObjectNotFoundException.class, () -> getUser(id))
    );
  }

  private User getUser(ObjectId id) {
    return userService.getUserById(id).await().indefinitely();
  }

}
