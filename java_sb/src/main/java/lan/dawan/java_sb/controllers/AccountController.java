package lan.dawan.java_sb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.repositories.UserRepository;


@Controller
public class AccountController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/accounts")
  public String accounts(Model model) {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByUsername(userName);
    Account account = user.getClient().getAccount();
    model.addAttribute("account", account);
    return "accounts";
  }

}
