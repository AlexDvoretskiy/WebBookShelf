package webBookShelf.application.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webBookShelf.application.exceptions.ResponseNotFoundException;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.persistence.dto.mapping.BookInfoMapper;
import webBookShelf.application.persistence.entities.data.UserBook;
import webBookShelf.application.services.bookRequestService.BookRequestService;
import webBookShelf.application.services.dataServices.BookshelfService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class BookShelfController {
	private final BookshelfService bookshelfService;
	private final BookRequestService bookRequestService;
	private final BookInfoMapper bookInfoMapper;

	@GetMapping("/")
	public String showBookShelf(Model model) {
		List<UserBook> userBooks = bookshelfService.getUserBooks();
		List<BookInfoDto> bookInfoList = bookInfoMapper.map(userBooks);
		model.addAttribute("bookList", bookInfoList);
		return "bookshelf";
	}

	@RequestMapping(path="/add/{request_id}", method= RequestMethod.GET)
	public String addBookToShelf(@PathVariable(value = "request_id") String request_id, Model model) {
		try {
			BookInfoDto bookInfoDto = bookRequestService.requestItem(request_id);
			bookshelfService.saveUserBook(bookInfoDto);
		} catch (ResponseNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:/shelf/";
	}

	@RequestMapping(path="/delete/{id}", method= RequestMethod.GET)
	public String deleteBookFromShelf(@PathVariable(value = "id") Long id) {
		bookshelfService.deleteUserBook(id);
		return "redirect:/shelf/";
	}

	@RequestMapping(path="/notActive/{id}", method= RequestMethod.GET)
	public String setNotActiveBook(@PathVariable(value = "id") Long id) {
		bookshelfService.setNotActive(id);
		return "redirect:/shelf/";
	}
}
