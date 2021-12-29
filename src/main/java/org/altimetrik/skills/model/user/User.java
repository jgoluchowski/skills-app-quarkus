package org.altimetrik.skills.model.user;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import java.util.List;
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
@MongoEntity(collection = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends ReactivePanacheMongoEntityBase {

  @BsonId
  private ObjectId id;
  @NonNull
  private String privateEmail;
  private String companyEmail;
  @NonNull
  private String name;
  @NonNull
  private String surname;
  @NonNull
  private UserRole role;

  private List<SkillDocument> skillDocuments;

}
