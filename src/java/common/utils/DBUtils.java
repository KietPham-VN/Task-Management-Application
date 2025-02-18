package common.utils;

import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Tiện ích kết nối cơ sở dữ liệu (Database Utility).
 * <p>
 * Lớp này chịu trách nhiệm:
 * <ul>
 * <li>Đọc các biến môi trường (driver, URL, username, password) thông qua
 * Dotenv.</li>
 * <li>Tạo và trả về đối tượng Connection đến cơ sở dữ liệu.</li>
 * </ul>
 * <p>
 * Cách sử dụng:
 * <pre>{@code
 *  try (Connection connection = DBUtils.getConnection()) {
 *      // Thực hiện truy vấn, xử lý dữ liệu...
 *  } catch (ClassNotFoundException | SQLException e) {
 *      e.printStackTrace();
 *  }
 * }</pre>
 *
 * Lưu ý: Cần đảm bảo file .env có các key:
 * <ul>
 * <li>DB_DRIVER</li>
 * <li>DB_URL</li>
 * <li>DB_USERNAME</li>
 * <li>DB_PASSWORD</li>
 * </ul>
 *
 */
public class DBUtils
{

    /**
     * Logger cho lớp DBUtils.
     */
   // private static final Logger LOGGER = Logger.getLogger(DBUtils.class.getName());

    /**
     * Đối tượng Dotenv để tải các biến môi trường từ file .env.
     */
    private static final Dotenv DOT_ENV = Dotenv.configure()
            .directory(Paths.get("D:/F_CODE/CrewBE/BE3/Task-Management-Application").toAbsolutePath().toString())
            .load();


    /**
     * Trả về một đối tượng {@link Connection} kết nối đến database.
     * <p>
     * Phương thức sẽ:
     * <ol>
     * <li>Đọc driver, URL, username, password từ biến môi trường (file
     * .env).</li>
     * <li>Nạp driver bằng {@code Class.forName(driver)}.</li>
     * <li>Tạo kết nối bằng
     * {@code DriverManager.getConnection(url, username, password)}.</li>
     * <li>Log thông tin khi kết nối thành công.</li>
     * </ol>
     *
     * @return {@link Connection} kết nối tới DB (đã được thiết lập).
     * @throws ClassNotFoundException Nếu driver DB không tồn tại trong
     * classpath.
     * @throws SQLException Nếu có lỗi khi kết nối (sai URL, tài khoản, network,
     * v.v.)
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String driver = DOT_ENV.get("DB_DRIVER");
        String url = DOT_ENV.get("DB_URL");
        String username = DOT_ENV.get("DB_USERNAME");
        String password = DOT_ENV.get("DB_PASSWORD");
        Class.forName(driver);
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=TaskManagementDB;user=sa;password=12345;");
        //LOGGER.info("Database connection successfully established.");
        return conn;
    }
}
