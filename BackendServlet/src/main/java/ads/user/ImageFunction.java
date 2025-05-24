package ads.user;

import java.util.List;

import ads.objects.Image;

public interface ImageFunction {
	List<Image> getImgsByTourId(String tourId);
	boolean updateImgsByTourId(String tourId, String delImages, List<String> imgUrls, int touId);
}
