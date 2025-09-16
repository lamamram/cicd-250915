package lan.dawan.java_sb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne(mappedBy = "client")
  private Account account;

  // Constructeur sans le champ id (généré automatiquement)
  public Client(String name, String email, User user) {
    this.name = name;
    this.email = email;
    this.user = user;
  }
}
