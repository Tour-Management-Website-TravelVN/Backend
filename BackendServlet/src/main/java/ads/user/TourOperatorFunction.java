package ads.user;

import java.util.ArrayList;

import ads.objects.TourOperator;

public interface TourOperatorFunction {
	public ArrayList<TourOperator> getFirst100TourOperators();
	
	public boolean addTourOperator(TourOperator tourOperator);
	
	public boolean isPhoneNumberExist(String phoneNumber);
	
	public boolean isCitizenIdExist(String citizenId);
	
	public boolean updateTourOperator(TourOperator tourOperator);
	
	public boolean deleteTourOperatorAndAccount(int tourOperatorId);
}
