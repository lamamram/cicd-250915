package lan.dawan.java_sb.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.entities.Client;
import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.repositories.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
  private Account account;
  Float amount;

  // simulation d'un simulacre de dépôt (mêmes attributs, mêmes méthodes)
  // MAIS pas de data !!!
  @Mock
  private AccountRepository accountRepository;

  // injections du dépôt fake dans le service
  @InjectMocks
  private AccountServiceImpl accountServiceImpl;

  // FIXTURE statique: ressource nécessaire au test
  @BeforeEach
  public void generate_account() {
    // ARRANGE : GIVEN : contexte
    User user = new User("user", "password", "ROLE_USER");
    Client client = new Client("machin", "machin@example.com", user);
    account = new Account(client, "machin", 500.f, 100.f);
    amount = 100.f;
  }

  @Test
  void testWithdrawal() {
    Float initBalance = account.getBalance();
    try {
      // ACT : WHEN : action
      accountServiceImpl.withdrawal(account, amount);
      // ASSERT : THEN : vérification
      assertThat(account.getBalance()).isEqualTo(initBalance - amount);
    } catch (IllegalArgumentException e) {
      // ASSERT : THEN : expect exception
      assertThat(e.getMessage()).contains("bucks");
    }
  }
}
