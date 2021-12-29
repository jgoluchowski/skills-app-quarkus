package org.altimetrik.skills.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.altimetrik.skills.model.tag.TagFactory;
import org.altimetrik.skills.model.tag.TagRequestDto;
import org.altimetrik.skills.model.tag.TagResponseDto;
import org.altimetrik.skills.service.TagService;
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
@RequestMapping("/tag")
public class TagController {

  private final TagService tagService;
  private final TagFactory tagFactory;

  @GetMapping
  public Multi<TagResponseDto> getAllTags() {
    return tagService.getAllTags().map(tagFactory::create);
  }

  @GetMapping(value = "/{id}")
  public Uni<TagResponseDto> getTag(@PathVariable(value = "id") String id) {
    return tagService
        .getTagById(new ObjectId(id))
        .map(tagFactory::create);
  }

  @PostMapping
  public Uni<TagResponseDto> createTag(@RequestBody @Valid TagRequestDto requestDto) {
    return tagService
        .saveTag(tagFactory.create(requestDto))
        .map(tagFactory::create);
  }

  @PutMapping(value = "/{id}")
  public Uni<TagResponseDto> updateTag(
      @PathVariable(value = "id") String id, @RequestBody @Valid TagRequestDto requestDto) {
    return tagService
        .updateTag(new ObjectId(id), tagFactory.create(requestDto))
        .map(tagFactory::create);
  }

  @DeleteMapping("/{id}")
  public Uni<Void> deleteTag(@PathVariable String id) {
    return tagService.deleteTag(new ObjectId(id));
  }

}
