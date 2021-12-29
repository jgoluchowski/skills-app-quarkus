package org.altimetrik.skills.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.tag.Tag;
import org.altimetrik.skills.repository.TagRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Multi<Tag> getAllTags() {
    return tagRepository.findAll().stream();
  }

  public Uni<Tag> getTagById(ObjectId tagId) {
    return tagRepository
        .findById(tagId).onItem().ifNull()
        .failWith(() -> new ObjectNotFoundException("Tag", tagId.toString()));
  }

  public Uni<Tag> saveTag(Tag tag) {
    return tagRepository.persist(tag);
  }

  public Uni<Tag> updateTag(ObjectId id, Tag tag) {
    return getTagById(id)
        .flatMap(
            existingTag ->
                tagRepository.update(existingTag.toBuilder()
                    .name(tag.getName())
                    .build()));
  }

  public Uni<Void> deleteTag(ObjectId id) {
    return getTagById(id).flatMap(tagRepository::delete);
  }
}
