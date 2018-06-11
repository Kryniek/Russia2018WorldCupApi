package pl.cup.russia.api.Russia2018Api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.cup.russia.api.Russia2018Api.model.security.User;
import pl.cup.russia.api.Russia2018Api.repository.security.UserRepository;

import java.util.Objects;

@Service
public class SecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (Objects.isNull(user))
            throw new UsernameNotFoundException(username);

        return user;

    }

}
