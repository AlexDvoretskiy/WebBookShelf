package webBookShelf.application.services.dataServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webBookShelf.application.persistence.entities.data.Book;
import webBookShelf.application.persistence.entities.data.UserBook;
import webBookShelf.application.persistence.repositories.BookshelfRepository;
import webBookShelf.application.services.AuthService;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookshelfService {
	private BookshelfRepository bookshelfRepository;
	private AuthService authService;

	public List<Book> getUserBooks() {
		List<Book> books = new ArrayList<>();
		List<UserBook> userBooks = bookshelfRepository.getAllByUserName(authService.getCurrentUsername());

		for (UserBook userBook : userBooks) {
			books.add(userBook.getBook());
		}
		return books;
	}

	@Autowired
	public void setBookshelfRepository(BookshelfRepository bookshelfRepository) {
		this.bookshelfRepository = bookshelfRepository;
	}

	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
}
