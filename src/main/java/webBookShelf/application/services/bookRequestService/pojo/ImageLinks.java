package webBookShelf.application.services.bookRequestService.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageLinks {
	private String smallThumbnail;
	private String thumbnail;

	@Override
	public String toString() {
		return "smallThumbnail: " + smallThumbnail + "\n" +
		       "thumbnail: " + thumbnail;
	}
}
