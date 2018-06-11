package pl.cup.russia.api.Russia2018Api.definition.security;

import pl.cup.russia.api.Russia2018Api.model.security.User;

public interface UserService {

    User registerNewUserAccount(User user);

    User selectUserByUsername(String username);
}
