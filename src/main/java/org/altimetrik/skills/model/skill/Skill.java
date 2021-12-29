package org.altimetrik.skills.model.skill;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@MongoEntity(collection = "skills")
@EqualsAndHashCode(callSuper = true)
public class Skill extends ReactivePanacheMongoEntityBase {

  @BsonId
  private ObjectId id;
  @NonNull
  private String name;
  private String tagId;
}
