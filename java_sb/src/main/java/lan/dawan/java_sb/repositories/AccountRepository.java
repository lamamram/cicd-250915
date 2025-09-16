package lan.dawan.java_sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import lan.dawan.java_sb.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByNumero(String numero);

  Account findByClientId(Long clientId);
}
