package se452.project.grocery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.repos.AccountRepo;

@Log4j2
@Service
public class AccountService {
	
	@Autowired
	AccountRepo accountRepo;

	private String salt = "ff1234";
	// private char chars[] = "0123456789abcdef".toCharArray();
	private char chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ".toCharArray();

	public boolean createAccount(Account account) {
		try{
			if(account != null) {
				if(accountRepo.findAccountByEmail(account.getEmail())==null) {
					if(account.getEmail()!=null && account.getPassword()!=null) {
						log.info("input account: "+account.getEmail());

						//convert password to hex format via hash function
						String password = toHash(account.getPassword());
						log.info("input pw: "+account.getPassword());
						log.info("input pw hash val: "+password);
						log.info("input role: "+account.getRole());

						account.setPassword(randomString(16));
						log.info("generate random str replace pw: "+account.getPassword());

						byte randomByte[] = Base64.getDecoder().decode(account.getPassword());
						log.info("trans to random: "+randomByte.toString());
						BytesEncryptor  en = Encryptors.standard(password,salt);
						log.info("set en: "+en.toString());

						byte encryptByte[] = en.encrypt(randomByte);
						log.info("encry random: "+encryptByte.toString());

						String encryptStr = Base64.getEncoder().encodeToString(encryptByte);
						log.info("generate Encrypt str: "+account.getEncryptStr());

						account.setEncryptStr(encryptStr);
						log.info("set Encrypt str: "+account.getEncryptStr());

						accountRepo.save(account);
						return true;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean loginAccount(Account account) {
		if(account != null) {
			if(accountRepo.findAccountByEmail(account.getEmail())!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				// if(account.getPassword()
				// 		.equals(accountRepo.findAccountByEmail(account.getEmail()).getPassword())) {
				// 	return true;
				// }
				Account record = accountRepo.findAccountByEmail(account.getEmail());
				String loginEncrypt = Base64.getEncoder().encodeToString(Encryptors.standard(toHash(account.getPassword()), salt).encrypt(Base64.getDecoder().decode(record.getPassword())));
				log.info("login loginEncrypt :"+loginEncrypt);
				String accountEn = record.getEncryptStr();
				log.info("saved Encrypt :"+accountEn);
				log.info("compare:"+loginEncrypt.equals(record.getEncryptStr()));

				return loginEncrypt.equals(record.getEncryptStr());
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

	private String randomString(int strSize){
		SecureRandom secureRandom = new SecureRandom();
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<strSize;i++){
			int nextchar = secureRandom.nextInt(chars.length);
			sb.append(chars[nextchar]);
		}
		return sb.toString();
	}
    public static String toHash(String str){
        String SHA256String = "";
        try{
            //java build in hash class
            MessageDigest ourMD = MessageDigest.getInstance("SHA-256");
            //set hash generate seed 
            ourMD.update (str.getBytes());
            //compute hash value
            byte byteData[] = ourMD.digest();
            //since the compute hash is byte, we need to convert to hex version
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                //because the byte data length is 32, and 256 bit convert to 64 hex
                //each value in byteData worth 2 hexdecimal, so we using 0xff as a mask to get the value
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
            SHA256String = sb.toString(); 
        }catch(NoSuchAlgorithmException x){};
        return SHA256String;
    }
}
