package ads.util;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

import java.math.BigDecimal;
import java.util.Set;

import ads.objects.Discount;
import ads.objects.TourUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.DenseInstance;

@Getter
@Setter
@Builder
@ToString
public class AmountOfCustomerPredictor {

	    private static LinearRegression model;
	    private static Instances dataset;
	    
	    public static AmountOfCustomerPredictor getInstance()
	    {
	    	return new AmountOfCustomerPredictor();
	    }
	    public static void trainModel() throws Exception {
	    	dataset = LoadData.getDataFromDB(); // Lưu lại dataset để dùng cho predict
	        dataset.setClassIndex(3); // Giả sử 'available_capacity' ở index 3

	        model = new LinearRegression(); // <-- Quan trọng: KHỞI TẠO model
	        model.buildClassifier(dataset);


	        model.buildClassifier(dataset);
	        System.out.println(dataset);
	    }

	    public static double predict(
	    	    double adultPrice,
	    	    double ratingValue,
	    	    double discountValue,
	    	    double privateRoomPrice,
	    	    double childPrice,
	    	    double maxCapacity
	    	) throws Exception {
	    	    Instance inst = new DenseInstance(7); 
	    	    inst.setDataset(dataset);

	    	    inst.setValue(0, adultPrice);
	    	    inst.setValue(1, ratingValue);
	    	    inst.setValue(2, discountValue);
	    	    inst.setValue(3, privateRoomPrice);
	    	    inst.setValue(4, childPrice);
	    	    inst.setValue(5, maxCapacity);

	    	    return maxCapacity -  model.classifyInstance(inst); 
	    	}

	

}
