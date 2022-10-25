package se452.project.grocery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.encrypt.Encryptors;
import java.security.SecureRandom;
import java.util.Base64;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.repos.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	AccountRepo accountRepo;
	private String salt = "SE 452";
	private int strSize = 16;

	private char chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ".toCharArray();

	public boolean createAccount(Account account) {
		if(account != null) {
			if(accountRepo.findAccountByEmail(account.getEmail())==null) {
				if(account.getEmail()!=null && account.getPassword()!=null) {
					account.setRole(Role.USER);
					String password = account.getPassword();
					account.setPassword(randomString());
					account.setEncryptStr(Base64.getEncoder().encodeToString(Encryptors.standard(password,
											salt).encrypt(Base64.getDecoder().decode(account.getPassword()))));
					accountRepo.save(account);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean loginAccount(Account account) {
		if(account != null) {
			if(accountRepo.findAccountByEmail(account.getEmail())!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				if(account.getPassword()
						.equals(accountRepo.findAccountByEmail(account.getEmail()).getPassword())) {
					return true;
				}
				Account record = accountRepo.findAccountByEmail(account.getEmail());
				return Base64.getEncoder().encodeToString(Encryptors.standard(account.getPassword(), salt).encrypt(Base64.getDecoder().decode(record.getPassword()))).equals(record.getEncryptStr());
			}
		}
		return false;
	}
	
	public Role getRole(Account account) {
		if(account != null) {
			if(accountRepo.findAccountByEmail(account.getEmail())!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				if(accountRepo.findAccountByEmail(account.getEmail()).getRole().equals(Role.ADMIN)) {
					return Role.ADMIN;
				}
				
				else if (accountRepo.findAccountByEmail(account.getEmail()).getRole().equals(Role.USER)) {
					return Role.USER;
				}
			}
		}
		return null;
	}

	private String randomString(){
		SecureRandom secureRandom = new SecureRandom();
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<strSize;i++){
			int nextchar = secureRandom.nextInt(strSize);
			sb.append(chars[nextchar]);
		}
		return sb.toString();
	}

}
