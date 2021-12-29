package org.altimetrik.skills.model.user;

import java.util.Collection;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

  public User create(UserRequestDto userRequestDto) {
    return User.builder()
        .privateEmail(userRequestDto.getPrivateEmail())
        .companyEmail(userRequestDto.getCompanyEmail())
        .name(userRequestDto.getName())
        .surname(userRequestDto.getSurname())
        .role(userRequestDto.getRole())
        .build();
  }

  public UserResponseDto create(User user) {
    return UserResponseDto.builder()
        .id(user.getId().toString())
        .privateEmail(user.getPrivateEmail())
        .companyEmail(user.getCompanyEmail())
        .name(user.getName())
        .surname(user.getSurname())
        .role(user.getRole())
        .skillDocuments(
            Optional.ofNullable(user.getSkillDocuments()).stream().flatMap(Collection::stream)
                .map(this::create).toList())
        .build();
  }

  public UserSkillResponseDto create(SkillDocument skillDocument) {
    return UserSkillResponseDto.builder()
        .skillName(skillDocument.getSkillName())
        .hype(skillDocument.getHype())
        .experienceLevel(skillDocument.getExperienceLevel())
        .build();
  }

}
