package pl.kamsoft.nfz.service;

import java.util.List;

import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Book;

public interface BookService {
	List<Book> selectBooks() throws DatabaseException;
	
	void saveBook(Book book) throws DatabaseException, InputException;
	
	void removeBook(Long id) throws DatabaseException, InputException;
	
	void updateBook(Long id, Book book) throws DatabaseException, InputException;
	
	Book getBookByID(Long id) throws DatabaseException;
}
