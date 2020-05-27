package webBookShelf.application.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/shelf")
public class BookShelfController {

	@GetMapping("/")
	public String showBookShelf(Model model) {
		return "bookshelf";
	}
}
