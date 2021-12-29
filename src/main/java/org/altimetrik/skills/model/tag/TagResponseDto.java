package org.altimetrik.skills.model.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TagResponseDto {

  private String id;
  private String name;
}
