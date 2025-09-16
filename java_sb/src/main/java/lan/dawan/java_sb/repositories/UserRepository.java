package lan.dawan.java_sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lan.dawan.java_sb.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
  // Additional query methods can be defined here if needed
  User findByUsername(String username);
}
