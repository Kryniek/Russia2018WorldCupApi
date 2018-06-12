package pl.cup.russia.api.Russia2018Api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.definition.security.UserService;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.repository.security.UserRepository;
import pl.cup.russia.api.Russia2018Api.util.exception.security.UserAlreadyExistAuthenticationException;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Override
    public User registerNewUserAccount(User user) throws UserAlreadyExistAuthenticationException {
        if (isUserExists(user))
            throw new UserAlreadyExistAuthenticationException("Użytkownik o loginie: " + user.getUsername()
                    + ", już istnieje.");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    private Boolean isUserExists(User user) {
        if (!isNull(selectUserByUsername(user.getUsername())))
            return true;

        return false;
    }

}
