package org.altimetrik.skills.controller;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.security.Principal;
import java.util.Set;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.model.skill.UserBySkillAndRoleRequestDto;
import org.altimetrik.skills.model.user.UserFactory;
import org.altimetrik.skills.model.user.UserRequestDto;
import org.altimetrik.skills.model.user.UserResponseDto;
import org.altimetrik.skills.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserFactory userFactory;
  private final SecurityIdentity identity;

  @GetMapping
  public Multi<UserResponseDto> getAllUsers() {
    return userService.getAllUsers().map(userFactory::create);
  }

  @GetMapping(value = "/{id}")
  public Uni<UserResponseDto> getUser(@PathVariable(value = "id") String id) {
    return userService.getUserById(new ObjectId(id)).map(userFactory::create);
  }

  @PostMapping
  public Uni<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
    return userService
        .saveUser(userFactory.create(userRequestDto), userRequestDto.getSkillDocuments())
        .map(userFactory::create);
  }

  @PutMapping(value = "/{id}")
  public Uni<UserResponseDto> updateUser(
      @PathVariable(value = "id") String id, @RequestBody @Valid UserRequestDto userRequestDto) {
    return userService
        .updateUser(new ObjectId(id), userFactory.create(userRequestDto))
        .map(userFactory::create);
  }

  @DeleteMapping("/{id}")
  public Uni<Void> deleteUser(@PathVariable String id) {
    return userService.deleteUser(new ObjectId(id));
  }

  @PostMapping("/filterBySkills")
  public Multi<UserResponseDto> filterUsers(
      @RequestBody @Valid UserBySkillAndRoleRequestDto request) {
    return userService
        .findUsersBySkillsAndRole(request)
        .map(userFactory::create);
  }

  @GetMapping("/info")
  public Uni<Principal> userInfo() {
    return Uni.createFrom().item(identity.getPrincipal());
  }

  @GetMapping("/roles")
  public Uni<Set<String>> userRoles() {
    return Uni.createFrom().item(identity.getRoles());
  }

}
