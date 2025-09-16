package lan.dawan.java_sb.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import lan.dawan.java_sb.entities.User;
import lan.dawan.java_sb.entities.Client;
import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.repositories.AccountRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;

// utilisation de Mockito avec Spring Boot
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

  @BeforeEach
  public void generate_account() {
    // ARRANGE : GIVEN : contexte
    User user = new User("user", "password", "ROLE_USER");
    Client client = new Client("machin", "machin@example.com", user);
    account = new Account(client, "machin", 500.f, 100.f);
    amount = 100.f;
  }

  // jeu de données statique
  // private static Stream<Arguments> generator() {
  //   Arguments arg1 = Arguments.of(new Account("machin", 500.f, -100.f), 100.f);
  //   Arguments arg2 = Arguments.of(new Account("trucmuche", 50.f, -50.f), 120.f);
  //   return Stream.of(arg1, arg2);
  // }

  // @ParameterizedTest
  // @MethodSource("generator")
  // void testWithdrawal(Account account, Float amount) {
  // Float initBalance = account.getBalance();
  // try {
  // accountServiceImpl.withdrawal(account, amount);
  // assertThat(account.getBalance()).isEqualTo(initBalance - amount);
  // }
  // catch (IllegalArgumentException e) {
  // assertThat(e.getMessage()).contains("bucks");
  // }
  // }

  // chemin depuis src/test/resources
  // normalement ce chemin est auto. dans le classpath
  // sinon pom.xml -> build -> resources
  @ParameterizedTest
  @CsvFileSource(resources = "/fixtures/amounts.csv", numLinesToSkip = 1)
  void testWithdrawal(Float amount, String status) {
    Float initBalance = account.getBalance();
    if (status.equals("OK")) {
      accountServiceImpl.withdrawal(account, amount);
      assertThat(account.getBalance()).isEqualTo(initBalance - amount);
    } else {
      assertThatThrownBy(() -> accountServiceImpl.withdrawal(account, amount))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("bucks");
    }
  }
}
