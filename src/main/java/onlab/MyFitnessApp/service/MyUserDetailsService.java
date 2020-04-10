package onlab.MyFitnessApp.service;

import onlab.MyFitnessApp.dao.UserRepository;
import onlab.MyFitnessApp.entity.MyUserDetails;
import onlab.MyFitnessApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static User currentUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        currentUser = user.get();
        return user.map(MyUserDetails::new).get();
    }

    public void addUser(User u)
    {
        User user = new User();
        user.setUsername(u.getUsername());
        user.setPassword(passwordEncoder.encode(u.getPassword()));
        user.setActive(true);
        user.setRole("USER");
        userRepository.save(user);
    }
}