package ads.user;

import java.util.ArrayList;

import ads.objects.TourUnit;

public interface TourUnitFunction {

	public ArrayList<TourUnit> getAllTourUnit(String id,int page);
	public ArrayList<TourUnit> getByName(String search);
	public int countByName(String search);
	ArrayList<TourUnit> getObjects(TourUnit similar,int at,byte total);
	public TourUnit getById(String id);
	public boolean updateTourUnit(TourUnit t);
	public boolean addTourUnit(TourUnit t);
	public boolean deleteById(String id);
	public int getMaxPage();
}
