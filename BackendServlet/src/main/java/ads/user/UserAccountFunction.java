package ads.user;

import java.util.ArrayList;

import ads.objects.Administrator;
import ads.objects.UserAccount;

public interface UserAccountFunction {
	public UserAccount getUserAccount(String username, String password);
	
	public ArrayList<UserAccount> getFirst100UserAccounts();
	
	public boolean addUserAccount(UserAccount userAccount);
	
	public boolean toggleAccountStatus(String username);
	
	public boolean updateUserAccount(String username, String password, String email);
	
	public boolean deleteUserAccount(String username);
	
	public boolean isUsernameExist(String username);
	
	public boolean isEmailExist(String email);
	
	public boolean isAdministratorIdExist(int id);
	
	public boolean isCustomerIdExist(int id);
	
	public boolean isTourOperatorIdExist(int id);
	
	public boolean isTourGuideIdExist(int id);
	
	// Đặt status là DELETE
	public boolean softDeleteAccount(String username);
	
	public boolean deleteAllSoftDeleteAccounts();
	
	public ArrayList<UserAccount> getRecentDelelteAccounts();
	
	// Đặt lại status là OFF
	public boolean restoreAccount(String username);
	
	public boolean restoreAllAccounts();
}
