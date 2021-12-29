package org.altimetrik.skills.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.skill.Skill;
import org.altimetrik.skills.repository.SkillRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

  private final SkillRepository skillRepository;

  public Multi<Skill> getAllSkills() {
    return skillRepository.findAll().stream();
  }

  public Uni<Skill> getSkillById(ObjectId skillId) {
    return skillRepository.findById(skillId).onItem().ifNull()
        .failWith(() -> new ObjectNotFoundException("Skill", skillId.toString()));
  }

  public Uni<List<Skill>> findAllList() {
    return skillRepository.findAll().list();
  }

  public Uni<Skill> saveSkill(Skill skill) {
    return skillRepository.persist(skill);
  }

  public Uni<Skill> updateSkill(ObjectId id, Skill skill) {
    return getSkillById(id)
        .flatMap(existingSkill ->
            skillRepository.update(existingSkill.toBuilder()
                .name(skill.getName())
                .build()));
  }

  public Uni<Void> deleteSkill(ObjectId id) {
    return getSkillById(id).flatMap(skillRepository::delete);
  }
}
