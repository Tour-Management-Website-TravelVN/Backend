package ads.library;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import ads.objects.Image;
import ads.objects.Tour;
import ads.user.TourFunctionImpl;
import ads.util.Format;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TourLibrary {

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
			sBuilder.append("<a href=\"/adv/to/tour/tour_detail?tourid=").append(tour.getTourId()).append("\">");
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
		sBuilder.append(" \"><a class=\"page-link\" href=\"/adv/to/tour?page=").append(currentPage - 1);
		if (keyword.trim().length() != 0)
			sBuilder.append("&keyword=").append(keyword);
		sBuilder.append(" \"><i class=\"bi bi-arrow-left\"></i></a></li>");

		int count = 1;
		while (count <= maxPage && count <= 3) {
			sBuilder.append("<li class=\"page-item ");
			if (currentPage == count)
				sBuilder.append("active");
			sBuilder.append("\"><a class=\"page-link\" ");
			sBuilder.append(" href=\"/adv/to/tour?page=").append(count);
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
			sBuilder.append("href=\"/adv/to/tour?page=").append(count);
			if (keyword.trim().length() != 0)
				sBuilder.append("&keyword=").append(keyword);
			sBuilder.append("\">").append(count).append("</a></li>");
			count++;
		}

		sBuilder.append("<li class=\"page-item ");
		if (currentPage == maxPage)
			sBuilder.append("disabled");
		sBuilder.append("\"><a class=\"page-link ");
		sBuilder.append("\" href=\"/adv/to/tour?page=").append(currentPage + 1);
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
		sBuilder.append("<p class=\"fw-normal fs-6\">")
				.append(tour.getLastUpdatedOperator().getFirstname() == null ? "" : tour.getLastUpdatedOperator().getFullName())
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
				"<a href=\"[DONVITOUR]\" class=\"fw-bold text-end col\"> Quản lý các đơn vị tour <i class=\"bi-arrow-right\"></i></a>");
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

		sBuilder.append("<a href=\"[form-tour.html]\" class=\"fw-bold col-md-1 mt-3 ms-auto text-end fs-3 ")
				.append(tourUnitsQuant == 0 ? "me-5" : "me-0").append(" pe-1\">");
		sBuilder.append("<i class=\"bi bi-gear-fill text-warning\"></i>");
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
		sBuilder.append("<form action=\"/adv/to/tour/tour_detail?action=del&tourid=").append(tourId)
				.append("\" method=\"post\">");
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
				"<input class=\"form-control d-none\" type=\"file\" id=\"image-input\" placeholder=\"Ảnh\" multiple");
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
		sBuilder.append("<option value=\"1\">Du lịch biển</option>");
		sBuilder.append("<option value=\"2\">Du lịch núi</option>");
		sBuilder.append("<option value=\"3\">Du lịch văn hóa</option>");
		sBuilder.append("<option value=\"4\">Du lịch ẩm thực</option>");
		sBuilder.append("<option value=\"5\">Du lịch sinh thái</option>");
		sBuilder.append("<option value=\"6\">Du lịch mạo hiểm</option>");
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
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleCar\" value=\"Ô tô\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleCar\">Ô tô</label>");
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
				"<input name=\"vehicle\" class=\"form-check-input\" type=\"checkbox\" id=\"vehicleShip\" value=\"Tàu thủy\" checked>");
		sBuilder.append("<label class=\"form-check-label\" for=\"vehicleShip\">Tàu thuỷ</label>");
		sBuilder.append("</div>");
		sBuilder.append(
				"<div class=\"small text-danger mt-1 fs-6\" style=\"display: none;\">Chọn ít nhất 1 phương tiện.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row mb-3 d-flex justify-content-center justify-content-md-end\">");
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
		sBuilder.append("<div class=\"modal-header\">");
		sBuilder.append(
				"<h5 class=\"modal-title text-danger fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận thoát</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\"");
		sBuilder.append("aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("Thông tin tour sẽ không được lưu?");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"button\" class=\"btn btn-primary\">Xác nhận</button>");
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
}
