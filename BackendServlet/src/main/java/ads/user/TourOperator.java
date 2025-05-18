package ads.user;

import java.util.ArrayList;

import ads.objects.TourOperatorObject;
import ads.objects.TourUnitObject;

public interface TourOperator {

	public ArrayList<TourOperatorObject> getAllTourUnit();
	public ArrayList<TourOperatorObject> getByName(String search);
	public int countByName(String search);
	ArrayList<TourOperatorObject> getObjects(TourOperatorObject similar,int at,byte total);
	public TourOperatorObject getById(String id);
	public boolean updateTourOperator(TourOperatorObject t);
	public boolean addTourOperator(TourOperatorObject t);
	public boolean deleteById(String id);
}
