package webBookShelf.application.persistence.dto.mapping;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import webBookShelf.application.persistence.dto.BookInfoDto;
import webBookShelf.application.services.bookRequestService.pojo.BookResponse;
import webBookShelf.application.services.bookRequestService.pojo.Items;

import java.util.ArrayList;
import java.util.List;


@Component
@NoArgsConstructor
public class BookInfoMapper {

	public List<BookInfoDto> map(BookResponse bookResponse) {
		List<BookInfoDto> bookDtos = new ArrayList<>();
		List<Items> itemsList = bookResponse.getItems();

		for (Items items : itemsList) {
			BookInfoDto bookInfoDto = mapItems(items);

			if (!bookInfoDto.isEmpty()) {
				bookDtos.add(bookInfoDto);
			}
		}
		return bookDtos;
	}

	public BookInfoDto map (Items items) {
		return mapItems(items);
	}

	private BookInfoDto mapItems (Items items) {
		return BookInfoDto.builder()
				.id(items.getId())
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
}
