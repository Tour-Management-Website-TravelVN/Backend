# Phát triển hệ thống website quản lý tour du lịch trong nước cho công ty TravelVN

Đây là dự án phát triển hệ thống website quản lý tour du lịch trong nước, phục vụ nhu cầu đặt tour cho khách hàng và quản lý vận hành cho doanh nghiệp TravelVN.

Dự án được chia thành hai phần chính:

* Giao diện khách hàng (người dùng cuối): đặt tour, đăng ký tài khoản, đánh giá tour,...
* Giao diện quản trị (dành cho người quản lý và người điều hành tour): quản lý thông tin, duyệt đánh giá, xem báo cáo, thống kê...

---

## Công nghệ sử dụng (Backend)

Hệ thống backend được chia thành 2 hướng xử lý độc lập theo vai trò người dùng:

### 1. Phía khách hàng – Backend sử dụng Spring Framework

* Spring Boot: Tạo ứng dụng Java Web độc lập, cấu trúc rõ ràng, dễ mở rộng.
* Spring Security: Xác thực và phân quyền người dùng (JWT + HttpOnly Cookie).
* Spring Data JPA: Làm việc với CSDL qua ORM Hibernate.
* HikariCP: Quản lý connection pool hiệu quả.
* Restful API: Các chức năng như đăng ký, đăng nhập, đặt tour, thanh toán, đánh giá tour... được cung cấp qua các endpoint dạng REST, sử dụng JSON.
* CORS + Cookie HTTP Only: hỗ trợ frontend truy cập bảo mật từ trình duyệt.
* Sử dụng mô hình DTO – Entity – Service để tổ chức rõ ràng logic xử lý.

### 2. Phía quản lý & người điều hành tour – Backend sử dụng Servlet & JDBC

* Java Servlet: Dùng cho các trang quản lý nội bộ không dùng API mà xử lý qua form HTML với phương thức doGet và doPost.
* JDBC thuần: Làm việc trực tiếp với CSDL.
* Connection Pool tự xây dựng: Quản lý kết nối cơ sở dữ liệu bằng stack và thread-safe.
* HTML Form để nhập liệu và hiển thị kết quả.
* Quản lý session, xác thực thủ công qua session.
* Phân chia role (người điều hành, người quản lý) và giao diện chức năng riêng biệt.

---

## Phân quyền hệ thống

* Khách hàng: Đăng ký tài khoản, xem tour, đặt tour, đánh giá, hủy tour.
* Người điều hành tour: Quản lý tour, đơn vị tour, khách hàng, lễ hội, hướng dẫn viên, duyệt yêu cầu hủy.
* Người quản lý hệ thống: Quản lý nhân sự, tài khoản, xem thống kê, duyệt đánh giá.

---

## Các Use Case chính

Hệ thống hỗ trợ tổng cộng 24 use case chính và 1 use case thứ cấp, bao gồm:

* Đặt tour, hủy tour, xem lịch sử, đánh giá (phía khách hàng)
* Quản lý tour, khách hàng, lễ hội, hướng dẫn viên, duyệt hủy tour (phía điều hành tour)
* Duyệt đánh giá, thống kê doanh thu, khóa/mở tài khoản (phía quản lý)
* Use case nền tảng: đăng ký, đăng nhập, sửa đổi thông tin cá nhân
* Cập nhật trạng thái tour (tự động)

Chi tiết toàn bộ danh sách use case đã được liệt kê trong tài liệu báo cáo.

---

## Bảo mật

* Phía khách hàng: sử dụng JWT lưu trong HttpOnly Cookie để bảo vệ API REST khỏi tấn công XSS.
* Spring Security cấu hình CORS, session stateless, bảo vệ từng route.
* Phía quản trị: kiểm tra session cookie để xác định đăng nhập trước khi xử lý yêu cầu.

---

## Yêu cầu triển khai

* JDK 17 trở lên
* Maven 3.8+
* MySQL 8
* IDE: IntelliJ hoặc Eclipse (Servlet)
* Frontend: HTML/CSS/JS (kết nối với RestAPI hoặc Form submit), jQuery, Boostrap 5
* Postman (test API)

---

## Hướng dẫn chạy dự án

1. Tạo cơ sở dữ liệu và import dữ liệu mẫu (cập nhật mật khẩu có sử dụng hàm băm cho bản ghi trong CSDL để có thể đăng nhập)
2. Chạy ứng dụng Spring Boot (khách hàng). Client-side ở repo Frontend_end_user (cùng tổ chức này)
3. Deploy ứng dụng quản lý (Servlet) vào Tomcat hoặc chạy qua IDE
4. Truy cập giao diện khách hàng/người quản lý/người điều hành tour
