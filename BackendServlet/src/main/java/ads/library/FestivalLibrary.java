package ads.library;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ads.objects.Festival;
import ads.user.DiscountFunctionImpl;
import ads.user.FestivalFunctionImpl;
import jakarta.servlet.http.HttpServletRequest;

public class FestivalLibrary {
	public static String viewFestival(HttpServletRequest request) {
		String page = request.getParameter("page");

		int pageNumber = 1;
		if (!StringUtils.isBlank(page))
			pageNumber = Integer.parseInt(page);

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<main id=\"main\" class=\"main\">");
		sBuilder.append("<div class=\"pagetitle d-flex\">");
		sBuilder.append("<h1>Danh sách lễ hội</h1>");
		sBuilder.append("<nav class=\"ms-auto\">");
		sBuilder.append("<ol class=\"breadcrumb\">");
		sBuilder.append("<li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		sBuilder.append("<li class=\"breadcrumb-item\">Quản lý lễ hội</li>");
		sBuilder.append("</ol>");
		sBuilder.append("</nav>");
		sBuilder.append("</div><!-- End Page Title -->");
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-lg-12\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<div class=\"d-flex mt-3\">");
		
		sBuilder.append("<select class=\"form-select fw-bold w-25 text-secondary\" id=\"page\">");
		
		int maxPage = FestivalFunctionImpl.getInstance().getMaxPage();
		String temp = "";
		
		for (int i = 1; i <= maxPage; i++) {
			temp = (i==pageNumber)?"selected":"";
			sBuilder.append(String.format("<option value=\"/adv/to/festival?page=%d\" %s>Trang %d</option>", i, temp, i));
		}
		
		sBuilder.append("</select>");
		
		sBuilder.append(
				"<div class=\"btn btn-success fw-bold me-2 ms-auto\" data-bs-toggle=\"modal\" data-bs-target=\"#addFes\"><i class=\"bi bi-plus-circle-fill me-2\"></i>Thêm lễ hội </div>");
		sBuilder.append("</div>");
		sBuilder.append("<table class=\"table datatable\">");
		sBuilder.append("<thead>");
		sBuilder.append("<tr>");
		sBuilder.append("<th style=\"width: 5%;\">ID</th>");
		sBuilder.append("<th style=\"width: 20%;\">Tên lễ hội</th>");
		sBuilder.append("<th style=\"width: 30%;\">Mô tả</th>");
		sBuilder.append("<th style=\"width: 10%;\">Hiển thị</th>");
		sBuilder.append("<!-- <th data-type=\"date\" data-format=\"YYYY/DD/MM\">Start Date</th> -->");
		sBuilder.append("<th style=\"width: 5%;\">Tùy chọn</th>");
		sBuilder.append("</tr>");
		sBuilder.append("</thead>");
		sBuilder.append("<tbody>");
		
		List<Festival> festivals = FestivalFunctionImpl.getInstance().getFestivals(pageNumber);
		festivals.forEach(festival -> {
			sBuilder.append("<tr>");
			sBuilder.append(
					"<td data-bs-toggle=\"modal\" data-bs-target=\"#viewFes\" style=\"cursor: pointer;\" class=\"fesId\" data-id=\"").append(festival.getId()).append("\">").append(festival.getId()).append("</td>");
			sBuilder.append(
					"<td data-bs-toggle=\"modal\" data-bs-target=\"#viewFes\" style=\"cursor: pointer;\" class=\"fesName\">").append(festival.getFestivalName()).append("</td>");
			sBuilder.append(
					"<td class=\"text-justify fesDes\" data-des=\"").append(festival.getDescription()).append("\" data-bs-toggle=\"modal\" data-bs-target=\"#viewFes\" style=\"cursor: pointer;\">")
			.append((festival.getDescription().length()>=30)?festival.getDescription().substring(0, 30)+"...":festival.getDescription()).append("</td>");
			sBuilder.append("<td align=\"center\" data-order=\"").append(festival.getDisplayStatus()?"Hiện":"Ẩn").append("\" data-search=\"").append(festival.getDisplayStatus()?"Hiện":"Ẩn").append("\" class=\"status\" style=\"cursor: pointer;\">");
			sBuilder.append("<i class=\"bi bi-eye text-primary fs-4 ").append(festival.getDisplayStatus()?"":"d-none").append("\"></i>");
			sBuilder.append("<i class=\"bi bi-eye-slash text-danger fs-4 ").append(festival.getDisplayStatus()?"d-none":"").append("\"></i>");
			sBuilder.append("</td>");
			sBuilder.append("<td class=\"d-flex justify-content-evenly\">");
			sBuilder.append("<button type=\"button\" class=\"btn btn-primary btnUpdate\" data-bs-toggle=\"modal\"");
			sBuilder.append("data-bs-target=\"#updateFes\"><i class=\"bi bi-pencil-fill\"></i></button>");
			sBuilder.append(
					"<button type=\"button\" class=\"btn btn-danger btnDel\"><i class=\"bi bi-trash3-fill\"></i></button>");
			sBuilder.append("</td>");
			sBuilder.append("</tr>");
		});
		
		sBuilder.append("</tbody>");
		sBuilder.append("</table>");
		sBuilder.append("<!-- Modal Add Fes-->");
		sBuilder.append(
				"<div class=\"modal fade\" id=\"addFes\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-lg modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content py-4 px-3 rounded-5\">");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center\">");
		sBuilder.append("<h3 class=\"text-success fw-bold\">Thêm lễ hội</h3>");
		sBuilder.append("<button class=\"btn-close fs-2\" data-bs-dismiss=\"modal\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<form class=\"needs-validation\" novalidate id=\"formAdd\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control\" id=\"festiName\" required>");
		sBuilder.append("<label for=\"floatingInput\">Tên lễ hội</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Từ 10 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto text-center position-relative\">");
		sBuilder.append("<small class=\"text-secondary\">Hiển thị</small><br>");
		sBuilder.append("<i class=\"bi bi-eye text-primary fs-3 mb-5\"></i>");
		sBuilder.append("<i class=\"bi bi-eye-slash text-danger d-none fs-3 mb-5\"></i>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<textarea class=\"form-control\" style=\"height: 200px;\" id=\"festiDes\"></textarea>");
		sBuilder.append("<label>Mô tả</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"invalid-feedback\">Please fill out this field.</div>");
		sBuilder.append("<div class=\"d-flex justify-content-center\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger me-1\" style=\"width: 30%;\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"submit\" class=\"btn btn-success ms-1\" style=\"width: 30%;\">Thêm</button>");
		sBuilder.append("</div>");
		sBuilder.append("</form>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal View Fes-->");
		sBuilder.append(
				"<div class=\"modal\" id=\"viewFes\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-lg modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content py-4 px-3 rounded-5\">");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center\">");
		sBuilder.append("<h3 class=\"text-dark fw-bold\">Thông tin chi tiết</h3>");
		sBuilder.append("<button class=\"btn-close fs-2\" data-bs-dismiss=\"modal\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<form class=\"needs-validation\" id=\"formView\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-2\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control border-0 ms-0\" id=\"festivalId\" value=\"1\" readonly>");
		sBuilder.append("<label for=\"floatingInput\">Mã lễ hội</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control border-0 ms-1\" id=\"festivalName\" value=\"Hoa Cỏ\" readonly>");
		sBuilder.append("<label for=\"floatingInput\">Tên lễ hội</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto text-center position-relative\">");
		sBuilder.append("<small class=\"text-secondary\">Hiển thị</small><br>");
		sBuilder.append("<i class=\"bi bi-eye text-primary fs-3 mb-5\"></i>");
		sBuilder.append("<i class=\"bi bi-eye-slash text-danger d-none fs-3 mb-5\"></i>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append(
				"<textarea class=\"form-control border-0 ms-0\" style=\"height: 200px;\" id=\"festivalDes\" readonly>poldih</textarea>");
		sBuilder.append("<label>Mô tả</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"d-flex justify-content-center\">");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-danger me-1 btnDel\" style=\"width: 30%;\">Xóa</button>");
		sBuilder.append(
				"<button type=\"button\" class=\"btn btn-primary ms-1\" style=\"width: 30%;\" ");
		sBuilder.append(" id=\"btnChange\">Sửa</button>");
		sBuilder.append("</div>");
		sBuilder.append("</form>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal Update Fes-->");
		sBuilder.append(
				"<div class=\"modal\" id=\"updateFes\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-lg modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content py-4 px-3 rounded-5\">");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center\">");
		sBuilder.append("<h3 class=\"text-primary fw-bold\">Sửa lễ hội</h3>");
		sBuilder.append("<button class=\"btn-close fs-2\" data-bs-dismiss=\"modal\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<form class=\"needs-validation\" novalidate id=\"formUpdate\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-2\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append(
				"<input type=\"text\" class=\"form-control border-0 ms-0\" value=\"1\" id=\"festiId2\" readonly>");
		sBuilder.append("<label for=\"floatingInput\">Mã lễ hội</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control\" id=\"festiName2\">");
		sBuilder.append("<label for=\"floatingInput\">Tên lễ hội</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Từ 10 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto text-center position-relative\">");
		sBuilder.append("<small class=\"text-secondary\">Hiển thị</small><br>");
		sBuilder.append("<i class=\"bi bi-eye text-primary fs-3 mb-5\"></i>");
		sBuilder.append("<i class=\"bi bi-eye-slash text-danger d-none fs-3 mb-5\"></i>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"invalid-feedback\">Please fill out this field.</div>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<textarea class=\"form-control\" style=\"height: 200px;\" id=\"festiDes2\"></textarea>");
		sBuilder.append("<label>Mô tả</label>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"invalid-feedback\">Please fill out this field.</div>");
		sBuilder.append("<div class=\"d-flex justify-content-center\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger me-1\" style=\"width: 30%;\"");
		sBuilder.append("data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"submit\" class=\"btn btn-primary ms-1\" style=\"width: 30%;\">Sửa</button>");
		sBuilder.append("</div>");
		sBuilder.append("</form>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
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
		sBuilder.append("<div class=\"modal-body\">Bạn có muốn xóa &lt;Tên lễ hội&gt;?</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"button\" class=\"btn btn-primary btnDelete\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");
		sBuilder.append(
				"<div class=\"toast position-fixed bottom-0 end-0\" style=\"z-index: 999999;\" id=\"myToast\" role=\"alert\"");
		sBuilder.append("aria-live=\"assertive\" aria-atomic=\"true\">");
		sBuilder.append("<div class=\"toast-header bg-primary\">");
		sBuilder.append("<strong class=\"me-auto text-warning\">Thông báo</strong>");
		sBuilder.append("<small class=\"text-white\">Vừa xong</small>");
		sBuilder.append(
				"<button type=\"button\" class=\"btn-close btn-close-white\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"toast-body bg-white\">Đã cập nhật thông tin tour.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</main><!-- End #main -->");
		return sBuilder.toString();
	}
}
