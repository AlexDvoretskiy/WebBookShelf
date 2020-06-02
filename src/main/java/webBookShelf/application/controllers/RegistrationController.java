package webBookShelf.application.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webBookShelf.application.persistence.entities.SystemUser;
import webBookShelf.application.persistence.entities.data.User;
import webBookShelf.application.services.dataServices.interfaces.UserService;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/register")
public class RegistrationController {
	private final UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/")
	public String showRegisterForm(Model model) {
		model.addAttribute("systemUser", new SystemUser());
		return "registerForm";
	}

	@PostMapping("/")
	public String registerUser(@Valid @ModelAttribute("systemUser") SystemUser systemUser, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "registerForm";
		}

		System.out.println("name:" + systemUser.getUserName() + " email: " + systemUser.getEmail() + " pass: " + systemUser.getPassword());
		User existingUser = userService.findByUserName(systemUser.getUserName());
		if (existingUser != null) {
			model.addAttribute("error", true);
			return "registerForm";
		}
		userService.save(systemUser);
		return "registerSuccess";
	}
}
