package ads.user;

import java.util.List;

import ads.objects.Tour;

public interface TourFunction {
	List<Tour> getToursList(short page, String keyword);
	int getMaxToursPage(String keyword);
	Tour getTourByTourId(String tourId);
	int countTourUnit(String tourId);
	boolean deleteTourByTourId(String tourId);
}
