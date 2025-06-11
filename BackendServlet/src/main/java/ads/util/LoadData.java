package ads.util;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class LoadData {
    public static Instances getDataFromDB() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");

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
    public static Instances getCategoryYearlyBookingData() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");

        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL("jdbc:mysql://localhost:3306/tour_booking_system");
        query.setUsername("Unknown");
        query.setPassword("Drsunknown");

        query.setQuery(
            "SELECT " +
            "    AVG(tu.adult_tour_price) AS adult_price, " +
            "    AVG(d.discount_value) AS discount_value, " +
            "    AVG(tu.maximum_capacity) AS max_capacity, " +
            "    AVG(tr.rating_value) AS rating_value, " +
            "    COUNT(b.booking_id) AS total_bookings " +
            "FROM booking b " +
            "JOIN tour_unit tu ON b.tour_unit_id = tu.tour_unit_id " +
            "JOIN tour t ON tu.tour_id = t.tour_id " +
            "JOIN category c ON t.category_id = c.category_id " +
            "LEFT JOIN discount d ON tu.discount_id = d.discount_id " +
            "LEFT JOIN tour_rating tr ON tu.tour_unit_id = tr.tour_unit_id " +
            "GROUP BY c.category_id, YEAR(b.booking_date)"
        );

        Instances data = query.retrieveInstances();
        data.setClassIndex(data.numAttributes() - 1); // total_bookings
        return data;
    }
    public static Instances getInputDataForCurrentYear() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");

        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL("jdbc:mysql://localhost:3306/tour_booking_system");
        query.setUsername("Unknown");
        query.setPassword("Drsunknown");

        query.setQuery(
            "SELECT " +
            "    c.category_name, " +
            "    AVG(tu.adult_tour_price) AS adult_price, " +
            "    AVG(d.discount_value) AS discount_value, " +
            "    AVG(tu.maximum_capacity) AS max_capacity, " +
            "    AVG(tr.rating_value) AS rating_value " +
            "FROM tour_unit tu " +
            "JOIN tour t ON tu.tour_id = t.tour_id " +
            "JOIN category c ON t.category_id = c.category_id " +
            "LEFT JOIN discount d ON tu.discount_id = d.discount_id " +
            "LEFT JOIN tour_rating tr ON tu.tour_unit_id = tr.tour_unit_id " +
            "WHERE YEAR(tu.departure_date) = YEAR(CURDATE()) " + // ⚠ Năm hiện tại
            "GROUP BY c.category_id, c.category_name"
        );

        return query.retrieveInstances();
    }


}
