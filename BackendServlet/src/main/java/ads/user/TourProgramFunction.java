package ads.user;

import java.util.List;

import ads.objects.TourProgram;

public interface TourProgramFunction {
	List<TourProgram> getTourProgramsByTourId(String tourId);
}
