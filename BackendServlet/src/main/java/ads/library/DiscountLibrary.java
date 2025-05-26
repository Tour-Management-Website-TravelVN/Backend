package ads.library;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ads.objects.Discount;
import ads.user.DiscountFunctionImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscountLibrary {
	public static String viewDiscount(HttpServletRequest request) {
		String page = request.getParameter("page");
		
		int pageNumber = 1;
		if(!StringUtils.isBlank(page)) pageNumber = Integer.parseInt(page);
		
		StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("<main id=\"main\" class=\"main\">");
		sBuilder.append("<div class=\"pagetitle d-flex\">");
		sBuilder.append("<h1>Danh sách chương trình giảm giá</h1>");
		sBuilder.append("<nav class=\"ms-auto\">");
		sBuilder.append("<ol class=\"breadcrumb\">");
		sBuilder.append("<li class=\"breadcrumb-item\"><i class=\"bi bi-house-door\"></i></a></li>");
		sBuilder.append("<li class=\"breadcrumb-item\">Chương trình giảm giá</li>");
		sBuilder.append("</ol>");
		sBuilder.append("</nav>");
		sBuilder.append("</div><!-- End Page Title -->");
		sBuilder.append("<section class=\"section\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-lg-12\">");
		sBuilder.append("<div class=\"card\">");
		sBuilder.append("<div class=\"card-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between mt-3\">");
		sBuilder.append("<!-- <div class=\"btn btn-info fw-bold me-2 ms-auto text-white\"> <i class=\"bi bi-eye-fill\"></i>");
		sBuilder.append("Sửa hiển thị </div> -->");
		sBuilder.append("<select class=\"form-select fw-bold w-25 text-secondary\" id=\"page\">");
		
		int maxPage = DiscountFunctionImpl.getInstance().getMaxPage();
		String temp = "";
		
		log.info("MAX PAGE: {}", maxPage);
		log.info("CUR PAGE: {}", pageNumber);
		
		for (int i = 1; i <= maxPage; i++) {
			temp = (i==pageNumber)?"selected":"";
			sBuilder.append(String.format("<option value=\"/adv/to/discount?page=%d\" %s>Trang %d</option>", i, temp, i));
		}
		
//		sBuilder.append("<option value=\"/adv/to/discount?page=1\" selected>Trang 1</option>");
//		sBuilder.append("<option value=\"2\">Trang 2</option>");
		
		sBuilder.append("</select>");
		sBuilder.append("<div class=\"btn btn-success fw-bold me-2 ms-auto\" data-bs-toggle=\"modal\" data-bs-target=\"#addDis\"> <i");
		sBuilder.append("class=\"bi bi-plus-circle-fill\"></i>");
		sBuilder.append("Thêm giảm giá </div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Table with stripped rows -->");
		sBuilder.append("<table class=\"table datatableDis\">");
		sBuilder.append("<thead>");
		sBuilder.append("<tr>");
		sBuilder.append("<th style=\"width: 10%;\">ID</th>");
		sBuilder.append("<th style=\"width: 50%;\">Chương trình giảm giá</th>");
		sBuilder.append("<th style=\"width: 20%;\">Giá trị</th>");
		sBuilder.append("<!-- <th style=\"width: 10%;\">Đơn vị</th> -->");
		sBuilder.append("<!-- <th data-type=\"date\" data-format=\"YYYY/DD/MM\">Start Date</th> -->");
		sBuilder.append("<th style=\"width: 10%;\">Tùy chọn</th>");
		sBuilder.append("</tr>");
		sBuilder.append("</thead>");
		sBuilder.append("<tbody>");
		
		List<Discount> discounts = DiscountFunctionImpl.getInstance().getDiscounts(pageNumber);
		discounts.forEach(discount -> {
			sBuilder.append("<tr>");
			sBuilder.append(String.format("<td class=\"disId\" data-id=\"%d\">%d</td>", discount.getId(), discount.getId()));
			sBuilder.append(String.format("<td class=\"disName\" data-name=\"%s\">%s</td>", discount.getDiscountName(), discount.getDiscountName()));
			sBuilder.append("<td align=\"center\" class=\"text-justify disValue\" data-value=\"")
			.append(discount.getDiscountValue()).append("\"")
			.append(String.format(" data-unit=\"%s\">%s</td>", discount.getDiscountUnit(), discount.getValue()));
			sBuilder.append("<td class=\"d-flex justify-content-evenly\" data-order=\"null\" data-search=\"null\">");
			sBuilder.append("<button type=\"button\" class=\"btn btn-primary btnUpdate\"><i class=\"bi bi-pencil-fill\"></i></button>");
			sBuilder.append("<button type=\"button\" class=\"btn btn-danger btnDel\"><i class=\"bi bi-trash3-fill\"></i></button>");
			sBuilder.append("</td>");
			sBuilder.append("</tr>");
		});
		
//		sBuilder.append("<tr>");
//		sBuilder.append("<td class=\"disId\" data-id=\"1\">1</td>");
//		sBuilder.append("<td class=\"disName\" data-name=\"Giảm giá 50%\">Giảm giá 50%</td>");
//		sBuilder.append("<td align=\"center\" class=\"text-justify disValue\" data-value=\"50\" data-unit=\"%\">");
//		sBuilder.append("50%</td>");
//		sBuilder.append("<!-- <td align=\"center\">");
//		sBuilder.append("%");
//		sBuilder.append("</td> -->");
//		sBuilder.append("<td class=\"d-flex justify-content-evenly\" data-order=\"null\" data-search=\"null\">");
//		sBuilder.append("<button type=\"button\" class=\"btn btn-primary btnUpdate\"><i class=\"bi bi-pencil-fill\"></i></button>");
//		sBuilder.append("<button type=\"button\" class=\"btn btn-danger btnDel\"><i class=\"bi bi-trash3-fill\"></i></button>");
//		sBuilder.append("</td>");
//		sBuilder.append("</tr>");
		
		
		sBuilder.append("</tbody>");
		sBuilder.append("</table>");
		sBuilder.append("<!-- Modal Add Fes-->");
		sBuilder.append("<div class=\"modal fade\" id=\"addDis\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-md modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content py-4 px-3 rounded-5\">");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center\">");
		sBuilder.append("<h3 class=\"text-success fw-bold\">Thêm chương trình giảm giá</h3>");
		sBuilder.append("<button class=\"btn-close fs-2\" data-bs-dismiss=\"modal\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<form class=\"needs-validation\" id=\"formAdd\" novalidate>");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control\" id=\"discountName\" required>");
		sBuilder.append("<label>Tên chương trình giảm giá</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài nằm trong khoảng 10 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"number\" class=\"form-control\" id=\"discountValue\" required>");
		sBuilder.append("<label for=\"floatingInput\">Giá trị</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Giá trị không phù hợp.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto text-center position-relative\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append("<select class=\"form-select\" id=\"discountUnit\" aria-label=\"Floating label select example\">");
		sBuilder.append("<option value=\"VND\" selected>VND</option>");
		sBuilder.append("<option value=\"%\">%</option>");
		sBuilder.append("</select>");
		sBuilder.append("<label for=\"floatingSelect\">Đơn vị</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"d-flex justify-content-center\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger me-1\" style=\"width: 30%;\"");
		sBuilder.append("data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"submit\" class=\"btn btn-success ms-1\" style=\"width: 30%;\">Thêm</button>");
		sBuilder.append("</div>");
		sBuilder.append("</form>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<!-- Modal Update Dis-->");
		sBuilder.append("<div class=\"modal\" id=\"updateDis\" data-bs-backdrop=\"static\" data-bs-keyboard=\"false\" tabindex=\"-1\"");
		sBuilder.append("aria-labelledby=\"staticBackdropLabel\" aria-hidden=\"true\">");
		sBuilder.append("<div class=\"modal-dialog modal-md modal-dialog-centered\">");
		sBuilder.append("<div class=\"modal-content py-4 px-3 rounded-5\">");
		sBuilder.append("<div class=\"modal-body\">");
		sBuilder.append("<div class=\"d-flex justify-content-between align-items-center\">");
		sBuilder.append("<h3 class=\"text-primary fw-bold\">Sửa chương trình giảm giá</h3>");
		sBuilder.append("<button class=\"btn-close fs-2\" data-bs-dismiss=\"modal\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<hr>");
		sBuilder.append("<form class=\"needs-validation\" id=\"formUpdate\">");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col-2\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control border-0 ms-1\" id=\"discountId2\" value=\"1\"");
		sBuilder.append("readonly required>");
		sBuilder.append("<label>Mã</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"text\" class=\"form-control\" id=\"discountName2\" required>");
		sBuilder.append("<label>Tên chương trình giảm giá</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Độ dài nằm trong khoảng 10 đến 255 ký tự</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"row\">");
		sBuilder.append("<div class=\"col\">");
		sBuilder.append("<div class=\"form-floating mb-3\">");
		sBuilder.append("<input type=\"number\" class=\"form-control\" id=\"discountValue2\" required>");
		sBuilder.append("<label for=\"floatingInput\">Giá trị</label>");
		sBuilder.append("<div class=\"invalid-feedback\">Giá trị không phù hợp.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"col-auto text-center position-relative\">");
		sBuilder.append("<div class=\"form-floating\">");
		sBuilder.append("<select class=\"form-select\" id=\"discountUnit2\" aria-label=\"Floating label select example\">");
		sBuilder.append("<option value=\"VND\" selected>VND</option>");
		sBuilder.append("<option value=\"%\">%</option>");
		sBuilder.append("</select>");
		sBuilder.append("<label for=\"floatingSelect\">Đơn vị</label>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
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
		sBuilder.append("<h5 class=\"modal-title text-danger fw-bold fs-4\" id=\"staticBackdropLabel\">Xác nhận xóa</h5>");
		sBuilder.append("<button type=\"button\" class=\"btn-close fs-3\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"modal-body\">Bạn có muốn xóa &lt;Tên chương trình giảm giá&gt;?</div>");
		sBuilder.append("<div class=\"modal-footer\">");
		sBuilder.append("<button type=\"button\" class=\"btn btn-danger\" data-bs-dismiss=\"modal\">Hủy</button>");
		sBuilder.append("<button type=\"button\" class=\"btn btn-primary\" id=\"btnDelete\">Xác nhận</button>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</div>");
		sBuilder.append("</section>");
		sBuilder.append("<div class=\"toast position-fixed bottom-0 end-0\" style=\"z-index: 999999;\" id=\"myToast\" role=\"alert\"");
		sBuilder.append("aria-live=\"assertive\" aria-atomic=\"true\">");
		sBuilder.append("<div class=\"toast-header bg-primary\">");
		sBuilder.append("<strong class=\"me-auto text-warning\">Thông báo</strong>");
		sBuilder.append("<small class=\"text-white\">Vừa xong</small>");
		sBuilder.append("<button type=\"button\" class=\"btn-close btn-close-white\" data-bs-dismiss=\"toast\" aria-label=\"Close\"></button>");
		sBuilder.append("</div>");
		sBuilder.append("<div class=\"toast-body bg-white\">Đã cập nhật thông tin tour.</div>");
		sBuilder.append("</div>");
		sBuilder.append("</main><!-- End #main -->");
		
		return sBuilder.toString();
	}
}
