package pl.kamsoft.nfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.playerony.libraryV2.dao.UserDao;
import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.User;

import pl.kamsoft.nfz.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public List<User> selectUsers() throws DatabaseException {
		return userDao.selectUsers();
	}

	@Override
	public void saveUser(User user) throws DatabaseException, InputException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		userDao.insertUser(user);
	}

	@Override
	public void deleteUser(Long id) throws DatabaseException, InputException {
		userDao.deleteUser(id);
	}

}
