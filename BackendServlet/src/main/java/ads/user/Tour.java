package ads.user;

import java.util.ArrayList;

import ads.objects.TourObject;
import ads.objects.TourUnitObject;

public interface Tour {
	public ArrayList<TourObject> getAllTour();
	public ArrayList<TourObject> getByName(String search);
	public int countByName(String search);
	ArrayList<TourUnitObject> getObjects(TourObject similar,int at,byte total);
	public TourObject getById(String id);
	public boolean updateTourUnit(TourObject t);
	public boolean addTourUnit(TourObject t);
	public boolean deleteById(String id);
}
