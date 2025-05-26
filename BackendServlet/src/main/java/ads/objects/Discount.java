package ads.objects;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Discount {
    private Integer id;

    private String discountName;

    private BigDecimal discountValue;

    private String discountUnit;

    private Set<TourUnit> tourUnitSet = new HashSet<>();
    
    public String getValue() {
    	discountValue = discountValue.setScale(2, RoundingMode.HALF_UP);
    	return switch (discountUnit){
    	case "%" -> discountValue+discountUnit;
    	case "VND" -> discountValue+" "+discountUnit;
    	default -> "";
    	};
    }
}