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
public class Items {
	private String id;
	private VolumeInfo volumeInfo = new VolumeInfo();
	private AccessInfo accessInfo = new AccessInfo();

	@Override
	public String toString() {
		return "id:" + id + "\n" + volumeInfo.toString() + "\n" +
		       "Ссылка на фрагмент: " + accessInfo.toString();
	}
}
