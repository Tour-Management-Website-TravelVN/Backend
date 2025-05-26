package ads.user;

import java.util.List;

import ads.objects.Festival;


public interface FestivalFunction {
	List<Festival> getFestivals(int page);
	boolean addFestival(Festival festival);
	boolean updateFestival(Festival festival);
	boolean deleteFestival(int festivalId);
	
	boolean canDel(int festivalId);
	boolean updateVisible(int festivalId, boolean isShow);
	
	int getMaxPage();
}
