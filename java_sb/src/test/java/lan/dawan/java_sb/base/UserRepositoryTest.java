package lan.dawan.java_sb.base;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import lan.dawan.java_sb.repositories.UserRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testAtLeastOneUserExistsInDatabase() {
    // GIVEN - nothing here
    // When - Récupération du nombre total d'utilisateurs
    long userCount = userRepository.count();

    // Then - Vérification qu'il y a au moins un utilisateur
    assertTrue(userCount >= 1,
        "Il devrait y avoir au moins un utilisateur dans la base de données");
  }
}
