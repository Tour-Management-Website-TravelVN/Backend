package ads.objects;

import lombok.*;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DiscountObject {
	  private Integer id;

	    private String discountName;

	    private BigDecimal discountValue;

	    private String discountUnit;

	    private Set<TourUnitObject> tourUnitSet = new HashSet<>();
}