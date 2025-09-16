package lan.dawan.java_sb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lan.dawan.java_sb.entities.Client;
import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.repositories.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HomeController {
  @Value("${spring.application.name}")
  String appName;

  @Autowired
  private UserRepository userRepository;
  private AuthenticationManager authenticationManager;


  @GetMapping("/")
  public String home(Model model) {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByUsername(userName);
    Client client = user.getClient();
    model.addAttribute("appName", appName);
    model.addAttribute("client", client);
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public ResponseEntity<?> postMethodName(@RequestBody User user) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      return ResponseEntity.ok("Authentication successful");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("Authentication failed: username or password is incorrect");
    }
  }


}
