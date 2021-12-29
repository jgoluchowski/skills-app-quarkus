package org.altimetrik.skills.tag;

import org.altimetrik.skills.model.tag.TagRequestDto;

public class TagRequestDtoFaker {

  public static TagRequestDto TEST_TAG_REQUEST_DTO = TagRequestDto.builder()
      .tagName("someTag")
      .build();

  public static TagRequestDto TEST_TAG_2_REQUEST_DTO = TagRequestDto.builder()
      .tagName("someTag2")
      .build();

}
