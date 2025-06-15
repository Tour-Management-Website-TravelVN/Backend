package ads.library;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import ads.objects.Category;
import ads.objects.Image;
import ads.objects.Tour;
import ads.objects.TourProgram;
import ads.user.CategoryFunctionImpl;
import ads.user.ImageFunctionImpl;
import ads.user.TourFunctionImpl;
import ads.util.Format;
import ads.util.GsonProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TourLibraryAD {

	private static String PLANE = "máy bay";
	private static String CAR = "xe du lịch";
	private static String SHIP = "du thuyền";
	private static String TRAIN = "tàu hỏa";

	public static String viewToursList(int currentPage, String keyword) {
		StringBuilder sBuilder = new StringBuilder("");

		List<Tour> list = TourFunctionImpl.getInstance().getToursList((short) currentPage, keyword);

		list.forEach(tour -> {
			Iterator<Image> iterator = tour.getImageSet().iterator();

			sBuilder.append("<div class=\"card mb-3\">");
			try {
				sBuilder.append("<a href=\"/adv/ad/tour-management/tour_detail?tourid=")
						.append(URLEncoder.encode(tour.getTourId(), "UTF-8")).append("\">");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sBuilder.append("<div class=\"row g-0\">");
			sBuilder.append("<div class=\"col-md-4\">");
			sBuilder.append("<img src=\"").append(iterator.next().getUrl()).append("\"");
			sBuilder.append(" class=\"img-fluid rounded-start\" alt=\"...\">");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-md-8\">");
			sBuilder.append("<div class=\"card-body\">");
			sBuilder.append("<div class=\"d-flex row\">");
			sBuilder.append("<h5 class=\"card-title ps-2 pe-2 mb-0 pb-2\">").append(tour.getTourName()).append("</h5>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"d-flex justify-content-between align-items-center mb-2\">");
			sBuilder.append("<h5 class=\"text-primary fw-bold d-block\">").append(tour.getTourId()).append("</h5>");
			sBuilder.append("<span class=\"badge text-white bg-primary fs-6\">")
					.append(tour.getCategory().getCategoryName()).append("</span>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"row text-dark\">");
			sBuilder.append("<div class=\"col-6 col-md\">");
			sBuilder.append("<i class=\"bi bi-clock-fill me-3\"></i>");
			sBuilder.append("<p class=\"d-inline-block mb-0\">").append(tour.getDuration()).append("</p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-auto col-md-auto\">");

			String vehicle = tour.getVehicle().toLowerCase();
			if (vehicle.contains(CAR))
				sBuilder.append("<i class=\"bi bi-bus-front-fill text-dark fs-5 me-3\"></i>");

			if (vehicle.contains(PLANE))
				sBuilder.append("<i class=\"bi bi-airplane-fill text-dark rotate-45 fs-5 me-3\"></i>");

			if (vehicle.contains(TRAIN))
				sBuilder.append("<i class=\"bi bi-train-freight-front-fill fs-5 me-3 text-dark\"></i>");

			if (vehicle.contains(SHIP))
				sBuilder.append("<i class=\"fa-solid fa-ship fs-5 text-dark\"></i>");

			sBuilder.append("</div>");
			sBuilder.append("<div class=\"w-100 mt-1\"></div>");
			sBuilder.append("<div class=\"col-12 d-flex\">");
			sBuilder.append("<i class=\"bi bi-geo-alt-fill me-3 pb-2 mb-2 text-dark\"></i>");
			sBuilder.append("<span class=\"fw-bold me-2\">Điểm đi: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"departurePlace\"><span class=\"text-dark\">")
					.append(tour.getDeparturePlace()).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 d-flex float-end\">");
			sBuilder.append("<i class=\"bi bi-flag-fill me-3 text-dark\"></i>");
			sBuilder.append("<span class=\"fw-bold me-2\">Điểm đến: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"placesToVisit\"><span class=\"text-dark\">")
					.append(tour.getPlacesToVisit()).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"w-100\"></div>");
			sBuilder.append("<div class=\"col-12 d-flex\">");
			sBuilder.append("<i class=\"bi bi-people-fill pb-0 text-dark me-3\"></i>");
			sBuilder.append("<span class=\"fw-bold me-3\">Đối tượng thích hợp: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"placesToVisit\"><span class=\"text-dark\">")
					.append(tour.getTargetAudience()).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 d-flex\">");
			sBuilder.append("<i class=\"bi bi-hourglass-split pb-0 text-dark me-3\"></i>");
			sBuilder.append("<span class=\"fw-bold me-3\">Thời gian lý tưởng: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"placesToVisit\"><span class=\"text-dark\">")
					.append(tour.getIdealTime()).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"w-100\"></div>");
			sBuilder.append("<div class=\"col-12 col-md d-flex\">");
			sBuilder.append("<i class=\"bi bi-plus-circle-dotted me-3 pb-2 mb-2 text-dark\"></i>");
			sBuilder.append("<span class=\"fw-bold me-2\">Tạo lúc: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"departurePlace\"><span class=\"text-dark\">")
					.append(Format.formatTime(tour.getCreatedTime())).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 col-md-auto d-flex float-end\">");
			sBuilder.append("<i class=\"bi bi-arrow-clockwise me-3 text-dark\"></i>");
			sBuilder.append("<span class=\"fw-bold me-2\">Sửa lúc: </span>");
			sBuilder.append(
					"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"placesToVisit\"><span class=\"text-dark\">")
					.append(Format.formatTime(tour.getLastUpdatedTime())).append("</span></p>");
			sBuilder.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("</a>");
			sBuilder.append("</div>");
		});
		return sBuilder.toString();
	}

	public static String viewPageNavigation(int currentPage, String keyword) {

		int maxPage = TourFunctionImpl.getInstance().getMaxToursPage(keyword);

		if (maxPage == 0)
			return "<h5 class=\"text-center my-3 fw-bold\">Không có kết quả phù hợp</h5>";

		StringBuilder sBuilder = new StringBuilder();

		sBuilder.append("<nav aria-label=\"Page navigation example\">")
				.append("<ul class=\"pagination justify-content-center\">");
		sBuilder.append("<li class=\"page-item ");
		if (currentPage == 1)
			sBuilder.append("disabled");
		sBuilder.append(" \"><a class=\"page-link\" href=\"/adv/ad/tour-management?page=").append(currentPage - 1);
		if (keyword.trim().length() != 0)
			sBuilder.append("&keyword=").append(keyword);
		sBuilder.append(" \"><i class=\"bi bi-arrow-left\"></i></a></li>");

		int count = 1;
		while (count <= maxPage && count <= 3) {
			sBuilder.append("<li class=\"page-item ");
			if (currentPage == count)
				sBuilder.append("active");
			sBuilder.append("\"><a class=\"page-link\" ");
			sBuilder.append(" href=\"/adv/ad/tour-management?page=").append(count);
			if (keyword.trim().length() != 0)
				sBuilder.append("&keyword=").append(keyword);
			sBuilder.append("\">").append(count).append("</a></li>");
			count++;
		}

		if (maxPage >= 7) {
			sBuilder.append("<li class=\"page-item\"><a class=\"page-link\">...</a></li>");
			count = maxPage - 3 + 1;
		}

		while (count <= maxPage) {
			sBuilder.append("<li class=\"page-item ");
			if (currentPage == count)
				sBuilder.append("active");
			sBuilder.append("\"><a class=\"page-link\" ");
			;
			sBuilder.append("href=\"/adv/ad/tour-management?page=").append(count);
			if (keyword.trim().length() != 0)
				sBuilder.append("&keyword=").append(keyword);
			sBuilder.append("\">").append(count).append("</a></li>");
			count++;
		}

		sBuilder.append("<li class=\"page-item ");
		if (currentPage == maxPage)
			sBuilder.append("disabled");
		sBuilder.append("\"><a class=\"page-link ");
		sBuilder.append("\" href=\"/adv/ad/tour-management?page=").append(currentPage + 1);
		if (keyword.trim().length() != 0)
			sBuilder.append("&keyword=").append(keyword);
		sBuilder.append("\"><i class=\"bi bi-arrow-right\"></i></a></li>").append("</ul>")
				.append("</nav><!-- End Basic Pagination -->");

		return sBuilder.toString();
	}

	public static String viewTourDetail(String tourId, String actionTour) {
		// if(tourId == null) return "<section class=\"section\"><h5 class='fw-bold
		// text-center'>KHÔNG CÓ THÔNG TIN</h5></section>";
		Tour tour = TourFunctionImpl.getInstance().getTourByTourId(tourId.trim());
		if (tour == null)
			return "<section class=\"section\"><h5 class='fw-bold text-center'>KHÔNG CÓ THÔNG TIN</h5></section>";

		int tourUnitsQuant = TourFunctionImpl.getInstance().countTourUnit(tourId.trim());

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-12\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body pt-3 pb-0 mb-0\">");
		sBuilder.append("<h5 class=\"text-primary fw-bold mb-3\">Thông tin cơ bản</h5>");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-12 col-md-4\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-6 ps-3\">");
		sBuilder.append("<p class=\"fw-bold fs-6\">Người tạo tour: </p>");
		sBuilder.append("<p class=\"fw-bold fs-6\">Thời gian tạo: </p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-6\">");
		sBuilder.append("<p class=\"fw-normal fs-6\">").append(tour.getTourOperator().getFullName()).append("</p>");
		sBuilder.append("<p class=\"fw-normal fs-6\">").append(Format.formatTime(tour.getCreatedTime())).append("</p>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12 col-md-4\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-6 ps-3\">");
		sBuilder.append("<p class=\"fw-bold fs-6\">Người sửa lần cuối: </p>");
		sBuilder.append("<p class=\"fw-bold fs-6\">Thời gian sửa: </p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-6\">");
		sBuilder.append("<p class=\"fw-normal fs-6\">").append(
				tour.getLastUpdatedOperator().getFirstname() == null ? "" : tour.getLastUpdatedOperator().getFullName())
				.append("</p>");
		sBuilder.append("<p class=\"fw-normal fs-6\">").append(Format.formatTime(tour.getLastUpdatedTime()))
				.append("</p>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12 col-md-4\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-6 ps-3\">");
		sBuilder.append("<p class=\"fw-bold fs-6 text-warning\">Số đơn vị tour: </p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-6\">");
		sBuilder.append("<p class=\"fw-normal fs-6 text-warning fw-bold\">").append(tourUnitsQuant).append("</p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"w-100\"></div>");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<p class=\"ps-1 pb-1 text-center text-md-start\">");
		sBuilder.append(
				"<a href=\"../tour-management/Tour-Unit?tour_id="+tour.getTourId()+"&recordArea=1\" class=\"fw-bold text-end col\">Các đơn vị tour <i class=\"bi-arrow-right\"></i></a>");
		sBuilder.append("</p>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-12\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between w-100 ms-0 ps-0\">");
		sBuilder.append("<h5 class=\"card-title d-inline-block ms-0 pb-0\">").append(tour.getTourName())
				.append("</h5>");

		if (tourUnitsQuant == 0) {
			try {
				sBuilder.append("<a href=\"/adv/ad/tour-management/tour_form?action=update&tourid=")
						.append(URLEncoder.encode(tourId, "UTF-8"))
						.append("\" class=\"fw-bold col-md-1 mt-3 ms-auto text-end fs-3 ");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				sBuilder.append("<a href=\"/adv/ad/tour-management/tour_form?action=updateImgs&tourid=")
						.append(URLEncoder.encode(tourId, "UTF-8"))
						.append("\" class=\"fw-bold col-md-1 mt-3 ms-auto text-end fs-3 ");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		sBuilder.append(tourUnitsQuant == 0 ? "me-5" : "me-0").append(" pe-1\">");
//		sBuilder.append("<i class=\"bi bi-gear-fill text-warning\"></i>");
		sBuilder.append("</a>");

		if (tourUnitsQuant == 0) {
			sBuilder.append(
					"<a class=\" fw-bold text-danger fw-bold col-md-auto mt-3 ms-0 text-end fs-3\" style=\"cursor: pointer;\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop\">");
			sBuilder.append("<i class=\"bi bi-trash-fill\"></i>");
			sBuilder.append("</a>");
		}
		sBuilder.append("<!-- Modal -->");
		sBuilder.append("<div class=\"modal\" id=\"staticBackdrop\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append(
				"<h5 class=\"modal-title text-danger fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận xóa</h5>");
		sBuilder.append(
				"<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">Bạn có muốn xóa tour này không?</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		try {
			sBuilder.append("<form action=\"/adv/ad/tour-management/tour_detail?action=del&tourid=")
					.append(URLEncoder.encode(tourId, "UTF-8")).append("\" method=\"post\">");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sBuilder.append("<button type=\"submit\" class=\"btn btn-primary\">Xác nhận</button>");
		sBuilder.append("</form>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center mt-2 mb-3\">");
		sBuilder.append("<h5 class=\"text-primary fw-bold d-block\">").append(tour.getTourId()).append("</h5>");
		sBuilder.append("<span class=\"badge text-white bg-primary fs-6\">")
				.append(tour.getCategory().getCategoryName()).append("</span>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-6 col-md\">");
		sBuilder.append("<i class=\"bi bi-clock-fill me-3\"></i>");
		sBuilder.append("<p class=\"d-inline-block\">").append(tour.getDuration()).append("</p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto col-md-auto\">");

		String vehicle = tour.getVehicle().toLowerCase();
		if (vehicle.contains(CAR))
			sBuilder.append("<i class=\"bi bi-bus-front-fill text-dark fs-5 me-3\"></i>");

		if (vehicle.contains(PLANE))
			sBuilder.append("<i class=\"bi bi-airplane-fill text-dark rotate-45 fs-5 me-3\"></i>");

		if (vehicle.contains(TRAIN))
			sBuilder.append("<i class=\"bi bi-train-freight-front-fill fs-5 me-3 text-dark\"></i>");

		if (vehicle.contains(SHIP))
			sBuilder.append("<i class=\"fa-solid fa-ship fs-5 text-dark\"></i>");

//		sBuilder.append("<i class=\"bi bi-bus-front-fill text-dark fs-5 me-3\"></i>");
//		sBuilder.append("<i class=\"bi bi-airplane-fill text-dark rotate-45 fs-5 me-3\"></i>");
//		sBuilder.append("<i class=\"bi bi-train-freight-front-fill fs-5 me-3\"></i>");
//		sBuilder.append("<i class=\"fa-solid fa-ship fs-5\"></i>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"w-100 mt-1\"></div>");
		sBuilder.append("<div class=\"col-12 col-md d-flex\">");
		sBuilder.append("<i class=\"bi bi-geo-alt-fill me-3 pb-2 mb-2 text-dark\"></i>");
		sBuilder.append("<span class=\"fw-bold me-2\">Điểm đi: </span>");
		sBuilder.append(
				"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"departurePlace\"><span class=\"text-dark\">")
				.append(tour.getDeparturePlace()).append("</span></p>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12 col-md-auto d-flex float-end\">");
		sBuilder.append("<i class=\"bi bi-flag-fill me-3 text-dark\"></i>");
		sBuilder.append("<span class=\"fw-bold me-2\">Điểm đến: </span>");
		sBuilder.append(
				"<p class=\"fs-6 mb-2 text-dark pt-0 pb-2 mb-2\" id=\"placesToVisit\"><span class=\"text-dark\">")
				.append(tour.getPlacesToVisit()).append("</span></p>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-12 col-md-4 d-flex flex-column align-items-center\">");
		sBuilder.append("<i class=\"bi bi-people-fill text-primary fa-3x pb-0\"></i>");
		sBuilder.append("<span class=\"text-primary fw-bold decrease-margin-lg-15\">Đối tượng thích hợp</span>");
		sBuilder.append("<span class=\"text-dark fs-6 fw-bold text-center\" id=\"targetAudience\">")
				.append(tour.getTargetAudience()).append("</span>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12 col-md-4 d-flex flex-column align-items-center\">");
		sBuilder.append("<i class=\"bi bi-flower1 text-primary fa-3x pb-0\"></i>");
		sBuilder.append("<span class=\"text-primary fw-bold decrease-margin-lg-15\">Ẩm thực</span>");
		sBuilder.append("<span class=\"text-dark fs-6 fw-bold text-center\" id=\"cuisine\">").append(tour.getCuisine())
				.append("</span>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12 col-md-4 d-flex flex-column align-items-center\">");
		sBuilder.append("<i class=\"bi bi-clock-fill text-primary fa-3x pb-0\"></i>");
		sBuilder.append("<span class=\"text-primary fw-bold decrease-margin-lg-15\">Thời gian lý tưởng</span>");
		sBuilder.append("<span class=\"text-dark fs-6 fw-bold text-center\" id=\"idealTime\">")
				.append(tour.getIdealTime()).append("</span>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mt-1\">");
		sBuilder.append("<div class=\"col-12\">");
		sBuilder.append("<h5 class=\"text-primary fw-bold mt-2\">Mô tả</h5>");
		if (tour.getExclusions().contains("\n")) {
			sBuilder.append("<div class=\"border-0 w-100\">").append(Format.replaceEnter(tour.getDescription()))
					.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 mt-3\">");
			sBuilder.append("<h5 class=\"text-primary fw-bold\">Tour bao gồm</h5>");
			sBuilder.append("<div class=\"border-0 w-100\">").append(Format.replaceEnter(tour.getInclusions()))
					.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 mt-3\">");
			sBuilder.append("<h5 class=\"text-primary fw-bold\">Tour không bao gồm</h5>");
			sBuilder.append("<div class=\"border-0 w-100\">").append(Format.replaceEnter(tour.getExclusions()))
					.append("</div>");
		} else {
			sBuilder.append("<div class=\"border-0 w-100\">").append(tour.getDescription())
					.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 mt-3\">");
			sBuilder.append("<h5 class=\"text-primary fw-bold\">Tour bao gồm</h5>");
			sBuilder.append("<div class=\"border-0 w-100\">").append(tour.getInclusions())
					.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"col-12 mt-3\">");
			sBuilder.append("<h5 class=\"text-primary fw-bold\">Tour không bao gồm</h5>");
			sBuilder.append("<div class=\"border-0 w-100\">").append(tour.getExclusions())
					.append("</div>");
		}

		sBuilder.append("</div>");
		sBuilder.append("</div>");

		if (actionTour != null) {
			sBuilder.append("<!-- Toast Container -->");
			sBuilder.append(
					"<div class=\"toast position-fixed bottom-0 end-0\" style=\"z-index: 999999;\" id=\"myToast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">");
			sBuilder.append("<div class=\"toast-header\">");
			sBuilder.append("<strong class=\"me-auto text-warning\">Thông báo</strong>");
			sBuilder.append("<small>Vừa xong</small>");
			sBuilder.append(
					"<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
			sBuilder.append("</div>");
			sBuilder.append("<div class=\"toast-body bg-white\">").append(actionTour).append("</div>");
			sBuilder.append("<!-- Show Toast -->");

			sBuilder.append("<script>");
			sBuilder.append("$(function () {");
			sBuilder.append("const toastEl = document.getElementById('myToast');");
			sBuilder.append("const toast = new bootstrap.Toast(toastEl);");
			sBuilder.append("toast.show();");
			sBuilder.append("})");
			sBuilder.append("</script>");
		}

		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-12 col-lg-12 mb-0\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body pb-0\">");
		sBuilder.append("<h5 class=\"card-title pb-0\">Hình ảnh</h5>");
		sBuilder.append(
				"<div class=\"d-flex overflow-auto gap-3 p-2 col-12 mb-3 px-0\" style=\"height: 250px; width: auto;\">");

		tour.getImageSet().stream().forEach(image -> {
			sBuilder.append("<img src=\"").append(image.getUrl())
					.append("\" class=\"img-thumbnail\" style=\"height: 215px; width: auto;\" />");
		});

		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-12\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<!-- Tour Programs Start -->");
		sBuilder.append("<h5 class=\"text-start text-primary fw-bold mt-4 mb-4\">Chương trình tour</h5>");
		sBuilder.append("<div class=\"accordion accordion-flush\" id=\"accordionTourPrograms\">");

		AtomicInteger index = new AtomicInteger(1);
		tour.getTourProgramSet().stream().forEach(tp -> {
			int count = index.getAndIncrement();
			sBuilder.append("<div class=\"accordion-item mb-1\">");
			sBuilder.append("<h2 class=\"accordion-header\">");
			sBuilder.append(
					"<button class=\"accordion-button collapsed border px-2 px-lg-4 pt-3 bg-light\" type=\"button\"");
			sBuilder.append("data-bs-toggle=\"collapse\" data-bs-target=\"#arc").append(count)
					.append("\" aria-expanded=\"false\"");
			sBuilder.append("aria-controls=\"arc").append(count).append("\">");
			sBuilder.append("<div>");
			sBuilder.append("<span class=\"d-block text-primary fw-bold\">Ngày ").append(tp.getDay()).append(": ")
					.append(tp.getLocations()).append("</span>");
			sBuilder.append("<br><span class=\"text-dark fw-normal\"><i class=\"fa-solid fa-utensils me-2\"></i>")
					.append(tp.getMealDescription()).append("</span>");
			sBuilder.append("</div>");
			sBuilder.append("</button>");
			sBuilder.append("</h2>");
			sBuilder.append("<div id=\"arc").append(count).append("\" class=\"accordion-collapse collapse show\">");
			sBuilder.append(
					"<div class=\"accordion-body border shadow text-justify text-dark px-2 px-lg-4 pt-3 lh-lg\">");
			sBuilder.append(Format.replaceEnter(tp.getDesciption()));

			log.info("day: {}, location: {}, meal: {}, descr: {}", tp.getDay(), tp.getLocations(),
					tp.getMealDescription(), tp.getLocations());

			sBuilder.append("</div>");
			sBuilder.append("</div>");
			sBuilder.append("</div>");
		});

		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");

//		log.info(sBuilder.toString());
		return sBuilder.toString();
	}

	public static String formAddTour() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<main id=\"main\" class=\"main\">");
		sBuilder.append("<div class=\"pagetitle d-flex\">");
		sBuilder.append("<h1>Thêm tour mới</h1>");
		sBuilder.append("<nav class=\"ms-auto\">");
		sBuilder.append("<ol class=\"breadcrumb\">");
		sBuilder.append(
				"<li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
		sBuilder.append("<li class=\"breadcrumb-item\">Quản lý tour</li>");
		sBuilder.append("<li class=\"breadcrumb-item active\">Thêm tour mới</li>");
		sBuilder.append("</ol>");
		sBuilder.append("</nav>");
		sBuilder.append("</div><!-- End Page Title -->");
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<form class=\"needs-validation\" id=\"tourForm\" novalidate action=\"\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<h5 class=\"card-title\">Nhập thông tin cho tour mới</h5>");
		sBuilder.append("<div class=\"row mb-3\" id=\"tourInfo\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control bg-light\" id=\"tourId\" placeholder=\"Mã tour\" readonly>");
		sBuilder.append("<label for=\"tourId\">Mã tour</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating d-flex align-items-center h-100 justify-content-center\">");
		sBuilder.append("<p class=\"mb-0 fst-italic\">Lưu ý: Tour phải có ít nhất 1 ảnh</p>");
		sBuilder.append("<!--tourImage-->");
		sBuilder.append(
				"<input class=\"form-control d-none\" type=\"file\" id=\"image-input\" placeholder=\"Ảnh\" multiple ");
		sBuilder.append("accept=\"/image/*\" required>");
		sBuilder.append("<label for=\"image-input\" class=\"d-none\">Ảnh</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-3\" style=\"height: 250px; width: auto;\">");
		sBuilder.append("<div id=\"image-list\" class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-0 border-0\"");
		sBuilder.append("style=\"height: 250px; width: auto;\">");
		sBuilder.append("</div>                 ");
		sBuilder.append("<div class=\"w-100 h100 d-flex align-items-center me-3\">");
		sBuilder.append("<button type=\"button\" class=\"bg-white border-0\" id=\"add-image-btn\">");
		sBuilder.append("<i class=\"bi bi-plus-circle text-primary h1\"></i>");
		sBuilder.append("</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourName\" placeholder=\"Tên tour\" required>");
		sBuilder.append("<label for=\"tourName\">Tên tour</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Tên tour nằm trong khoảng 15 đến 255 ký tự.</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"number\" class=\"form-control\" id=\"tourDuration\" placeholder=\"Thời lượng tour\" required>");
		sBuilder.append("<label for=\"tourDuration\">Thời lượng tour (ngày)</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Từ 1 đến 60 ngày</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourDeparture\" placeholder=\"Nơi khởi hành\" required>");
		sBuilder.append("<label for=\"tourDeparture\">Nơi khởi hành</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourAttractions\" placeholder=\"Điểm tham quan\" required>");
		sBuilder.append("<label for=\"tourAttractions\">Điểm tham quan</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourTarget\" placeholder=\"Đối tượng nhắm tới\" required>");
		sBuilder.append("<label for=\"tourTarget\">Đối tượng thích hợp</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourCuisine\" placeholder=\"Ẩm thực\" required>");
		sBuilder.append("<label for=\"tourCuisine\">Ẩm thực</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourIdealTime\" placeholder=\"Thời điểm lý tưởng\"");
		sBuilder.append("required>");
		sBuilder.append("<label for=\"tourIdealTime\">Thời điểm lý tưởng</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append("<select class=\"form-select\" id=\"idCategory\" required>");
		sBuilder.append("<option value=\"\" selected disabled hidden>Chọn thể loại</option>");
		List<Category> categories = CategoryFunctionImpl.getInstance().getCategories();
		categories.forEach(category -> {
			sBuilder.append("<option value=\"").append(category.getId()).append("\">")
					.append(category.getCategoryName()).append("</option>");
		});
//		sBuilder.append("<option value=\"2\">Du lịch núi</option>");
//		sBuilder.append("<option value=\"3\">Du lịch văn hóa</option>");
//		sBuilder.append("<option value=\"4\">Du lịch ẩm thực</option>");
//		sBuilder.append("<option value=\"5\">Du lịch sinh thái</option>");
//		sBuilder.append("<option value=\"6\">Du lịch mạo hiểm</option>");
		sBuilder.append("</select>");
		sBuilder.append("<label for=\"floatingSelect\">Thể loại</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Vui lòng chọn thể loại.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-included\" class=\"mb-1\">Đã bao gồm</label>");
		sBuilder.append("<div id=\"quill-included\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3 mt-0\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-not-included\" class=\"mb-1\">Chưa bao gồm</label>");
		sBuilder.append("<div id=\"quill-not-included\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-description\" class=\"mb-1\">Mô tả</label>");
		sBuilder.append("<div id=\"quill-description\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<legend class=\"col-form-label col-sm-auto pt-0\">Phương tiện</legend>");
		sBuilder.append("<div class=\"col-sm-9 ms-0\" id=\"vehicle\">");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleCar\" value=\"Xe du lịch\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleCar\">Xe du lịch</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehiclePlane\" value=\"Máy bay\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehiclePlane\">Máy bay</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleTrain\" value=\"Tàu hỏa\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleTrain\">Tàu hoả</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleShip\" value=\"Du thuyền\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleShip\">Du thuyền</label>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"small text-danger mt-1 fs-6\" style=\"display: none;\">Chọn ít nhất 1 phương tiện.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3 d-flex justify-content-center justify-content-md-end\">");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-warning text-white\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop2\" id=\"btnClear\" ");
		sBuilder.append("style=\"width: 150px;\">Bỏ khôi phục</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop\"");
		sBuilder.append("style=\"width: 150px;\">Hủy bỏ</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary\" id=\"addTour\" style=\"width: 150px;\">Thêm tour mới</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append("<div class=\"modal\" id=\"staticBackdrop\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header bg-danger\">");
		sBuilder.append(
				"<h5 class=\"modal-title text-white fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận thoát</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\"");
		sBuilder.append("aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("Thông tin tour sẽ không được lưu?");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" id=\"btnExit\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append(
				"<div class=\"modal\" id=\"finalModal\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"finalModel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append("<h5 class=\"modal-title text-danger fw-bold fs-4\">Kiểm tra thông tin lần cuối</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\"");
		sBuilder.append("aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("Hãy kiểm tra lại thông tin tour lần cuối");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<div class=\"card\" id=\"tpMark\" style=\"display: none;\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<h5 class=\"card-title\">Chương trình tour</h5>");
		sBuilder.append("<div class=\"card-body\" id=\"tourPrograms\">");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"container row mx-2 mb-3 d-flex justify-content-center justify-content-md-end\">");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger\" id=\"unlockAll\" style=\"width: 150px;\">Hủy bỏ</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append("<button type=\"button\" id=\"saveAll\" class=\"btn btn-primary float-end\"");
		sBuilder.append("style=\"width: 150px;\">Lưu</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</form><!-- End General Form Elements -->");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");
		sBuilder.append("</main><!-- End #main -->");
		return sBuilder.toString();
	}

	public static String formUpdateImgsTour(HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuilder sBuilder = new StringBuilder();

		String tourId = request.getParameter("tourid");
		if (StringUtils.isBlank(tourId))
			return "<h5 class=\"text-center my-3 fw-bold\">Không có kết quả phù hợp</h5>";

		String tourName = TourFunctionImpl.getInstance().getTourNameByTourId(tourId);
		if (StringUtils.isBlank(tourName))
			return "<h5 class=\"text-center my-3 fw-bold\">Không có kết quả phù hợp</h5>";

		sBuilder.append("<main id=\"main\" class=\"main\">");
		sBuilder.append("<div class=\"pagetitle d-flex\">");
		sBuilder.append("<h1>Cập nhật ảnh tour</h1>");
		sBuilder.append("<nav class=\"ms-auto\">");
		sBuilder.append("<ol class=\"breadcrumb\">");
		sBuilder.append(
				"<li class=\"breadcrumb-item\"><a href=\"[index.html]\"><i class=\"bi bi-house-door\"></i></a></li>");
		sBuilder.append("<li class=\"breadcrumb-item\">Quản lý tour</li>");
		sBuilder.append("<li class=\"breadcrumb-item active\">Cập nhật ảnh tour</li>");
		sBuilder.append("</ol>");
		sBuilder.append("</nav>");
		sBuilder.append("</div><!-- End Page Title -->");
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<form class=\"needs-validation\" id=\"tourForm\" novalidate action=\"\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");

		sBuilder.append("<h5 class=\"card-title\">Cập nhật ảnh cho tour: ").append(tourName).append("</h5>");
		sBuilder.append("<!-- General Form Elements -->");
		sBuilder.append("<div class=\"row mb-3\" id=\"tourInfo\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append("<input type=\"text\" class=\"form-control bg-light\" id=\"tourId\" value=\"").append(tourId)
				.append("\" placeholder=\"Mã tour\" readonly>");
		sBuilder.append("<label for=\"tourId\">Mã tour</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating d-flex align-items-center h-100 justify-content-center\">");
		sBuilder.append("<p class=\"mb-0 fst-italic\">Lưu ý: Tour phải có ít nhất 1 ảnh</p>");
		sBuilder.append("<!--tourImage-->");
		sBuilder.append(
				"<input class=\"form-control d-none\" type=\"file\" id=\"image-input\" placeholder=\"Ảnh\" multiple accept=\"/image/*\" required>");
		sBuilder.append("<label for=\"image-input\" class=\"d-none\">Ảnh</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-3\" style=\"height: 250px; width: auto;\">");
		sBuilder.append(
				"<div id=\"image-list\" class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-0 border-0\" style=\"height: 250px; width: auto;\">");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"w-100 h100 d-flex align-items-center me-3\">");
		sBuilder.append("<button type=\"button\" class=\"bg-white border-0\" id=\"add-image-btn\">");
		sBuilder.append("<i class=\"bi bi-plus-circle text-primary h1\"></i>");
		sBuilder.append("</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3 d-flex justify-content-center justify-content-md-end\">");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop\" style=\"width: 150px;\">Hủy bỏ</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary\" id=\"addTour\" style=\"width: 150px;\">Lưu</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append(
				"<div class=\"modal\" id=\"staticBackdrop\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append(
				"<h5 class=\"modal-title text-danger fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận thoát</h5>");
		sBuilder.append(
				"<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\"> Thông tin tour sẽ không được lưu? </div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		try {
			sBuilder.append("<a href=\"/adv/ad/tour-management/tour_detail?tourid=").append(URLEncoder.encode(tourId, "UTF-8"))
					.append("\" class=\"btn btn-primary\">Xác nhận</a>");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append(
				"<div class=\"modal\" id=\"finalModal\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\" aria-labelledby=\"finalModel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append("<h5 class=\"modal-title text-danger fw-bold fs-4\">Kiểm tra lại thông tin</h5>");
		sBuilder.append(
				"<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\"> Tour phải có ít nhất 1 ảnh </div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</form><!-- End General Form Elements -->");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");
		sBuilder.append("</main><!-- End #main -->");
		sBuilder.append("<script>");

		List<Image> list = ImageFunctionImpl.getInstance().getImgsByTourId(tourId);

		sBuilder.append("let existingImages2 = [");

		StringBuilder sBuilder2 = new StringBuilder();

		list.forEach(img -> {
			sBuilder2.append(String.format("{ id: '%d', url: '%s', deleted: false },", img.getId(), img.getUrl()));
		});

		String exString = sBuilder2.toString();
		sBuilder.append(exString.substring(0, exString.length() - 1));

		log.info("EXS: {}", exString);

		sBuilder.append("];");
		sBuilder.append("localStorage.setItem('updateImgsTour', JSON.stringify(existingImages2));");

		/*
		 * sBuilder.append(""" let existingImages2 = [ { id: 'abc123.jpg', url:
		 * 'https://baodongnai.com.vn/file/e7837c02876411cd0187645a2551379f/dataimages/
		 * 201706/original/images1920558_4053279_16.jpg', deleted: false }, { id:
		 * 'xyz456.jpg', url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:
		 * ANd9GcRdvHiMePqz1GKwY38h5_Nfx0ga731PEC0l7A&s', deleted: false }, { id: '1',
		 * url: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:
		 * ANd9GcRdvHiMePqz1GKwY38h5_Nfx0ga731PEC0l7A&s', deleted: false }, { id: '2',
		 * url: 'https://cdn-media.sforum.vn/storage/app/media/anh-dep-83.jpg', deleted:
		 * false } ]; // Lưu dữ liệu (cần stringify)
		 * localStorage.setItem('updateImgsTour', JSON.stringify(existingImages2));
		 * """);
		 */

		sBuilder.append("</script>");

		return sBuilder.toString();
	}

	public static String formUpdateTour(HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuilder sBuilder = new StringBuilder();

		String tourId = request.getParameter("tourid");
		if (StringUtils.isBlank(tourId))
			return "<h5 class=\"text-center my-3 fw-bold\">Không có kết quả phù hợp</h5>";

		Tour tour = TourFunctionImpl.getInstance().getTourByTourId(tourId);
		if (StringUtils.isBlank(tour.getTourName()))
			return "<h5 class=\"text-center my-3 fw-bold\">Không có kết quả phù hợp</h5>";

		sBuilder.append("<main id=\"main\" class=\"main\">");
		sBuilder.append("<div class=\"pagetitle d-flex\">");
		sBuilder.append("<h1>Cập nhật tour</h1>");
		sBuilder.append("<nav class=\"ms-auto\">");
		sBuilder.append("<ol class=\"breadcrumb\">");
		sBuilder.append(
				"<li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
		sBuilder.append("<li class=\"breadcrumb-item\">Quản lý tour</li>");
		sBuilder.append("<li class=\"breadcrumb-item active\">Cập nhật tour</li>");
		sBuilder.append("</ol>");
		sBuilder.append("</nav>");
		sBuilder.append("</div><!-- End Page Title -->");
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<form class=\"needs-validation\" id=\"tourForm\" novalidate action=\"\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<h5 class=\"card-title\">Nhập thông tin mới cho tour</h5>");
		sBuilder.append("<!-- General Form Elements -->");
		sBuilder.append("<div class=\"row mb-3\" id=\"tourInfo\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control bg-light\" id=\"tourId\" placeholder=\"Mã tour\" value=\"")
				.append(tour.getTourId()).append("\" readonly required>");
		sBuilder.append("<label for=\"tourId\">Mã tour</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating d-flex align-items-center h-100 justify-content-center\">");
		sBuilder.append("<p class=\"mb-0 fst-italic\">Lưu ý: Tour phải có ít nhất 1 ảnh</p>");
		sBuilder.append("<!--tourImage-->");
		sBuilder.append(
				"<input class=\"form-control d-none\" type=\"file\" id=\"image-input\" placeholder=\"Ảnh\" multiple ");
		sBuilder.append("accept=\"/image/*\" required>");
		sBuilder.append("<label for=\"image-input\" class=\"d-none\">Ảnh</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-3\" style=\"height: 250px; width: auto;\">");
		sBuilder.append("<div id=\"image-list\" class=\"d-flex overflow-auto gap-3 p-2 border col-12 mb-0 border-0\"");
		sBuilder.append("style=\"height: 250px; width: auto;\"></div>");
		sBuilder.append("<div class=\"w-100 h100 d-flex align-items-center me-3\">");
		sBuilder.append("<button type=\"button\" class=\"bg-white border-0\" id=\"add-image-btn\">");
		sBuilder.append("<i class=\"bi bi-plus-circle text-primary h1\"></i>");
		sBuilder.append("</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control\" id=\"tourName\" value=\"")
				.append(tour.getTourName()).append("\" required>");
		sBuilder.append("<label for=\"tourName\">Tên tour</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Tên tour nằm trong khoảng 15 đến 255 ký tự.</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"number\" class=\"form-control bg-light\" id=\"tourDuration\" placeholder=\"Thời lượng tour\" value=\"")
				.append(Integer.parseInt(tour.getDuration().split("N")[0])).append("\" required readonly>");
		sBuilder.append("<label for=\"tourDuration\">Thời lượng tour (ngày)</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Từ 1 đến 60 ngày</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control bg-light\" id=\"tourDeparture\" placeholder=\"Nơi khởi hành\" value=\"")
				.append(tour.getDeparturePlace()).append("\" required readonly>");
		sBuilder.append("<label for=\"tourDeparture\">Nơi khởi hành</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control bg-light\" id=\"tourAttractions\" placeholder=\"Điểm tham quan\" value=\"")
				.append(tour.getPlacesToVisit()).append("\" readonly required>");
		sBuilder.append("<label for=\"tourAttractions\">Điểm tham quan</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourTarget\" placeholder=\"Đối tượng nhắm tới\" value=\"")
				.append(tour.getTargetAudience()).append("\" required>");
		sBuilder.append("<label for=\"tourTarget\">Đối tượng thích hợp</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourCuisine\" placeholder=\"Ẩm thực\" value=\"")
				.append(tour.getCuisine()).append("\" required>");
		sBuilder.append("<label for=\"tourCuisine\">Ẩm thực</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<div class=\"col-md-6\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control\" id=\"tourIdealTime\" placeholder=\"Thời điểm lý tưởng\" value=\"")
				.append(tour.getIdealTime()).append("\" ");
		sBuilder.append("required>");
		sBuilder.append("<label for=\"tourIdealTime\">Thời điểm lý tưởng</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 6 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-md-6 mt-3 mt-md-0\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append("<select class=\"form-select\" id=\"idCategory\" required>");

		List<Category> categories = CategoryFunctionImpl.getInstance().getCategories();
		String categoryName = tour.getCategory().getCategoryName();
		categories.forEach(category -> {
			sBuilder.append("<option value=\"").append(category.getId()).append("\" ")
					.append(categoryName.equalsIgnoreCase(category.getCategoryName()) ? "selected" : "").append(">")
					.append(category.getCategoryName()).append("</option>");
		});

//		sBuilder.append("<option value=\"1\">Du lịch biển</option>");

		sBuilder.append("</select>");
		sBuilder.append("<label for=\"floatingSelect\">Thể loại</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Vui lòng chọn thể loại.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-included\" class=\"mb-1\">Đã bao gồm</label>");
		sBuilder.append("<div id=\"quill-included\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3 mt-0\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-not-included\" class=\"mb-1\">Chưa bao gồm</label>");
		sBuilder.append("<div id=\"quill-not-included\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"mb-0\">");
		sBuilder.append("<div class=\"card pt-3\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<label for=\"quill-description\" class=\"mb-1\">Mô tả</label>");
		sBuilder.append("<div id=\"quill-description\"></div>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài từ 20 ký tự trở lên.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3\">");
		sBuilder.append("<legend class=\"col-form-label col-sm-auto pt-0\">Phương tiện</legend>");
		sBuilder.append("<div class=\"col-sm-9 ms-0\" id=\"vehicle\">");
		sBuilder.append("<div class=\"form-check form-check-inline\">");

		String vehicle = tour.getVehicle().toLowerCase();
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleCar\" value=\"Xe du lịch\" ")
				.append(vehicle.contains(CAR) ? "checked" : "").append(">");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleCar\">Xe du lịch</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehiclePlane\" value=\"Máy bay\" ")
				.append(vehicle.contains(PLANE) ? "checked" : "").append(">");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehiclePlane\">Máy bay</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleTrain\" value=\"Tàu hỏa\" ")
				.append(vehicle.contains(TRAIN) ? "checked" : "").append(">");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleTrain\">Tàu hoả</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-check form-check-inline\">");
		sBuilder.append(
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleShip\" value=\"Du thuyền\" ")
				.append(vehicle.contains(SHIP) ? "checked" : "").append(">");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleShip\">Du thuyền</label>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"small text-danger mt-1 fs-6\" style=\"display: none;\">Chọn ít nhất 1 phương tiện.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3 d-flex justify-content-center justify-content-md-end\">");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-warning text-white\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop2\" id=\"btnClear\" ");
		sBuilder.append("style=\"width: 150px;\">Bỏ khôi phục</a>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#staticBackdrop\" id=\"btnCancel\" ");
		sBuilder.append("style=\"width: 150px;\">Hủy bỏ</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary d-none\" id=\"addTour\" style=\"width: 150px;\">Thêm tour mới</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append("<div class=\"modal\" id=\"staticBackdrop\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header bg-danger\">");
		sBuilder.append(
				"<h5 class=\"modal-title text-white fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận thoát</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\"");
		sBuilder.append("aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("Thông tin tour sẽ không được lưu?");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" id=\"btnExit\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal -->");
		sBuilder.append(
				"<div class=\"modal\" id=\"finalModal\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"finalModel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content\">");
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append("<h5 class=\"modal-title text-danger fw-bold fs-4\">Kiểm tra thông tin lần cuối</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\"");
		sBuilder.append("aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("Hãy kiểm tra lại thông tin tour lần cuối");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary\" data-bs-dismiss=\"modal\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<div class=\"card\" id=\"tpMark\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<h5 class=\"card-title\">Chương trình tour</h5>");
		sBuilder.append("<div class=\"card-body\" id=\"tourPrograms\">");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"container row mx-2 mb-3 d-flex justify-content-center justify-content-md-end\">");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger\" id=\"unlockAll\" style=\"width: 150px;\">Hủy bỏ</button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto\">");
		sBuilder.append(
				"<button type=\"button\" id=\"saveAll\" class=\"btn btn-primary float-end\" style=\"width: 150px;\">Lưu</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</form><!-- End General Form Elements -->");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");
		sBuilder.append("</main><!-- End #main -->");

		sBuilder.append("<script>");
		List<Image> list = ImageFunctionImpl.getInstance().getImgsByTourId(tourId);
		sBuilder.append("let existingImages2 = [");

		StringBuilder sBuilder2 = new StringBuilder();
		list.forEach(img -> {
			sBuilder2.append(String.format("{ id: '%d', url: '%s', deleted: false },", img.getId(), img.getUrl()));
		});
		String exString = sBuilder2.toString();
		sBuilder.append(exString.substring(0, exString.length() - 1));
		log.info("EXS: {}", exString);

		sBuilder.append("];");
		sBuilder.append("localStorage.setItem('updateImgsTour', JSON.stringify(existingImages2));");

		Set<TourProgram> programs = tour.getTourProgramSet();
		String programsJSON = GsonProvider.getGson().toJson(programs);
		sBuilder.append("localStorage.setItem(\"tourPrograms\", JSON.stringify(").append(programsJSON).append("));");
		sBuilder.append("localStorage.setItem(\"description\", JSON.stringify(")
				.append(GsonProvider.getGson().toJson(tour.getDescription())).append("));");
		sBuilder.append("localStorage.setItem(\"inclusion\", JSON.stringify(")
				.append(GsonProvider.getGson().toJson(tour.getInclusions())).append("));");
		sBuilder.append("localStorage.setItem(\"exclusion\", JSON.stringify(")
				.append(GsonProvider.getGson().toJson(tour.getExclusions())).append("))");
		sBuilder.append("</script>");

		return sBuilder.toString();
	}
}
