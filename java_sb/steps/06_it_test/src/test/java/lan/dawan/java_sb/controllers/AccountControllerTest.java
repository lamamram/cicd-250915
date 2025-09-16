package lan.dawan.java_sb.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.entities.Client;
import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.repositories.AccountRepository;
import lan.dawan.java_sb.repositories.ClientRepository;
import lan.dawan.java_sb.services.impl.AccountServiceImpl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// 1. TDD normale (attributs, fixture, assertion initiale ...)
// 2. logique de Mocking (ExtendWith(MockitoExtension.class) => Mock [dépôt/service] => Injects
// [contrôleur])
// 3. instanciation & usage d'un MockMvc
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
  // simule la connexion http (bien en CICD)
  private MockMvc mvc;
  private User user;
  private Client client;
  private Account account;
  private Client targetClient;
  private Account targetAccount;
  private Float amount;

  @Mock
  @Autowired
  private ClientRepository clientRepository;
  @Mock
  @Autowired
  private AccountRepository accountRepository;
  @Mock
  private AccountServiceImpl accountService;

  @InjectMocks
  private AccountController accountController;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders.standaloneSetup(accountController).build();
    
    user = new User("user", "password", "ROLE_USER");
    user.setId(1L);
    User targetUser = new User("userb", "password", "ROLE_USER");
    client = new Client("machin", "machin@example.com", user);
    client.setId(1L);
    targetClient = new Client("bidule", "bidule@example.com", targetUser);
    targetClient.setId(2L);
    account = new Account(client, "machin", 500.f, 100.f);
    targetAccount = new Account(targetClient, "bidule2", 200.f, 50.f);
    amount = 100.f;
  }

  // l'utilisation du mockmvc demande à la méthode de test de pourvoir lancer une exception
  @Test
  @WithMockUser(username = "user", password = "password")
  void testTransfer() throws Exception {
    Long clientId = targetClient.getId();
    // simulation des méthodes du contrôleur réél
    // déjà testés avec les tests unitaires !!!
    // on simule le comportement du service
    given(clientRepository.findByUserUsername("user")).willReturn(client);
    given(accountRepository.findByClientId(client.getId())).willReturn(account);
    given(accountRepository.findByClientId(clientId)).willReturn(targetAccount);
    account.setBalance(account.getBalance() - amount);
    targetAccount.setBalance(account.getBalance() + amount);
    given(accountService.withdrawal(account, (float) amount)).willReturn(account);
    given(accountService.deposit(targetAccount, (float) amount)).willReturn(targetAccount);
    
    MockHttpServletResponse response = mvc
        .perform(post("/accounts/transfer")
            .param("amount", String.valueOf(amount))
            .param("clientId", String.valueOf(clientId)))
        .andReturn()
        .getResponse();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
  }
}
