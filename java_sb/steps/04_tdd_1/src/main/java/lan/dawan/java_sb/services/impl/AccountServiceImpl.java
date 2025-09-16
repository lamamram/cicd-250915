package lan.dawan.java_sb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lan.dawan.java_sb.entities.Account;
import lan.dawan.java_sb.repositories.AccountRepository;
import lan.dawan.java_sb.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  private AccountRepository accountRepository;



  @Override
  public Account getAccountByNumero(String numero) {
    return accountRepository.findByNumero(numero);
  }


  @Override
  public Account withdrawal(Account account, Float amount) throws IllegalArgumentException {
    if (amount > account.getBalance() + account.getOverdraft()) {
      throw new IllegalArgumentException("not enough bucks !!");
    }
    Float newBalance = (Float) account.getBalance() - (Float) amount;
    account.setBalance(newBalance);
    return accountRepository.save(account);
  }

  @Override
  public Account deposit(Account account, Float amount) throws IllegalArgumentException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
    Float newBalance = (Float) account.getBalance() + (Float) amount;
    account.setBalance(newBalance);
    return accountRepository.save(account);
  }

}
