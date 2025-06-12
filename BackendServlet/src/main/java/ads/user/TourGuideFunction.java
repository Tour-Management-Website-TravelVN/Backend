package ads.user;

import java.util.ArrayList;

import ads.objects.TourGuide;

public interface TourGuideFunction {
	public ArrayList<TourGuide> getFirst100TourGuide();
	
	public boolean addTourGuide(TourGuide tourGuide);
	
	public boolean isPhoneNumberExist(String phoneNumber);
	
	public boolean isCitizenIdExist(String citizenId);
	
	public boolean isCardIdExist(String cardId);
	
	public boolean updateTourGuide(TourGuide tourGuide);
	
	public boolean deleteTourGuideAndAccount(int tourGuideId);
}
