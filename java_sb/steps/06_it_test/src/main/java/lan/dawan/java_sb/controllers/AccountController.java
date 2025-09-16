package lan.dawan.java_sb.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.entities.Client;
import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.repositories.UserRepository;
import lan.dawan.java_sb.repositories.AccountRepository;
import lan.dawan.java_sb.repositories.ClientRepository;
import lan.dawan.java_sb.services.impl.AccountServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountServiceImpl accountService;

    
    @GetMapping("/accounts")
    public String accounts(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(userName);
        Account account = user.getClient().getAccount();
        model.addAttribute("account", account);
        List<Client> clients = userRepository.findAll().stream()
            .map(User::getClient)
            .collect(Collectors.toList());
        model.addAttribute("clients", clients);
        return "accounts";
    }

    @PostMapping("/accounts/transfer")
    public String transfer(@RequestParam String amount, @RequestParam String clientId, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientRepository.findByUserUsername(userName);
        Account account = accountRepository.findByClientId(client.getId());
        Account targetAccount = accountRepository.findByClientId(Long.parseLong(clientId));
        try {
            accountService.withdrawal(account, Float.parseFloat(amount));
            targetAccount = accountService.deposit(targetAccount, Float.parseFloat(amount));
        }
        finally{
            model.addAttribute("message", "forbidden transfer");
        }
        model.addAttribute("account", account);
        return "accounts";
    }
    
}
