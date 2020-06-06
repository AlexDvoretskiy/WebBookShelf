package webBookShelf.application.services.dataServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.persistence.entities.data.*;
import webBookShelf.application.persistence.repositories.BookshelfRepository;
import webBookShelf.application.services.AuthService;
import webBookShelf.application.services.dataServices.interfaces.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookshelfService {
	private BookshelfRepository bookshelfRepository;
	private UserService userService;
	private AuthService authService;

	public List<Book> getUserBooks() {
		List<Book> books = new ArrayList<>();
		List<UserBook> userBooks = bookshelfRepository.getAllByUserName(authService.getCurrentUsername());

		for (UserBook userBook : userBooks) {
			books.add(userBook.getBook());
		}
		return books;
	}

	@Transactional
	public void saveUserBook(BookInfoDto bookInfoDto) {
		User currentUser = userService.getCurrentUser();
		Book book = getBookFromDto(bookInfoDto);

		UserBook userBook = new UserBook(currentUser);
		userBook.setBook(book);

 		bookshelfRepository.save(userBook);
	}

	private Book getBookFromDto(BookInfoDto bookInfoDto) {
		Book book = Book.builder()
				.title(bookInfoDto.getTitle())
				.description(bookInfoDto.getDescription())
				.language(bookInfoDto.getLanguage())
				.imageLink(bookInfoDto.getImageLink())
				.webReaderLink(bookInfoDto.getWebReaderLink())
		.build();

		setAuthorsFromDto(bookInfoDto, book);
		setCategoriesFromDto(bookInfoDto, book);

		return book;
	}

	private void setAuthorsFromDto(BookInfoDto bookInfoDto, Book book) {
		List<Author> authors = new ArrayList<>();

		for (String authorName : bookInfoDto.getAuthors()) {
			authors.add(new Author(authorName, book));
		}
		book.setAuthors(authors);
	}

	private void setCategoriesFromDto(BookInfoDto bookInfoDto, Book book) {
		List<Category> categories = new ArrayList<>();

		for(String categoryName : bookInfoDto.getCategories()) {
			categories.add(new Category(categoryName, book));
		}
		book.setCategories(categories);
 	}

	@Autowired
	public void setBookshelfRepository(BookshelfRepository bookshelfRepository) {
		this.bookshelfRepository = bookshelfRepository;
	}

	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
