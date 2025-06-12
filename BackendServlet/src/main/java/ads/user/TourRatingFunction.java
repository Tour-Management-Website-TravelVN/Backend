package ads.user;

import java.util.ArrayList;

import ads.objects.TourRating;

public interface TourRatingFunction {
	public ArrayList<TourRating> getFirst100TourRatings();
	
	public boolean setStatus(int tourRatingId, int administrator, String status);
	
	public ArrayList<TourRating> getTourRatingsByStatus(String status);
}
