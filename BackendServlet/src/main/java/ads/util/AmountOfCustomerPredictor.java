package ads.util;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import weka.core.Attribute;
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

	        
	    }
	    public static LinearRegression trainModel(Instances trainingData) throws Exception {
	        trainingData.setClassIndex(trainingData.numAttributes() - 1); // total_bookings
	        LinearRegression model = new LinearRegression();
	        model.buildClassifier(trainingData);
	        return model;
	    }
	    
	    public static ArrayList<Map<String, Double>> predictNextYearBookings(LinearRegression model, Instances inputData) throws Exception {
	        ArrayList<Map<String, Double>> results = new ArrayList<>();

	        // Tạo cấu trúc giống dữ liệu huấn luyện (không có tên category)
	        ArrayList<Attribute> attributes = new ArrayList<>();
	        attributes.add(new Attribute("adult_price"));
	        attributes.add(new Attribute("discount_value"));
	        attributes.add(new Attribute("max_capacity"));
	        attributes.add(new Attribute("rating_value"));
	        attributes.add(new Attribute("total_bookings")); // class attribute

	        Instances predictionStructure = new Instances("prediction", attributes, 0);
	        predictionStructure.setClassIndex(predictionStructure.numAttributes() - 1);

	        for (int i = 0; i < inputData.numInstances(); i++) {
	            Instance row = inputData.instance(i);
	            String category = row.stringValue(0); // category_name

	            Instance inst = new DenseInstance(predictionStructure.numAttributes());
	            inst.setDataset(predictionStructure);

	            // Gán các giá trị từ input
	            for (int j = 1; j < row.numAttributes(); j++) {
	                inst.setValue(j - 1, row.value(j));
	            }

	            double predictedBookings = model.classifyInstance(inst);
	            Map<String, Double> result = new HashMap<>();
	            result.put(category, predictedBookings);
	            results.add(result);
	        }

	        return results;
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
