package org.altimetrik.skills.tag;

import static org.altimetrik.skills.tag.TagFaker.TEST_TAG;
import static org.altimetrik.skills.tag.TagFaker.TEST_TAG_2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import org.altimetrik.skills.ApplicationBaseFT;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.tag.Tag;
import org.altimetrik.skills.service.TagService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
class TagServiceTest extends ApplicationBaseFT {

  @Autowired
  TagService tagService;

  @Test
  void saveUserAndGetItById() {
    Tag tag = tagService.saveTag(TEST_TAG).await().indefinitely();

    Tag tagById = getTag(tag.getId());

    assertAll(
        () -> assertEquals(tagById.getName(), TEST_TAG.getName())
    );
  }

  @Test
  void saveMultipleUsersAndGetList() {
    tagService.saveTag(TEST_TAG).await().indefinitely();
    tagService.saveTag(TEST_TAG_2).await().indefinitely();

    List<Tag> tags = tagService.getAllTags().collect().asList().await().indefinitely();

    assertEquals(2, tags.size());
  }

  @Test
  void saveAndUpdateUser() {
    Tag tag = tagService.saveTag(TEST_TAG).await().indefinitely();
    Tag updatedTag = tagService.updateTag(tag.getId(), TEST_TAG_2).await()
        .indefinitely();

    assertAll(
        () -> assertEquals(updatedTag.getName(), TEST_TAG_2.getName())
    );
  }

  @Test
  void saveAndDeleteUser() {
    ObjectId id = tagService.saveTag(TEST_TAG).await().indefinitely().getId();

    tagService.deleteTag(id).await().indefinitely();

    assertAll(
        () -> assertEquals(0,
            tagService.getAllTags().collect().asList().await().indefinitely().size()),
        () -> assertThrows(ObjectNotFoundException.class, () -> getTag(id))
    );
  }

  private Tag getTag(ObjectId id) {
    return tagService.getTagById(id).await().indefinitely();
  }

}
