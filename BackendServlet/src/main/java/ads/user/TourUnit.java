package ads.user;

import java.util.ArrayList;

import ads.objects.TourUnitObject;

public interface TourUnit {

	public ArrayList<TourUnitObject> getAllTourUnit();
	public ArrayList<TourUnitObject> getByName(String search);
	public int countByName(String search);
	ArrayList<TourUnitObject> getObjects(TourUnitObject similar,int at,byte total);
	public TourUnitObject getById(String id);
	public boolean updateTourUnit(TourUnitObject t);
	public boolean addTourUnit(TourUnitObject t);
	public boolean deleteById(String id);
}
