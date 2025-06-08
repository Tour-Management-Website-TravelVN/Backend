package ads.user;

import java.util.List;

import ads.objects.BookingAggregate;

public interface StatisticFunction {
	List<BookingAggregate> getStackedBarChartData();
}
