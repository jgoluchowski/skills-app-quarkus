package org.altimetrik.skills.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import org.altimetrik.skills.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements ReactivePanacheMongoRepository<User> {

}
