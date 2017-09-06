package pl.kamsoft.nfz.service;

import java.util.List;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Author;


public interface AuthorService {
	List<Author> selectAuthors() throws DatabaseException;
	
	void saveAuthor(Author author) throws DatabaseException, InputException;
	
	void removeAuthor(Long id) throws DatabaseException, InputException;
	
	void updateAuthor(Long id, Author author) throws DatabaseException, InputException;
	
	Author getAuthorByID(Long id) throws DatabaseException, InputException;
}
