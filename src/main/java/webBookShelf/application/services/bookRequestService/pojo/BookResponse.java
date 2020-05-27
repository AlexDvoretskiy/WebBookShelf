package webBookShelf.application.services.bookRequestService.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {
	private String totalItems;
	private List<Items> items = new ArrayList<>();

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("totalItems: ").append(totalItems).append("\n");

		for (Items item : items) {
			stringBuilder.append(item.toString()).append("\n\n");
		}

		return stringBuilder.toString();
	}
//
//	@Data
//	@NoArgsConstructor
//	@JsonIgnoreProperties(ignoreUnknown = true)
//	static public class Items {
//		private VolumeInfo volumeInfo = new VolumeInfo();
//		private AccessInfo accessInfo = new AccessInfo();
//
//		@Override
//		public String toString() {
//			return volumeInfo.toString() + "\n" +
//			       "Ссылка на фрагмент: " + accessInfo.toString();
//		}
//	}
//
//	@Data
//	@NoArgsConstructor
//	@JsonIgnoreProperties(ignoreUnknown = true)
//	static public class VolumeInfo {
//		private String title;
//		private String description;
//		private String maturityRating;
//		private String language;
//		private List<String> authors = new ArrayList<>();
//		private List<String> categories = new ArrayList<>();
//		private ImageLinks imageLinks = new ImageLinks();
//
//		@Override
//		public String toString() {
//			StringBuilder stringBuilder = new StringBuilder();
//			stringBuilder.append("title: ").append(title).append("\n")
//					.append("description: ").append(description).append("\n")
//					.append("maturityRating: ").append(maturityRating).append("\n")
//					.append("language: ").append(language).append("\n");
//
//			stringBuilder.append("Авторы: ");
//			for (String author : authors) {
//				stringBuilder.append(author);
//			}
//			stringBuilder.append("\n");
//
//			stringBuilder.append("Категория: ");
//			for (String category : categories) {
//				stringBuilder.append(category);
//			}
//			stringBuilder.append("\n");
//
//			stringBuilder.append("Ссылки на изображения: \n");
//			if (imageLinks != null) {
//				stringBuilder.append(imageLinks.toString());
//			}
//
//			return stringBuilder.toString();
//		}
//	}
//
//	@Data
//	@NoArgsConstructor
//	@JsonIgnoreProperties(ignoreUnknown = true)
//	static public class ImageLinks {
//		private String smallThumbnail;
//		private String thumbnail;
//
//		@Override
//		public String toString() {
//			return "smallThumbnail: " + smallThumbnail + "\n" +
//			       "thumbnail: " + thumbnail;
//		}
//	}
//
//	@Data
//	@NoArgsConstructor
//	@JsonIgnoreProperties(ignoreUnknown = true)
//	static public class AccessInfo {
//		private String webReaderLink;
//
//		@Override
//		public String toString() {
//			return "webReaderLink: " + webReaderLink;
//		}
//	}
}



