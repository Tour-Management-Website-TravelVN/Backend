package ads.user;

import java.util.ArrayList;
import java.util.List;

import ads.objects.Category;

public interface CategoryFunction {
	List<Category> getCategories();
	
	ArrayList<Category> getAllCategories();
}
