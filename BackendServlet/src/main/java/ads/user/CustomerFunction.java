package ads.user;

import java.util.ArrayList;


import ads.objects.Customer;
import ads.objects.TourUnit;

public interface CustomerFunction {
	public ArrayList<Customer> getCustomers(int page);
	public ArrayList<Customer> getByName(String search);
	public int countByName(String search);
	ArrayList<Customer> getObjects(TourUnit similar,int at,byte total);
	public Customer getById(String id);
	public boolean updateCustomer(Customer t);
	public boolean addTourUnit(TourUnit t);
	public boolean deleteById(String id);
	public int getMaxPage();
	public ArrayList<Customer> getFirst100Customers();

}