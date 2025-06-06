package ads.user;

import java.util.List;

import ads.objects.Discount;

public interface DiscountFunction {
	List<Discount> getDiscounts(int page);
	Discount addDiscount(Discount discount);
	boolean updateDiscount(Discount discount);
	boolean deleteDiscount(int discountId);
	boolean canUpOrDel(int discountId);	
	Discount getById(int id);
	
	int getMaxPage();
}
