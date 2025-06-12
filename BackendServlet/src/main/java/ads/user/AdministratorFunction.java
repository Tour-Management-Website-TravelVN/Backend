package ads.user;

import ads.objects.Administrator;

public interface AdministratorFunction {
	public Administrator getAdministratorByUsername(String username);
	
	public boolean changeEmail(String email, String username);
	
	public boolean changePassword(String username, String newPassword);
}
