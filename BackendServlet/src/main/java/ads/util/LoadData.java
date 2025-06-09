package ads.util;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class LoadData {
    public static Instances getDataFromDB() throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL("jdbc:mysql://localhost:3306/tour_booking_system");
        query.setUsername("Unknown");
        query.setPassword("Drsunknown");

        query.setQuery(
        	    "SELECT adult_tour_price, rating_value, discount_value, available_capacity, " +
        	    "private_room_price, child_tour_price, maximum_capacity " +
        	    "FROM tour_unit " +
        	    "INNER JOIN tour_rating ON tour_unit.tour_unit_id = tour_rating.tour_unit_id " +
        	    "INNER JOIN discount ON tour_unit.discount_id = discount.discount_id"
        	);


        Instances data = query.retrieveInstances();
        
        data.setClassIndex(data.numAttributes() - 1);
        
        return data;
    }
}
