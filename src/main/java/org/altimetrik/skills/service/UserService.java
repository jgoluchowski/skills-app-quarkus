package org.altimetrik.skills.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.skill.Skill;
import org.altimetrik.skills.model.skill.UserBySkillAndRoleRequestDto;
import org.altimetrik.skills.model.user.SkillDocument;
import org.altimetrik.skills.model.user.User;
import org.altimetrik.skills.model.user.UserSkillRequestDto;
import org.altimetrik.skills.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final SkillService skillService;

  public Multi<User> getAllUsers() {
    return userRepository.findAll().stream();
  }

  public Uni<User> getUserById(ObjectId userId) {
    return userRepository.findById(userId).onItem().ifNull()
        .failWith(() -> new ObjectNotFoundException("User", userId.toString()));
  }

  public Uni<User> saveUser(User user, Optional<List<UserSkillRequestDto>> userSkillRequestDto) {
    return mapRequestToSkillsList(userSkillRequestDto)
        .flatMap(skillDocuments -> userRepository.persist(
            user.toBuilder().skillDocuments(skillDocuments).build()));
  }

  public Uni<User> updateUser(ObjectId id, User user) {
    return getUserById(id)
        .flatMap(existingUser -> userRepository.update(existingUser.toBuilder()
            .name(user.getName())
            .surname(user.getSurname())
            .privateEmail(user.getPrivateEmail())
            .companyEmail(user.getCompanyEmail())
            .role(user.getRole())
            .build())
        );
  }

  public Uni<Void> deleteUser(ObjectId id) {
    return getUserById(id).flatMap(userRepository::delete);
  }

  public Multi<User> findUsersBySkillsAndRole(UserBySkillAndRoleRequestDto request) {
    return userRepository
        .findAll().stream()
        .filter(user -> request.getRoles().contains(user.getRole()))
        .filter(user -> user.getSkillDocuments().stream().allMatch(
            skillDocument -> request.getSkillFilters().stream().allMatch(
                skillFilter -> skillFilter.getSkillId().equals(skillDocument.getSkillId())
                    && skillFilter.getExperienceLevels()
                    .contains(skillDocument.getExperienceLevel())
                    && skillFilter.getHypes().contains(skillDocument.getHype()))));
  }

  private Uni<List<SkillDocument>> mapRequestToSkillsList(
      Optional<List<UserSkillRequestDto>> userSkillRequestDto) {
    return skillService.findAllList(
            userSkillRequestDto.stream().flatMap(Collection::stream)
                .map(UserSkillRequestDto::getSkillId).toList())
        .map(skills -> userSkillRequestDto.stream().flatMap(Collection::stream)
            .filter(userSkill -> skills.stream()
                .anyMatch(skill -> skill.getId().toString().equals(userSkill.getSkillId())))
            .map(userSkill -> SkillDocument.builder()
                .skillId(userSkill.getSkillId())
                .skillName(skills.stream()
                    .filter(skill -> skill.getId().toString().equals(userSkill.getSkillId()))
                    .findFirst().map(Skill::getName).orElseThrow(
                        () -> new ObjectNotFoundException("Skill", userSkill.getSkillId())))
                .hype(userSkill.getHype())
                .experienceLevel(userSkill.getExperienceLevel())
                .build()).toList());
  }
}
