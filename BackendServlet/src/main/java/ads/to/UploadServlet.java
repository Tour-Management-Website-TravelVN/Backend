package ads.to;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ads.util.CloudinaryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html>");
		out.append("<html lang=\"en\">");
		out.append("");
		out.append("<head>");
		out.append("<meta charset=\"utf-8\">");
		out.append("<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">");
		out.append("");
		out.append("<title>Quản lý đơn vị tour</title>");
		out.append("<meta content=\"\" name=\"description\">");
		out.append("<meta content=\"\" name=\"keywords\">");
		out.append("</head>");
		out.append("""
				<form method="post" action="/adv/upload" enctype="multipart/form-data">
				    <input type="file" name="files" multiple>
				    <button type="submit">Upload</button>
				</form>
				""");
		out.append("<body>");
		out.append("</body>");
		out.append("");
		out.append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CloudinaryService cloudinaryService = CloudinaryService.getInstance();
		List<String> urls = cloudinaryService.getImgUrlsAfterUpload(request);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// In kết quả là mảng URL
		response.getWriter().write(urls.toString());
	}
}
