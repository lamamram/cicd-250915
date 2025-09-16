package lan.dawan.java_sb.entities;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "numero", nullable = true, unique = true)
  private String numero;
  private Float balance;
  private Float overdraft;

  @OneToOne
  @JoinColumn(name = "client_id")
  private Client client;

  public Account(Client client, String numero, Float balance, Float overdraft) {
    this.client = client;
    this.numero = numero;
    this.balance = balance;
    this.overdraft = overdraft;
  }

}
