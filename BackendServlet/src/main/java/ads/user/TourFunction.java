package ads.user;

import java.util.List;

import ads.objects.Tour;
import ads.objects.TourProgram;

public interface TourFunction {
	List<Tour> getToursList(short page, String keyword);
	int getMaxToursPage(String keyword);
	Tour getTourByTourId(String tourId);
	int countTourUnit(String tourId);
	boolean deleteTourByTourId(String tourId);
	String addTour(Tour tour, List<String> imgUrls, List<TourProgram> programs, int categoryId, int tocId);
	String getTourNameByTourId(String tourId);
	List<Tour> getAllTourNameAndId();
	boolean updateTour(Tour tour, String delImages, List<String> imgUrls, List<TourProgram> programs, int categoryId, int touId);
}
