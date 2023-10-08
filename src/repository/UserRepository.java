
package repository;

import dataacess.UserDAO;
/**
 *
 * @author Administrator
 */
public class UserRepository implements IUserRepository{

    @Override
    public void createNewAccount() {
        UserDAO.Instance().createNewAccount();
    }

    @Override
    public void loginSystem() {
       UserDAO.Instance().loginSystem();
    }
    
}