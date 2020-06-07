package webBookShelf.application.persistence.dto;


import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;


@Data
@Builder
public class BookInfoDto {
	private String request_id;
	private Long id;
	private String title;
	private String description;
	private String maturityRating;
	private String language;
	private List<String> authors;
	private List<String> categories;
	private String imageLink;
	private String webReaderLink;
	private String group;
	private Date addDate;
	private Date closeDate;
	private boolean isActive;

	public boolean isEmpty() {
		return StringUtils.isEmpty(request_id) ||
		       StringUtils.isEmpty(title) ||
		       StringUtils.isEmpty(description) ||
		       authors.isEmpty() ||
		       categories.isEmpty();
	}
}
