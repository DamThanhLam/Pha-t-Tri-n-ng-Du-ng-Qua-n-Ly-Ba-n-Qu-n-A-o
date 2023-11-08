package test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestSMS {
	 public static void main(String[] args) {
		 String text = "Nội dung văn bản"; // Lấy từ trường nhập liệu hoặc từ người dùng
		 String phoneNumber = "0349616610"; // Lấy từ trường nhập liệu hoặc từ người dùng

	        try {
	            // Địa chỉ URL của tuyến đường Express
	            String expressUrl = "http://localhost:3000/send-message-spend-customer";

	            // Tạo dữ liệu bạn muốn gửi (dưới đây là dữ liệu JSON)
	            String postData = "{\"text\":\"" + text + "\",\"phoneNumber\":\"" + phoneNumber + "\"}";

	            // Tạo URL và mở kết nối
	            URL url = new URL(expressUrl);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            // Thiết lập phương thức là POST
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            // Đặt tiêu đề cho yêu cầu (nếu cần)
	            conn.setRequestProperty("Content-Type", "application/json");

	            // Ghi dữ liệu vào yêu cầu POST
	            try (OutputStream os = conn.getOutputStream()) {
	                byte[] input = postData.getBytes(StandardCharsets.UTF_8);
	                os.write(input, 0, input.length);
	            }

	            // Lấy mã trạng thái của phản hồi
	            int responseCode = conn.getResponseCode();
	            
	            if (responseCode == 200) {
	                System.out.println("POST request successful!");
	            } else if(responseCode == 201){
	            	System.out.println("Phone Number not exist");
	            }else {
	                System.out.println("POST request failed with status code: " + responseCode);
	            }

	            // Đóng kết nối
	            conn.disconnect();
	            System.out.println("đóng kết nối");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e);
	        }
	    }
}
