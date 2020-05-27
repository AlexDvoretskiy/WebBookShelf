package webBookShelf.application.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webBookShelf.application.exceptions.ResponseNotFoundException;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.persistence.dto.SearchRequestDto;
import webBookShelf.application.services.bookRequestService.BookRequestService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BookSearchController {
	private final BookRequestService bookRequestService;

	@GetMapping("/")
	public String showIndex(Model model) {
		model.addAttribute("searchRequestDto", new SearchRequestDto());
		model.addAttribute("showInfoPanel", true);
		return "index";
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public String searchItems(Model model, SearchRequestDto searchRequestDto) {
		try {
			List<BookInfoDto> bookList = bookRequestService.requestQuery(searchRequestDto.getRequest());
			model.addAttribute("bookList", bookList);
			model.addAttribute("searchRequestDto", new SearchRequestDto());
			model.addAttribute("showInfoPanel", false);
			model.addAttribute("showWarningMsg", bookList.isEmpty());
		} catch (ResponseNotFoundException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
