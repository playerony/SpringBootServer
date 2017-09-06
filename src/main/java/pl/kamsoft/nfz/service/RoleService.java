package pl.kamsoft.nfz.service;

import java.util.List;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Role;

public interface RoleService {
	List<Role> selectRoles() throws DatabaseException;
	
	Role getRoleByID(Long id) throws DatabaseException, InputException;
}
