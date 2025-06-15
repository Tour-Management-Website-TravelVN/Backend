package ads.user;

import java.time.LocalDate;
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
	public boolean isGuide(int guide, LocalDate newStart, LocalDate dateEnd);
	public boolean arrangeTourGuide(int tour_guide_id,String tour_unit_id,int toi);
	public boolean reArrangeTourGuide(int tour_guide_id, String tour_unit_id);
	public boolean checkConflictDate(String tour_unit_id,LocalDate newStart, LocalDate dateEnd);

}
