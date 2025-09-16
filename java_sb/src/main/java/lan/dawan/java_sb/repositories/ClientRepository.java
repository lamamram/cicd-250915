package lan.dawan.java_sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lan.dawan.java_sb.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
  // This interface will automatically provide CRUD operations for Client entities
  // Additional custom query methods can be defined here if needed
  // For example:
  // List<Client> findByName(String name);
  Client findByUserUsername(String username);
}
