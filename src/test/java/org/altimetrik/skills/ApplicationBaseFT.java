package org.altimetrik.skills;

import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.altimetrik.skills.repository.SkillRepository;
import org.altimetrik.skills.repository.TagRepository;
import org.altimetrik.skills.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
public class ApplicationBaseFT {

  @Inject
  protected UserRepository userRepository;

  @Autowired
  protected SkillRepository skillRepository;

  @Autowired
  protected TagRepository tagRepository;

  @AfterEach
  void cleanUp() {
    userRepository.deleteAll().await().indefinitely();
    skillRepository.deleteAll().await().indefinitely();
    tagRepository.deleteAll().await().indefinitely();
  }

}
