package pl.cup.russia.api.Russia2018Api.repository.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.cup.russia.api.Russia2018Api.model.security.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByUsername(String username);

}
