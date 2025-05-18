package ads.user;

import java.util.ArrayList;

import ads.objects.CategoryObject;


public interface Category {
	public ArrayList<CategoryObject> getAllCategory();
	public ArrayList<CategoryObject> getByName(String search);
	public int countByName(String search);
	ArrayList<CategoryObject> getObjects(CategoryObject similar,int at,byte total);
	public CategoryObject getById(String id);
	public boolean updateCategory(CategoryObject t);
	public boolean addTourUnit(CategoryObject t);
	public boolean deleteById(String id);
}
