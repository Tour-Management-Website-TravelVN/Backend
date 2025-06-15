package ads.to;

import java.io.IOException;


import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ads.objects.Discount;
import ads.objects.Festival;
import ads.objects.TourUnit;
import ads.user.DiscountFunctionImpl;
import ads.user.FestivalFunctionImpl;
import ads.user.TourFunctionImpl;
import ads.user.TourGuideFunctionImpl;
import ads.user.TourOperatorFunctionImpl;
import ads.user.TourUnitFunctionImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/to/tour/Tour-Unit-Edit")
public class TourUnitEditServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");

		 PrintWriter out = response.getWriter();
		 RequestDispatcher h = request.getRequestDispatcher("/he");
		 RequestDispatcher side = request.getRequestDispatcher("/side");
		 RequestDispatcher foot = request.getRequestDispatcher("/fo");
		 out.append("<!DOCTYPE html>");
			out.append("<html lang=\"en\">");
			out.append("");
			out.append("<head>");
			out.append("  <meta charset=\"utf-8\">");
			out.append("  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
			out.append("");
			out.append("  <title>Sửa đơn vị tour</title>");
			out.append("  <meta content=\"\" name=\"description\">");
			out.append("  <meta content=\"\" name=\"keywords\">");
			out.append("");
			out.append("  <!-- Favicons -->");
			out.append("  <link href=\"../assets/img/Logo.svg\" rel=\"icon\"> ");
			out.append("  <link href=\"../assets/img/apple-touch-icon.png\" rel=\"apple-touch-icon\">");
			out.append("");
			out.append("  <!-- Google Fonts -->");
			out.append("  <link href=\"https://fonts.gstatic.com\" rel=\"preconnect\">");
			out.append("  <link href='https://fonts.googleapis.com/css?family=Be Vietnam Pro' rel='stylesheet'>");
			out.append(
					"  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i&subset=vietnamese\" rel=\"stylesheet\">");
			out.append("");
			out.append("  <!-- Vendor CSS Files -->");
			out.append("  <link href=\"../../assets/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/bootstrap-icons/bootstrap-icons.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/boxicons/css/boxicons.min.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/quill/quill.snow.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/quill/quill.bubble.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/remixicon/remixicon.css\" rel=\"stylesheet\">");
			out.append("  <link href=\"../../assets/vendor/simple-datatables/style.css\" rel=\"stylesheet\">");
			out.append("");
			out.append("");
			out.append("  <!-- Template Main CSS File -->");
			out.append("  <link href=\"../../css/style.css\" rel=\"stylesheet\">");
		 h.include(request, response);
		 side.include(request, response);
		 TourUnit tu = TourUnitFunctionImpl.getInstance().getById(request.getParameter("tourUnitId"));
		 List<Festival> list_fes = FestivalFunctionImpl.getInstance().getFestivals(1);
		 log.info(list_fes.toString());
		 out.append("  <main id=\"main\" class=\"main\">");
		 out.append("");
		 out.append("    <div class=\"pagetitle d-flex\">");
		 out.append("      <h1>Sửa đơn vị tour</h1>");
		 out.append("      <nav class=\"ms-auto\">");
		 out.append("        <ol class=\"breadcrumb\">");
		 out.append("          <li class=\"breadcrumb-item\"><a href=\"index.html\"><i class=\"bi bi-house-door\"></i></a></li>");
		 out.append("          <li class=\"breadcrumb-item\">Quản lý tour</li>");
		 out.append("          <li class=\"breadcrumb-item active\">Sửa đơn vị tour</li>");
		 out.append("        </ol>");
		 out.append("      </nav>");
		 out.append("    </div><!-- End Page Title -->");
		 out.append("");
		 out.append("    <section class=\"section\">");
		 out.append("      <div class=\"row\">");
		 out.append("        <div class=\"col\">");
		 out.append("<!-- ...existing code... -->");
		 out.append("<div class=\"card\">");
		 out.append("  <div class=\"card-body\">");
		 out.append("    <h5 class=\"card-title\">Nhập thông tin mới cho đơn vị tour</h5>");
		 out.append("");
		 out.append("<div class=\"container-fluid\">");
		 out.append("<form action=\"Tour-Unit-Edit?tourId="+tu.getTour().getTourId()+"\" method=\"post\" id=\"edit\">");

		 out.append("<div class=\"row g-3\">");

		 out.append("<div class=\"col-12 col-md-6\">");
		 out.append("<div class=\"form-floating\">");
		 out.append("<input type=\"text\" value=\""+tu.getTourUnitId()+"\" class=\"form-control\" id=\"tourUnitId\" name=\"tourUnitId\" placeholder=\"ID tour\" readonly>");
		 out.append("<label for=\"tourUnitId\">ID tour unit</label>");
		 out.append("</div>");
		 out.append("</div>");

		 out.append("<div class=\"col-12 col-md-6\">");
		 out.append("<div class=\"form-floating\">");
		 out.append("<select class=\"form-select fw-bold\" id=\"festival\" name=\"festival\">");
		 for(Festival f : list_fes){
		     out.append("<option value=\""+f.getId()+"\">"+f.getFestivalName()+"</option>");
		 }
		 out.append("</select>");
		 out.append("<label for=\"festival\">Lễ hội</label>");
		 out.append("</div>");
		 out.append("</div>");

		 out.append("</div>"); // end row

		 out.append("<div class=\"form-floating my-3\">");
		 out.append("<input type=\"text\" class=\"form-control\" value=\""+tu.getTour().getTourName()+"\" id=\"tourName\" name=\"tourName\" placeholder=\"Tên tour\" readonly>");
		 out.append("<label for=\"tourName\">Tên tour</label>");
		 out.append("</div>");
		 ArrayList<Discount> list_dis = (ArrayList<Discount>) DiscountFunctionImpl.getInstance().getDiscounts(1);

		 out.append("  <div class=\"row\">");

		// Chương trình giảm giá
		out.append("    <div class=\"form-floating mb-3 col-6 col-md-3\">");
		out.append("      <select class=\"form-control form-control-sm fw-bold\" id=\"discount\" name=\"discount\">");
		for (Discount d : list_dis) {
		    out.append("        <option value=\"" + d.getId() + "\">" + d.getDiscountName() + "</option>");
		}
		out.append("        <option value=\"null\">Không giảm giá</option>");
		out.append("      </select>");
		out.append("      <label for=\"discount\" class=\"ms-2\">Chương trình giảm giá</label>");
		out.append("    </div>");

		// Chỗ
		out.append("    <div class=\"form-floating mb-3 col-6 col-md-3\">");
		out.append("      <input type=\"text\" class=\"form-control\" value=\"" + tu.getMaximumCapacity() + "\" id=\"availableCapacity\" name=\"availableCapacity\" placeholder=\"Chỗ\">");
		out.append("      <label for=\"availableCapacity\" class=\"ms-2\">Chỗ</label>");
		out.append("      <div class=\"invalid-feedback\" id=\"tourIncluded-feedback\"></div>");
		out.append("    </div>");

		// Ngày khởi hành
		out.append("    <div class=\"form-floating mb-3 col-6 col-md-3\">");
		out.append("      <input type=\"date\" class=\"form-control\" id=\"departure\" name=\"departure\" value=\"" + tu.getDepartureDate() + "\">");
		out.append("      <label for=\"departure\" class=\"ms-2\">Ngày khởi hành</label>");
		out.append("      <div class=\"invalid-feedback\" id=\"departure-feedback\"></div>");
		out.append("    </div>");

		// Ngày về
		out.append("    <div class=\"form-floating mb-3 col-6 col-md-3\">");
		out.append("      <input type=\"date\" class=\"form-control\" id=\"return\" name=\"return\" value=\"" + tu.getReturnDate() + "\">");
		out.append("      <label for=\"return\" class=\"ms-2\">Ngày về</label>");
		out.append("      <div class=\"invalid-feedback\" id=\"return-feedback\"></div>");
		out.append("    </div>");

		out.append("  </div>");


		 // Các phần input giá + chi phí
		 String[] prices = {
		     "adultprice:Giá người lớn", "childprice:Giá trẻ em", "toldlerprice:Giá trẻ em 6-10 tuổi", "babyprice:Giá trẻ sơ sinh"
		 };
		 String[] costs = {
		     "adultcost:Chi phí người lớn", "childcost:Chi phí trẻ em", "toldlercost:Chi phí trẻ em 6-10 tuổi", "babycost:Chi phí trẻ sơ sinh"
		 };

		 out.append("<div class=\"row g-3\">");
		 for(String p : prices){
		     String[] parts = p.split(":");
		     String id = parts[0];
		     String label = parts[1];
		     String value = "";
		     switch(id){
		         case "adultprice": value = tu.getAdultTourPrice().toString(); break;
		         case "childprice": value = tu.getChildTourPrice().toString(); break;
		         case "toldlerprice": value = tu.getToddlerTourPrice().toString(); break;
		         case "babyprice": value = tu.getBabyTourPrice().toString(); break;
		     }
		     out.append("<div class=\"form-floating col-12 col-md-3\">");
		     out.append("<input type=\"text\" class=\"form-control\" id=\""+id+"\" name=\""+id+"\" placeholder=\""+label+"\" value=\""+value+"\" "+(id.equals("adultprice")?"oninput=\"inputprice()\"":"readonly")+">");
		     out.append("<label for=\""+id+"\" class=\"ms-2\">"+label+"</label>");
		     out.append("</div>");
		 }
		 out.append("</div>");

		 out.append("<div class=\"row g-3\">");
		 for(String c : costs){
		     String[] parts = c.split(":");
		     String id = parts[0];
		     String label = parts[1];
		     String value = "";
		     switch(id){
		         case "adultcost": value = tu.getAdultTourCost().toString(); break;
		         case "childcost": value = tu.getChildTourCost().toString(); break;
		         case "toldlercost": value = tu.getToddlerTourCost().toString(); break;
		         case "babycost": value = tu.getBabyTourCost().toString(); break;
		     }
		     out.append("<div class=\"form-floating col-12 col-md-3\">");
		     out.append("<input type=\"text\" class=\"form-control\" id=\""+id+"\" name=\""+id+"\" placeholder=\""+label+"\" value=\""+value+"\" "+(id.equals("adultcost")?"oninput=\"inputcost()\"":"")+" required>");
		     out.append("<label for=\""+id+"\" class=\"ms-2\">"+label+"</label>");
		     out.append("</div>");
		 }
		 out.append("</div>");

		 // Private Room & Additional Cost
		 out.append("<div class=\"row g-3\">");

		 out.append("<div class=\"form-floating col-12 col-md-6\">");
		 out.append("<input type=\"text\" class=\"form-control\" id=\"privateRoom\" name=\"privateRoom\" placeholder=\"Giá phòng riêng\" value=\""+tu.getPrivateRoomPrice()+"\" required>");
		 out.append("<label for=\"privateRoom\" class=\"ms-2\">Giá phòng riêng</label>");
		 out.append("<div class=\"invalid-feedback\" id=\"privateroom-feedback\"></div>");
		 out.append("</div>");

		 out.append("<div class=\"form-floating col-12 col-md-6\">");
		 out.append("<input type=\"text\" class=\"form-control\" id=\"additionalCost\" name=\"additionalCost\" placeholder=\"Chi phí phát sinh\" value=\""+tu.getTotalAdditionalCost()+"\" required>");
		 out.append("<label for=\"additionalCost\" class=\"ms-2\">Chi phí phát sinh</label>");
		 out.append("<div class=\"invalid-feedback\" id=\"totaladditional-feedback\"></div>");
		 out.append("</div>");

		 out.append("</div>");

		 // Nút hành động
		 out.append("<div class=\"row mt-4\">");

		 out.append("<div class=\"col-6 col-md-3\">");
		 out.append("<button type=\"button\" class=\"btn btn-danger w-100\" data-bs-toggle=\"modal\" data-bs-target=\"#cancelModal\">Huỷ bỏ</button>");
		 out.append("</div>");

		 out.append("<div class=\"col-6 col-md-3 ms-auto\">");
		 out.append("<button type=\"submit\" class=\"btn btn-primary w-100\">Sửa tour unit</button>");
		 out.append("</div>");

		 out.append("</div>");

		 out.append("</form>");
		 out.append("</div>");

		 out.append("  </div>");
		 out.append("</div>");
		 out.append("<!-- ...existing code... -->");
		 out.append("              </form><!-- End General Form Elements -->");
		 out.append("");
		 out.append("            </div>");
		 out.append("          </div>");
		 out.append("");
		 out.append("        </div>");
		 out.append("");
		 out.append("        ");
		 out.append("      </div>");
		 out.append("    </section>");
		 out.append("");
		 out.append("  </main><!-- End #main -->");
		 out.append("<script>");
		  out.append("  function inputprice() {");
		  out.append("    var adultprice = document.getElementById(\"adultprice\").value;");
		out.append("");
		  out.append("    document.getElementById(\"childprice\").value = adultprice * 0.9;");
		  out.append("    document.getElementById(\"toldlerprice\").value = adultprice * 0.8;");
		  out.append("    document.getElementById(\"babyprice\").value = adultprice * 0;");
		out.append("");
		out.append("");
		  out.append("  }");
		  out.append("  function inputcost() {");
		  out.append("    var adultcost = document.getElementById(\"adultcost\").value;");
		out.append("");
		  out.append("    document.getElementById(\"childcost\").value = adultcost * 0.9;");
		  out.append("    document.getElementById(\"toldlercost\").value = adultcost * 0.8;");
		  out.append("    document.getElementById(\"babycost\").value = adultcost * 0;");
		out.append("");
		out.append("");
		  out.append("  }");
		  out.append("</script>");
		  out.append("<script>");

		  char dayfix = tu.getTour().getDuration().charAt(0);
		  out.append(" const i = "+dayfix+";");
		  out.append("");
		  out.append("const departureInput = document.getElementById('departure');");
		  out.append("const returnInput = document.getElementById('return');");
		  out.append("const departureFeedback = document.getElementById('departure-feedback');");
		  out.append("const returnFeedback = document.getElementById('return-feedback');");
		  out.append("const slot = document.getElementById('availableCapacity');");
		  out.append("const slotFB = document.getElementById('tourIncluded-feedback');");
		  out.append("");
		  out.append("const adultPrice = document.getElementById('adultprice');");
		  out.append("const adultpriceFB = document.getElementById('adultprice-feedback');");
		  out.append("const adultcost = document.getElementById('adultcost');");
		  out.append("const adultcostFB = document.getElementById('adultcost-feedback');");
		  out.append("const privateroom = document.getElementById('privateRoom');");
		  out.append("const privateroomFB = document.getElementById('privateroom-feedback');");
		  out.append("const totaladditional = document.getElementById('additionalCost');");
		  out.append("const totaladditionalFB = document.getElementById('totaladditional-feedback');");
		  out.append("privateroom.addEventListener('change', function () {");
		  out.append("  const room = this.value;");
		  out.append("  if(room < 0|| isNaN(room) )");
		  out.append("  {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    this.value = '';");
		  out.append("    privateroomFB.textContent = 'Giá không hợp lệ!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("  else");
		  out.append("  {");
		  out.append("    this.classList.remove('is-invalid');");
		  out.append("  }");
		  out.append("});");
		  out.append("totaladditional.addEventListener('change', function () {");
		  out.append("  const additional = this.value;");
		  out.append("  if(additional < 0)");
		  out.append("  {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    this.value = '';");
		  out.append("    totaladditionalFB.textContent = 'Giá không hợp lệ!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("  else");
		  out.append("  {");
		  out.append("    this.classList.remove('is-invalid');");
		  out.append("  }");
		  out.append("});");
		  out.append("slot.addEventListener('change', function () {");
		  out.append("  const s = this.value;");
		  out.append("  if(s > 100 || s < 10)");
		  out.append("  {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    this.value = '';");
		  out.append("    slotFB.textContent = 'Số lượng không hợp lệ! (chỉ nên từ 10-100)';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("  else");
		  out.append("  {");
		  out.append("    this.classList.remove('is-invalid');");
		  out.append("  }");
		  out.append("});");
		  out.append("adultPrice.addEventListener('change', function () {");
		  out.append("const costValue = parseFloat(adultPrice.value.trim());");
		  out.append("");
		  out.append("  if (isNaN(costValue) || costValue < 0) {");
		  out.append("    adultcost.classList.add('is-invalid');");
		  out.append("    adultcostFB.textContent = 'Giá người lớn phải là số dương hợp lệ.';");
		  out.append("    valid = false;");
		  out.append("  } else {");
		  out.append("    adultcost.classList.remove('is-invalid');");
		  out.append("    adultcost.classList.add('is-valid');  ");
		  out.append("    adultcostFB.textContent = '';");
		  out.append("  }");
		  out.append("  const price = this.value;");
		  out.append("  if(price <= 0 || isNaN(price))");
		  out.append("  {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    this.value = '';");
		  out.append("    adultpriceFB.textContent = 'Giá không hợp lệ!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("  else");
		  out.append("  {");
		  out.append("    this.classList.remove('is-invalid');");
		  out.append("  }");
		  out.append("});");
		  out.append("adultcost.addEventListener('change', function () {");
		  out.append("const costValue = parseFloat(this.value.trim());");
		  out.append("");
		  out.append("  if (isNaN(costValue) || costValue < 0) {");
		  out.append("    adultcost.classList.add('is-invalid');");
		  out.append("    adultcostFB.textContent = 'Giá người lớn phải là số dương hợp lệ.';");
		  out.append("    valid = false;");
		  out.append("  } else {");
		  out.append("    adultcost.classList.remove('is-invalid');");
		  out.append("    adultcost.classList.add('is-valid');  ");
		  out.append("    adultcostFB.textContent = '';");
		  out.append("  }");
		  out.append("  const cost = this.value;");
		  out.append("  if(cost <= 0 || isNaN(cost))");
		  out.append("  {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    this.value = '';");
		  out.append("    adultcostFB.textContent = 'Giá không hợp lệ!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("  else");
		  out.append("  {");
		  out.append("    this.classList.remove('is-invalid');");
		  out.append("  }");
		  out.append("});");
		  out.append("");
		  out.append("");
		  out.append("departureInput.addEventListener('change', function () {");
		  out.append("  const departureDate = new Date(this.value);");
		  out.append("  const now = new Date();");
		  out.append("  const year = departureDate.getFullYear();");
		  out.append("  let hasError = false;");
		  out.append("");
		  out.append("  this.classList.remove('is-invalid');");
		  out.append("  departureFeedback.textContent = '';");
		  out.append("");
		  out.append("  if (year > 2075) {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    departureFeedback.textContent = 'Năm khởi hành không hợp lệ!';");
		  out.append("    this.value = '';");
		  out.append("    returnInput.value = '';");
		  out.append("    hasError = true;");
		  out.append("  }");
		  out.append("");
		  out.append("  if (!hasError && departureDate < now) {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    departureFeedback.textContent = 'Ngày khởi hành đã qua, có thể gây ảnh hưởng đến các đơn vị tour!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("");
		  out.append("  if (!hasError && !isNaN(departureDate)) {");
		  out.append("    const returnDate = new Date(departureDate);");
		  out.append("    returnDate.setDate(returnDate.getDate() + i);");
		  out.append("    const yyyy = returnDate.getFullYear();");
		  out.append("    const mm = String(returnDate.getMonth() + 1).padStart(2, '0');");
		  out.append("    const dd = String(returnDate.getDate()).padStart(2, '0');");
		  out.append("    returnInput.value = `${yyyy}-${mm}-${dd}`;");
		  out.append("    returnInput.classList.remove('is-invalid');");
		  out.append("    returnFeedback.textContent = '';");
		  out.append("  }");
		  out.append("});");
		  out.append("");
		  out.append("returnInput.addEventListener('change', function () {");
		  out.append("  const returnDate = new Date(this.value);");
		  out.append("  const year = returnDate.getFullYear();");
		  out.append("  let hasError = false;");
		  out.append("");
		  out.append("  this.classList.remove('is-invalid');");
		  out.append("  returnFeedback.textContent = '';");
		  out.append("");
		  out.append("  past = new Date();");
		  out.append("  past.setDate(past.getDate() + i-1);");
		  out.append("");
		  out.append("  if (!hasError && returnDate < past) {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    returnFeedback.textContent = 'Ngày khởi hành đã qua, có thể gây ảnh hưởng đến các đơn vị tour!';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("");
		  out.append("  if (year > 2075) {");
		  out.append("    this.classList.add('is-invalid');");
		  out.append("    returnFeedback.textContent = 'Ngày về không hợp lệ!';");
		  out.append("    this.value = '';");
		  out.append("    return;");
		  out.append("  }");
		  out.append("");
		  out.append("  if (!isNaN(returnDate)) {");
		  out.append("    const departureDate = new Date(returnDate);");
		  out.append("    departureDate.setDate(departureDate.getDate() - i);");
		  out.append("    const yyyy = departureDate.getFullYear();");
		  out.append("    const mm = String(departureDate.getMonth() + 1).padStart(2, '0');");
		  out.append("    const dd = String(departureDate.getDate()).padStart(2, '0');");
		  out.append("    departureInput.value = `${yyyy}-${mm}-${dd}`;");
		  out.append("    departureInput.classList.remove('is-invalid');");
		  out.append("    departureFeedback.textContent = '';");
		  out.append("  }");
		  out.append("});");
		  out.append("const form  = document.getElementById('edit');   ");
		  out.append("const submitBtn = document.getElementById('confirm');  ");
		  out.append("");
		  out.append("function hasInvalidFields() {");
		  out.append("  return form.querySelectorAll('.is-invalid').length > 0;");
		  out.append("}");
		  out.append("");
		  out.append("form.addEventListener('submit', function (e) {");
		  out.append("");
		  out.append("  if (hasInvalidFields()) {");
		  out.append("    e.preventDefault();            ");
		  out.append("    e.stopPropagation();");
		  out.append("    const firstError = form.querySelector('.is-invalid');");
		  out.append("    if (firstError) firstError.scrollIntoView({behavior:'smooth', block:'center'});");
		  out.append("  }");
		  out.append("});");
		  out.append("</script>");
		  out.append("<!-- ...existing code... -->");
		  out.append("<!-- Modal xác nhận huỷ bỏ -->");
		  out.append("<div class=\"modal fade\" id=\"cancelModal\" tabindex=\"-1\" aria-labelledby=\"cancelModalLabel\" aria-hidden=\"true\">");
		  out.append("  <div class=\"modal-dialog modal-dialog-centered\">");
		  out.append("    <div class=\"modal-content\">");
		  out.append("      <div class=\"modal-header\">");
		  out.append("        <h5 class=\"modal-title\" id=\"cancelModalLabel\">Xác nhận huỷ bỏ</h5>");
		  out.append("        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Đóng\"></button>");
		  out.append("      </div>");
		  out.append("      <div class=\"modal-body\">");
		  out.append("        Bạn muốn huỷ bỏ tác vụ ?");
		  out.append("      </div>");
		  out.append("      <div class=\"modal-footer\">");
		  out.append("        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Không</button>");
		  out.append("        <a href=\"Tour_unit_management.html\" class=\"btn btn-danger\">Đồng ý</a>");
		  out.append("      </div>");
		  out.append("    </div>");
		  out.append("  </div>");
		  out.append("</div>");
		 foot.include(request, response);

			out.append(
					"  <a href=\"#\" class=\"back-to-top d-flex align-items-center justify-content-center\"><i class=\"bi bi-arrow-up-short\"></i></a>");
			out.append("");
			out.append("  <!-- Vendor JS Files -->");
			out.append("  <script src=\"../../assets/vendor/apexcharts/apexcharts.min.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/chart.js/chart.umd.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/echarts/echarts.min.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/quill/quill.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/simple-datatables/simple-datatables.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/tinymce/tinymce.min.js\"></script>");
			out.append("  <script src=\"../../assets/vendor/php-email-form/validate.js\"></script>");
			out.append("  <!-- Template Main JS File -->");
			out.append("  <script src=\"../../assets/js/main.js\"></script>");
			out.append("");
			out.append("</body>");
			out.append("");
			out.append("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		
		TourUnitFunctionImpl dao = TourUnitFunctionImpl.getInstance();
		TourUnit tu = new TourUnit();
		tu.setTourUnitId(req.getParameter("tourUnitId"));
		tu.setFestival(FestivalFunctionImpl.getInstance().getById(Integer.parseInt(req.getParameter("festival")) )); 
		if(!req.getParameter("discount").toString().equalsIgnoreCase("null"))
		{
			tu.setDiscount(DiscountFunctionImpl.getInstance().getById(Integer.parseInt(req.getParameter("discount")))); 
		}
		else
			tu.setDiscount(null); 
		tu.setAvailableCapacity(TourUnitFunctionImpl.getInstance().getById(tu.getTourUnitId()).getAvailableCapacity());
		tu.setMaximumCapacity(Short.parseShort(req.getParameter("availableCapacity")));
		tu.setTourUnitId(req.getParameter("tourUnitId"));
	    tu.setDepartureDate(LocalDate.parse(req.getParameter("departure")));
	    tu.setReturnDate(LocalDate.parse(req.getParameter("return")));
	    tu.setLastUpdatedTime(new Date().toInstant());
	    tu.setLastUpdatedOperator(tu.getLastUpdatedOperator());
	    tu.setAdultTourPrice(new BigDecimal(req.getParameter("adultprice")));    
	    tu.setChildTourPrice(new BigDecimal(req.getParameter("childprice")));
	    tu.setToddlerTourPrice(new BigDecimal(req.getParameter("toldlerprice")));
	    tu.setBabyTourPrice(new BigDecimal(req.getParameter("babyprice")));
	    tu.setAdultTourCost(new BigDecimal(req.getParameter("adultcost")));
	    tu.setChildTourCost(new BigDecimal(req.getParameter("childcost")));
	    tu.setToddlerTourCost(new BigDecimal(req.getParameter("toldlercost")));
	    tu.setBabyTourCost(new BigDecimal(req.getParameter("babycost")));
	    tu.setPrivateRoomPrice(new BigDecimal(req.getParameter("privateRoom")));
	    tu.setTotalAdditionalCost(new BigDecimal(req.getParameter("additionalCost")));
	    tu.setTour(TourFunctionImpl.getInstance().getTourByTourId(req.getParameter("tourId")));
	    tu.setTourOperator(TourOperatorFunctionImpl.getInstance().getById(req.getParameter("tourOperator")));
	    
	    boolean check = TourUnitFunctionImpl.getInstance().checkConflictDate(req.getParameter("tourUnitId"),LocalDate.parse(req.getParameter("departure")) , LocalDate.parse(req.getParameter("return")));
	    
	    if(check)
	    {
	        response.sendRedirect("Tour-Unit?tour_id="+tu.getTour().getTourId()+"&error=Updated Failed!&recordArea=1");
	        return;
	    }
	    
	    boolean updated = dao.updateTourUnit(tu);

	    if (updated) {
	        response.sendRedirect("Tour-Unit?tour_id="+tu.getTour().getTourId()+"&message=Updated Successfully!&recordArea=1");
	    } else {
	        response.sendRedirect("Tour-Unit?tour_id="+tu.getTour().getTourId()+"&error=Updated Failed!&recordArea=1");
	    }
		
		 
	}
	
	

}
