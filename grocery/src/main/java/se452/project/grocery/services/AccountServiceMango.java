package se452.project.grocery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import se452.project.grocery.Role;
import se452.project.grocery.entities.AccountMango;
import se452.project.grocery.repos.AccountMangoRepo;

@Log4j2
@Service
public class AccountServiceMango {
	
	@Autowired
	AccountMangoRepo accountRepo;

	private String salt = "SE 452";
	// private char chars[] = "0123456789abcdef".toCharArray();
	private char chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ".toCharArray();

	public boolean createAccount(AccountMango account) {
		try{
			if(account != null) {
				if(accountRepo.findAccountMangoByEmail(account.getEmail())==null) {
					if(account.getEmail()!=null && account.getPassword()!=null) {
						log.info("input account: "+account.getEmail());
						//if(account.getRole()==null) account.setRole(Role.USER);
						account.setRole(Role.USER);
						//convert password to hex format via hash function
						String hashVal = toHash(salt + account.getPassword());
						account.setPassword(randomString(16));
						log.info("replace pw: "+account.getPassword());
						account.setVerified(toHash(hashVal+account.getPassword()));

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
	/**
	 * @param input account
	 * @return -1 if account password incorrect, other wise will return account uid
	 */
	public String loginAccount(AccountMango input) {
		if(input != null) {
			if(accountRepo.findAccountMangoByEmail(input.getEmail())!=null) {

				AccountMango record = accountRepo.findAccountMangoByEmail(input.getEmail());
				String hashVal = toHash(salt + input.getPassword());
				boolean verified = toHash(hashVal+record.getPassword()).equals(record.getVerified());
				log.info("login "+verified);
				if(verified) return record.getUid();
				return "";
			}
		}
		return "";
	}
	
	public Role getRole(AccountMango account) {
		if(account != null) {
			if(accountRepo.findAccountMangoByEmail(account.getEmail())!=null) {
				//System.out.println("Account pass: " + account.getPassword());
				if(accountRepo.findAccountMangoByEmail(account.getEmail()).getRole().equals(Role.ADMIN)) {
					return Role.ADMIN;
				}
				
				else if (accountRepo.findAccountMangoByEmail(account.getEmail()).getRole().equals(Role.USER)) {
					return Role.USER;
				}
			}
		}
		return null;
	}
	public AccountMango getAccount(String uid) {
		return accountRepo.findAccountMangoByUid(uid);
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
