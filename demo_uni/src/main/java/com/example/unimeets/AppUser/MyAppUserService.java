package main.java.com.example.unimeets.AppUser;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyAppUserService implements UserDetailsService {
    
    private MyAppUserRepository repository;
    
    @Autowired
    public MyAppUserService(MyAppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyAppUser> user = repository.findByUsername(username);
        if (user.isPresent()) {

            var user0bj = user.get();
            return User.builder()
                    .username(user0bj.getUsername()) 
                    .password(user0bj.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
    }
    
}
