package ads.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import ads.objects.Tour;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Generator {
	private static Map<String, String> hashMap;
	static {
		hashMap = new HashMap<>();
        hashMap.put("Thành phố Hà Nội", "001");
        hashMap.put("Hà Giang", "002");
        hashMap.put("Cao Bằng", "004");
        hashMap.put("Bắc Kạn", "006");
        hashMap.put("Tuyên Quang", "008");
        hashMap.put("Lào Cai", "110");
        hashMap.put("Điện Biên", "111");
        hashMap.put("Lai Châu", "112");
        hashMap.put("Sơn La", "114");
        hashMap.put("Yên Bái", "115");
        hashMap.put("Hòa Bình", "117");
        hashMap.put("Thái Nguyên", "119");
        hashMap.put("Lạng Sơn", "220");
        hashMap.put("Quảng Ninh", "222");
        hashMap.put("Bắc Giang", "224");
        hashMap.put("Phú Thọ", "225");
        hashMap.put("Vĩnh Phúc", "226");
        hashMap.put("Bắc Ninh", "227");
        hashMap.put("Hải Dương", "330");
        hashMap.put("Thành phố Hải Phòng", "331");
        hashMap.put("Hưng Yên", "333");
        hashMap.put("Thái Bình", "334");
        hashMap.put("Hà Nam", "335");
        hashMap.put("Nam Định", "336");
        hashMap.put("Ninh Bình", "337");
        hashMap.put("Thanh Hóa", "338");
        hashMap.put("Nghệ An", "440");
        hashMap.put("Hà Tĩnh", "442");
        hashMap.put("Quảng Bình", "444");
        hashMap.put("Quảng Trị", "445");
        hashMap.put("Thừa Thiên-Huế", "446");
        hashMap.put("Thành phố Đà Nẵng", "448");
        hashMap.put("Quảng Nam", "449");
        hashMap.put("Quảng Ngãi", "551");
        hashMap.put("Bình Định", "552");
        hashMap.put("Phú Yên", "554");
        hashMap.put("Khánh Hòa", "556");
        hashMap.put("Ninh Thuận", "558");
        hashMap.put("Bình Thuận", "660");
        hashMap.put("Kon Tum", "662");
        hashMap.put("Gia Lai", "664");
        hashMap.put("Đắk Lắk", "666");
        hashMap.put("Đắc Nông", "667");
        hashMap.put("Lâm Đồng", "668");
        hashMap.put("Bình Phước", "770");
        hashMap.put("Tây Ninh", "772");
        hashMap.put("Bình Dương", "777");
        hashMap.put("Đồng Nai", "775");
        hashMap.put("Bà Rịa-Vũng Tàu", "777");
        hashMap.put("Thành Phố Hồ Chí Minh", "779");
        hashMap.put("Long An", "880");
        hashMap.put("Tiền Giang", "882");
        hashMap.put("Bến Tre", "883");
        hashMap.put("Trà Vinh", "884");
        hashMap.put("Vĩnh Long", "886");
        hashMap.put("Đồng Tháp", "887");
        hashMap.put("An Giang", "889");
        hashMap.put("Kiên Giang", "991");
        hashMap.put("Thành phố Cần Thơ", "992");
        hashMap.put("Hậu Giang", "993");
        hashMap.put("Sóc Trăng", "994");
        hashMap.put("Bạc Liêu", "995");
        hashMap.put("Cà Mau", "996");
	}
	
	public static String geneareTourId(Tour tour) {
		int[] max = {-1};
		String[] codeDes = {"0"};
		String destination = tour.getPlacesToVisit();
		hashMap.forEach((k,v) -> {
			int index = destination.toLowerCase().lastIndexOf(k.toLowerCase());
			if(max[0] < index) {
				max[0] = index;
				codeDes[0] = v;
			}
		});
		
		log.info("FLAGG1");
		if(max[0] == -1) return "";
		
		try {
			String dur = "";
			int duration = Integer.parseInt(tour.getDuration().split("N")[0]);
			
			if(duration == 1) {
				dur = "1N";
			} else {
				dur = duration+"N"+(duration-1)+"Đ";
			}
			tour.setDuration(dur);
			
			int num = ThreadLocalRandom.current().nextInt(0, 100000);
			String formatted = String.format("%05d", num);
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("T").append(codeDes[0]).append("-").append(formatted).append("-").append(dur);
			
			log.info("FLAGG2");
			
			return sBuilder.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}
	
	public static String generateImgName(Tour tour) {
		int num = ThreadLocalRandom.current().nextInt(0, 100000);
		String formatted = String.format("%05d", num);
		return tour.getTourName().substring(0, 50)+ formatted;
	}
}
