package ads.user;

import java.util.ArrayList;

import ads.objects.DiscountObject;

public interface Discount {

	public ArrayList<DiscountObject> getAllDiscount();
	public ArrayList<DiscountObject> getByName(String search);
	public int countByName(String search);
	ArrayList<DiscountObject> getObjects(DiscountObject similar,int at,byte total);
	public DiscountObject getById(String id);
	public boolean updateDiscount(DiscountObject t);
	public boolean addDiscount(DiscountObject t);
	public boolean deleteById(String id);
}
