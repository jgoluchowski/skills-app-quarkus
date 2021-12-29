package org.altimetrik.skills.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.model.skill.SkillFactory;
import org.altimetrik.skills.model.skill.SkillRequestDto;
import org.altimetrik.skills.model.skill.SkillResponseDto;
import org.altimetrik.skills.service.SkillService;
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
@RequestMapping("/skill")
public class SkillController {

  private final SkillService skillService;
  private final SkillFactory skillFactory;

  @GetMapping
  public Multi<SkillResponseDto> getAllSkills() {
    return skillService.getAllSkills().map(skillFactory::create);
  }

  @GetMapping(value = "/{id}")
  public Uni<SkillResponseDto> getSkill(@PathVariable String id) {
    return skillService.getSkillById(new ObjectId(id)).map(skillFactory::create);
  }

  @PostMapping
  public Uni<SkillResponseDto> createSkill(@RequestBody @Valid SkillRequestDto requestDto) {
    return skillService
        .saveSkill(skillFactory.create(requestDto))
        .map(skillFactory::create);
  }

  @PutMapping(value = "/{id}")
  public Uni<SkillResponseDto> updateSkill(@PathVariable String id,
      @RequestBody @Valid SkillRequestDto requestDto) {
    return skillService
        .updateSkill(new ObjectId(id), skillFactory.create(requestDto))
        .map(skillFactory::create);
  }

  @DeleteMapping("/{id}")
  public Uni<Void> deleteSkill(@PathVariable String id) {
    return skillService.deleteSkill(new ObjectId(id));
  }
}
