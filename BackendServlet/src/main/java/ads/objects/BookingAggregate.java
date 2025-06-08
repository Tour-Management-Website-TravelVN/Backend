package ads.objects;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookingAggregate implements Serializable {
	private int year;
	private int month;
	private int adultQuants;
	private int childQuants;
	private int toddlerQuants;
	private int babyQuants;
}
