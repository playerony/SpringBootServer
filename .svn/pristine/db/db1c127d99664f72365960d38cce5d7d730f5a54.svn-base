package pl.kamsoft.nfz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playerony.libraryV2.dao.BookDao;
import com.playerony.libraryV2.exception.DatabaseException;
import com.playerony.libraryV2.exception.InputException;
import com.playerony.libraryV2.model.Book;

import pl.kamsoft.nfz.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	private BookDao bookDao;
	
	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		super();
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> selectBooks() throws DatabaseException {
		return bookDao.selectBooks();
	}

	@Override
	public void saveBook(Book book) throws DatabaseException, InputException {
		bookDao.insertBook(book, null);
	}

	@Override
	public void removeBook(Long id) throws DatabaseException, InputException {
		bookDao.deleteBook(id);
	}

	@Override
	public void updateBook(Long id, Book book) throws DatabaseException, InputException {
		bookDao.updateBook(id, book);
	}

	@Override
	public Book getBookByID(Long id) throws DatabaseException {
		return bookDao.getBookById(id);
	}

}
