package pl.kamsoft.nfz.service;

import java.util.List;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.User;

public interface UserService {
	List<User> selectUsers() throws DatabaseException;
	
	void saveUser(User user) throws DatabaseException, InputException;
	
	void deleteUser(Long id) throws DatabaseException, InputException;
}
