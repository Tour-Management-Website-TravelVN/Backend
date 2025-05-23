package ads.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ads.config.CloudinaryConfig;
import ads.user.UserAccountFunctionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class CloudinaryService {

	private final Cloudinary CLOUDINARY;

	public CloudinaryService() {
		CloudinaryConfig config = new CloudinaryConfig();
		this.CLOUDINARY = config.cloudinary();
	}

	private static volatile CloudinaryService instance;

	// Singleton Pattern
	public static CloudinaryService getInstance() {
		if (instance == null) {
			Class var0 = CloudinaryService.class;
			synchronized (CloudinaryService.class) {
				if (instance == null) {
					instance = new CloudinaryService();
				}
			}
		}

		return instance;
	}

	/**
	 * Lấy các đường dẫn sau khi up cloudinary
	 * 
	 * @param request
	 * @return Danh sách đường dẫn
	 */
	public List<String> getImgUrlsAfterUpload(HttpServletRequest request) {
		List<String> urls = new ArrayList<String>();
		try {
			Collection<Part> parts = request.getParts();

			parts.forEach(part -> {
				// Chỉ xử lý những part là file, tên form phải là "files"
				if (part.getName().equals("files") && part.getSize() > 0) {
					// Upload các file và nhận lại url
					try (InputStream is = part.getInputStream()) {
						Map uploadResult = CLOUDINARY.uploader().upload(is.readAllBytes(), ObjectUtils.asMap("folder", "Image"));
						urls.add((String) uploadResult.get("url"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			return urls;

		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lấy các đường dẫn sau khi up cloudinary
	 * 
	 * @param request
	 * @return Danh sách đường dẫn
	 */
	public List<String> getImgUrlsFromCloud(HttpServletRequest request) {
		List<String> urls = new ArrayList<String>();
		try {
			Collection<Part> parts = request.getParts();

			parts.forEach(part -> {
				// Chỉ xử lý những part là file, tên form phải là "files"
				if (part.getName().equals("newImages") && part.getSize() > 0) {
					// Upload các file và nhận lại url
					try (InputStream is = part.getInputStream()) {
						Map uploadResult = CLOUDINARY.uploader().upload(is.readAllBytes(), ObjectUtils.asMap("folder", "Image"));
						urls.add((String) uploadResult.get("url"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			return urls;

		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lấy các đường dẫn sau khi up cloudinary
	 * 
	 * @param request
	 * @return Danh sách đường dẫn
	 */
	public String getImgUrlsAfterUpload(byte[] imgBytes) {
		try {
			Map uploadResult = CLOUDINARY.uploader().upload(imgBytes, ObjectUtils.asMap("folder", "Image"));
			return (String) uploadResult.get("url");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getVideoUrlsAfterUpload(HttpServletRequest request, String type, String folderName) {
		// type = video
		// folderName = Video
		List<String> urls = new ArrayList<String>();
		try {
			Collection<Part> parts = request.getParts();

			parts.forEach(part -> {
				// Chỉ xử lý những part là file, tên form phải là "files"
				if (part.getName().equals("files") && part.getSize() > 0) {
					// Upload các file và nhận lại url
					try (InputStream is = part.getInputStream()) {
						Map uploadResult = CLOUDINARY.uploader().upload(is,
								ObjectUtils.asMap("resource_type", type, "folder", folderName));
						urls.add((String) uploadResult.get("url"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			return urls;

		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getFileName(Part part) {
		// form-data; name="file"; filename="example.png"
		String contentDisp = part.getHeader("content-disposition");

		// ["form-data", " name=\"file\"", " filename=\"example.png\""]
		for (String cd : contentDisp.split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				return Paths.get(fileName).getFileName().toString();
			}
		}
		;

		return null;
	}

}
