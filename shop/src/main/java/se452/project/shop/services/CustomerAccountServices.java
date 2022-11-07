package se452.project.shop.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import se452.project.grocery.Role;
import se452.project.grocery.entities.Account;
import se452.project.grocery.entities.Item;
import se452.project.grocery.repos.ItemRepo;
import se452.project.shop.entities.CustomerAccount;
import se452.project.shop.entities.CustomerItem;
import se452.project.shop.repos.CustomerAccountRepos;
import se452.project.shop.repos.CustomerItemListRepo;

@Log4j2
@Service
public class CustomerAccountServices {

	@Autowired
	CustomerAccountRepos accountRepos;
	
	@Autowired
	CustomerItemListRepo customerItemListRepo;
	
	@Autowired
	ItemRepo itemRepo;
	
	private String salt = "SE 452";
	private char chars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ".toCharArray();

	public int loginAccount(CustomerAccount account) {
		if(account != null) {
			if(accountRepos.findCustomerAccountByUsername(account.getUsername())!=null) {

				CustomerAccount record = accountRepos.findCustomerAccountByUsername(account.getUsername());
				String hashVal = toHash(salt + account.getPassword());
				boolean verified = toHash(hashVal+record.getPassword()).equals(record.getVerified());
				log.info("login "+verified);
				if(verified) return record.getCid();
				return -1;
			}
		}
		return -1;
	}
	
	public boolean createAccount(CustomerAccount account) {
		try{
			if(account != null) {
				if(accountRepos.findCustomerAccountByUsername(account.getUsername())==null) {
					if(account.getUsername()!=null && account.getPassword()!=null) {
						log.info("input account: "+account.getUsername());
						//convert password to hex format via hash function
						String hashVal = toHash(salt + account.getPassword());
						account.setPassword(randomString(16));
						log.info("replace pw: "+account.getPassword());
						account.setVerified(toHash(hashVal+account.getPassword()));
						accountRepos.save(account);
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

	public List<CustomerItem> addItemToCart(String username, Item item) {
		CustomerItem items = new CustomerItem();
		
		CustomerAccount customerAccount = accountRepos.findCustomerAccountByUsername(username);

		
		Item itemss = itemRepo.findItemByName(item.getName());
		itemss.setQuantity(itemss.getQuantity()-1);
		itemRepo.save(itemss);
		
		items.setName(itemss.getName());
		items.setPrice(itemss.getPrice());
		items.setQuantity(itemss.getQuantity());
		customerItemListRepo.save(items);
		
		customerAccount.getShoppingCart().add(items);
		System.out.println(customerAccount.toString());
		System.out.println("-----------------------------------------------------------------------------------");
		if(customerAccount != null) {
			accountRepos.save(customerAccount);
			
			return customerAccount.getShoppingCart();
		}
		return null; 
		
	}
	public CustomerAccount getAccount(int cid) {
		return accountRepos.findCustomerAccountByCid(cid);
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
