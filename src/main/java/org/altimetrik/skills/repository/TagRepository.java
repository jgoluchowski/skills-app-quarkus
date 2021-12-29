package org.altimetrik.skills.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.altimetrik.skills.model.tag.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository implements ReactivePanacheMongoRepository<Tag> {

}
