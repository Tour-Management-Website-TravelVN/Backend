package ads.user;

import java.util.ArrayList;

import ads.objects.TourOperator;


public interface TourOperatorFunction {

	public ArrayList<TourOperator> getAllTourUnit();
	public ArrayList<TourOperator> getByName(String search);
	public int countByName(String search);
	ArrayList<TourOperator> getObjects(TourOperator similar,int at,byte total);
	public TourOperator getById(String id);
	//public boolean updateTourOperator(TourOperator t);
	//public boolean addTourOperator(TourOperator t);
	public boolean deleteById(String id);

	public ArrayList<TourOperator> getFirst100TourOperators();
	
	public boolean addTourOperator(TourOperator tourOperator);
	
	public boolean isPhoneNumberExist(String phoneNumber);
	
	public boolean isCitizenIdExist(String citizenId);
	
	public boolean updateTourOperator(TourOperator tourOperator);
	
	public boolean deleteTourOperatorAndAccount(int tourOperatorId);
}
