package webBookShelf.application.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webBookShelf.application.exceptions.ResponseNotFoundException;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.services.bookRequestService.BookRequestService;


@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookItemController {
	private final BookRequestService bookRequestService;

	@RequestMapping(path="/{id}", method= RequestMethod.GET)
	public String showBookItem(@PathVariable(value = "id") String id, Model model) {
		try {
			BookInfoDto bookInfoDto = bookRequestService.requestItem(id);
			model.addAttribute("bookItem", bookInfoDto);
		} catch (ResponseNotFoundException e) {
			e.printStackTrace();
		}
		return "bookItem";
	}
}
