package pl.cup.russia.api.Russia2018Api.definition.security;

import pl.cup.russia.api.Russia2018Api.model.security.User;

import java.util.List;

public interface UserService {

    User registerNewUserAccount(User user);

    User selectUserByUsername(String username);

    Integer setUserPaidStatusToTrue(String username);

    List<String> getPaidUsersUsernames();

    List<String> getNonPaidUsersUsernames();
}
