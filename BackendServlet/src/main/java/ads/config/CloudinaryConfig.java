package ads.config;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

public class CloudinaryConfig {

	public Cloudinary cloudinary() {
		Dotenv dotenv = Dotenv.load();
//		Dotenv dotenv = Dotenv.configure()
//			    .directory(".") // thư mục gốc project
//			    .filename(".env") // tên file
//			    .load();
//		Dotenv dotenv2 = Dotenv.configure().directory(".").load();
//		System.out.println(dotenv2.get("CLOUDINARY_CLOUD_NAME")); // In ra để kiểm tra
		return new Cloudinary(dotenv.get("CLOUDINARY_URL"));
	}
}
