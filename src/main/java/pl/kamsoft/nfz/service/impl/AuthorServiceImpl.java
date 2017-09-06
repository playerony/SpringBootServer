package pl.kamsoft.nfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playerony.libraryV2.dao.AuthorDao;
import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Author;

import pl.kamsoft.nfz.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	private AuthorDao authorDao;
	
	@Autowired
	public AuthorServiceImpl(AuthorDao authorDao) {
		super();
		this.authorDao = authorDao;
	}

	@Override
	public List<Author> selectAuthors() throws DatabaseException {
		return authorDao.selectAuthors();
	}

	@Override
	public void saveAuthor(Author author) throws DatabaseException, InputException {
		if(authorDao.getAuthorById(author.getId()) == null)
			authorDao.insertAuthor(author, null);
		else
			authorDao.updateAuthor(author.getId(), author);
	}

	@Override
	public void removeAuthor(Long id) throws DatabaseException, InputException {
		authorDao.deleteAuthor(id);
	}

	@Override
	public void updateAuthor(Long id, Author author) throws DatabaseException, InputException {
		authorDao.updateAuthor(id, author);
	}

	@Override
	public Author getAuthorByID(Long id) throws DatabaseException, InputException {
		return authorDao.getAuthorById(id);
	}
}
