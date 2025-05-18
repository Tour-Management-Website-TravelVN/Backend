package ads.user;

import java.util.ArrayList;

import ads.objects.FestivalObject;

public interface Festival {
	public ArrayList<FestivalObject> getAllFestival();
	public ArrayList<FestivalObject> getByName(String search);
	public int countByName(String search);
	ArrayList<FestivalObject> getObjects(FestivalObject similar,int at,byte total);
	public FestivalObject getById(String id);
	public boolean updateFestival(FestivalObject t);
	public boolean deleteById(String id);
}
