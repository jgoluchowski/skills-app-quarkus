package org.altimetrik.skills.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.altimetrik.skills.model.skill.Skill;
import org.springframework.stereotype.Repository;

@Repository
public class SkillRepository implements ReactivePanacheMongoRepository<Skill> {

}
