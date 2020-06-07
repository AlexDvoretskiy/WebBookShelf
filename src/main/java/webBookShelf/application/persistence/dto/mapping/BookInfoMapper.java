package webBookShelf.application.persistence.dto.mapping;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.persistence.entities.data.Author;
import webBookShelf.application.persistence.entities.data.Category;
import webBookShelf.application.persistence.entities.data.UserBook;
import webBookShelf.application.services.bookRequestService.pojo.BookResponse;
import webBookShelf.application.services.bookRequestService.pojo.Items;

import java.util.ArrayList;
import java.util.List;


@Component
@NoArgsConstructor
public class BookInfoMapper {

	public List<BookInfoDto> map(BookResponse bookResponse) {
		List<BookInfoDto> bookInfoList = new ArrayList<>();
		List<Items> itemsList = bookResponse.getItems();

		for (Items items : itemsList) {
			BookInfoDto bookInfoDto = mapItems(items);

			if (!bookInfoDto.isEmpty()) {
				bookInfoList.add(bookInfoDto);
			}
		}
		return bookInfoList;
	}

	public BookInfoDto map (Items items) {
		return mapItems(items);
	}

	private BookInfoDto mapItems (Items items) {
		return BookInfoDto.builder()
				.request_id(items.getId())
				.title(items.getVolumeInfo().getTitle())
				.description(items.getVolumeInfo().getDescription())
				.maturityRating(items.getVolumeInfo().getMaturityRating())
				.language(items.getVolumeInfo().getLanguage())
				.authors(items.getVolumeInfo().getAuthors())
				.categories(items.getVolumeInfo().getCategories())
				.imageLink(items.getVolumeInfo().getImageLinks().getSmallThumbnail())
				.webReaderLink(items.getAccessInfo().getWebReaderLink())
		.build();
	}

	public List<BookInfoDto> map(List<UserBook> userBooks) {
		List<BookInfoDto> bookInfoList = new ArrayList<>();

		for (UserBook userBook : userBooks) {
			bookInfoList.add(map(userBook));
		}
		return bookInfoList;
	}

	public BookInfoDto map(UserBook userBook) {
		BookInfoDto bookInfoDto = BookInfoDto.builder()
				.id(userBook.getId())
				.title(userBook.getBook().getTitle())
				.description(userBook.getBook().getDescription())
				.maturityRating(userBook.getBook().getMaturityRating())
				.language(userBook.getBook().getLanguage())
				.imageLink(userBook.getBook().getImageLink())
				.webReaderLink(userBook.getBook().getWebReaderLink())
				.group(userBook.getGroup())
				.addDate(userBook.getAddDate())
				.closeDate(userBook.getCloseDate())
				.isActive(userBook.isActive())
		.build();

		bookInfoDto.setAuthors(getAuthorNamesFromUserBook(userBook));
		bookInfoDto.setCategories(getCategoryNamesFromUserBook(userBook));

		return bookInfoDto;
	}

	private List<String> getAuthorNamesFromUserBook(UserBook userBook) {
		List<String> authorNames = new ArrayList<>();
		List<Author> authors = userBook.getBook().getAuthors();

		for (Author author : authors) {
			authorNames.add(author.getName());
		}
		return authorNames;
	}

	private List<String> getCategoryNamesFromUserBook(UserBook userBook) {
		List<String> categoryNames = new ArrayList<>();
		List<Category> categories = userBook.getBook().getCategories();

		for (Category category : categories) {
			categoryNames.add(category.getName());
		}
		return categoryNames;
	}
}
