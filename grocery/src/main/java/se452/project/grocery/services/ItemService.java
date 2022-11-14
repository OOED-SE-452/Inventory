// package se452.project.grocery.services;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import se452.project.grocery.entities.Item;
// import se452.project.grocery.repos.ItemRepo;
//
// @Service
// public class ItemService {
//
// @Autowired
// ItemRepo itemRepo;
//
// public boolean createItem(Item item) {
//
// Item findItem = itemRepo.findItemByName(item.getName());
//
// if(item != null) {
// if(findItem == null) {
// if(item.getName()!=null) {
// if(item.getPrice()<0.00)
// item.setPrice(0.00);
// if(item.getQuantity()<0)
// item.setQuantity(0);
// itemRepo.save(item);
// return true;
// }
// }
// }
// return false;
// }
//
// public boolean removeItem(Item item){
//
// Item findItem = itemRepo.findItemByName(item.getName());
// System.out.println(findItem);
// if(item != null) {
// itemRepo.delete(findItem);
// return true;
// }
// return false;
// }
//
//
// public Item findItemByName(String name) {
//
// Item findItem = itemRepo.findItemByName(name);
//
// if(findItem!=null) {
// return findItem;
// }
// return null;
// }
//
// public void save(Item item) {
// Item i = itemRepo.findItemByName(item.getName());
// if(i!=null) {
// i.setName(item.getName());
// if(item.getPrice()!=null)
// i.setPrice(item.getPrice());
// if(item.getQuantity()!=null)
// i.setQuantity(item.getQuantity());
// itemRepo.save(i);
// }
// }
//
// public List<Item> findAll(){
//
// return itemRepo.findAll();
//
// }
//
// }
