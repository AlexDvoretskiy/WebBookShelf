package webBookShelf.application.config.handlers;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import webBookShelf.application.persistence.entities.data.User;
import webBookShelf.application.services.dataServices.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String userName = authentication.getName();

		User user = userService.findByUserName(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		response.sendRedirect(request.getContextPath() + "/");
	}
}
