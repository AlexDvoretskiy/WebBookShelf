package webBookShelf.application.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webBookShelf.application.persistence.entities.data.Book;
import webBookShelf.application.services.dataServices.BookshelfService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class BookShelfController {
	private final BookshelfService bookshelfService;

	@GetMapping("/")
	public String showBookShelf(Model model) {
		return "bookshelf";
	}

	@RequestMapping(path="/add/{id}", method= RequestMethod.GET)
	public String addBookToShelf(@PathVariable(value = "id") String id, Model model) {

		return "bookshelf";
	}

	@GetMapping("/getAll")
	public String getAllUserBooks() {
		List<Book> books = bookshelfService.getUserBooks();
		return "bookshelf";
	}
}
