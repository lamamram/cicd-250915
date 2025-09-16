package lan.dawan.java_sb.services.impl;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
          user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}
