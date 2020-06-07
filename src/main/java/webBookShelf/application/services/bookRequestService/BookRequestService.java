package webBookShelf.application.services.bookRequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import webBookShelf.application.exceptions.ResponseNotFoundException;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.persistence.dto.mapping.BookInfoMapper;
import webBookShelf.application.services.bookRequestService.pojo.BookResponse;
import webBookShelf.application.services.bookRequestService.pojo.Items;

import java.util.List;


@Component
@RequiredArgsConstructor
public class BookRequestService {
	//TODO: вынести в пропертис
	private static String REQUEST_QUERY_URL = "https://www.googleapis.com/books/v1/volumes?q=%s";
	private static String REQUEST_ITEM_URL = "https://www.googleapis.com/books/v1/volumes/%s";

	private final BookInfoMapper bookInfoMapper;

	public List<BookInfoDto> requestQuery(String query) throws ResponseNotFoundException {
		String urlRequest = String.format(REQUEST_QUERY_URL, query);

		RestTemplate restTemplate = new RestTemplate();
		BookResponse bookResponse = restTemplate.getForObject(urlRequest, BookResponse.class);

		if (bookResponse == null) {
			throw new ResponseNotFoundException("Response is null");
		}
		return bookInfoMapper.map(bookResponse);
	}

	public BookInfoDto requestItem(String request_id) throws ResponseNotFoundException {
		String urlRequest = String.format(REQUEST_ITEM_URL, request_id);

		RestTemplate restTemplate = new RestTemplate();
		Items items = restTemplate.getForObject(urlRequest, Items.class);

		if (items == null) {
			throw new ResponseNotFoundException("Response is null");
		}
		return bookInfoMapper.map(items);
	}
}
