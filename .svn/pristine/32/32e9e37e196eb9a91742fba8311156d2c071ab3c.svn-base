package pl.kamsoft.nfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playerony.libraryV2.dao.RoleDao;
import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Role;

import pl.kamsoft.nfz.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> selectRoles() throws DatabaseException {
		return roleDao.selectRoles();
	}

	@Override
	public Role getRoleByID(Long id) throws DatabaseException, InputException {
		return roleDao.getRoleByID(id);
	}

}
