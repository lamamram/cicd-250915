package lan.dawan.java_sb.services;

import lan.dawan.java_sb.entities.Account;


public interface AccountService {
  Account getAccountByNumero(String numero);

  Account withdrawal(Account account, Float amount) throws Exception;

  Account deposit(Account account, Float amount) throws Exception;
}
