package top.gendseo.books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import top.gendseo.books.pojo.*;

public class BooksDao {
	
	/*
	 * 数据库全局配置
	 * 如需要更换数据库，将 DB_DEIVER，DB_URL，DB_NAME, DB_USER，DB_PASSWORD 更换成相对应数据库的配置
	 * DB_DEIVER 数据库驱动
	 * DB_URL 数据库地址
	 * DB_NAME 数据库名
	 * DB_USER 连接数据库的用户
	 * DB_PASSWORD 连接数据库用户的密码
	 * */
	private static final String DB_DEIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
	private static final String DB_NAME = "Books";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "1";
	private static Connection connection = null;
	
	/*
	 * 序列化和反序列化 JSON 的 GOOGLE 插件
	 */
	private static Gson gson = new Gson();
	
	/*
	 * 获得数据库连接
	 */
	private static void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DB_DEIVER);
		connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
	}
	
	/*
	 * 关闭数据库连接
	 * 关闭时需要符合堆栈原则，顺序是：
	 * rs --> ps == st --> connection
	 * 使用时，如果 st, ps, rs 中任一个不存在，则设为空即可，如：
	 * getClose(connection, st, null, null);
	 */
	private static void getClose(Connection connection, Statement st, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (st != null) {
			st.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	/*
	 * 查询操作
	 * 参数：无
	 * 返回值：一个 JSON 格式的字符串，方便序列化
	 * 返回值类型：字符串
	 * 例子：{"books":[{"Bid":4,"Bname":"Java网络编程设计实训","Bnumber":6},{"Bid":5,"Bname":"Javascript 高级编程","Bnumber":4}]}
	 */
	public static String Query() throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		/*
		 * n 本书的集合列表 booksList
		 * 声明 Book 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.Books
		 */
		List<Book> booksList = new ArrayList<>();
		getConnection();
		
		String sql = "SELECT * FROM books;";
		System.out.println(sql);
		ps = connection.prepareStatement(sql);
		// 获得查询出来的结果集合
		ResultSet rs = ps.executeQuery();
		// 如果结果集合不为空 do while
		while (rs.next()) {
			// 声明一本书的类，并且往里添加数据，一一对应
			Book book = new Book();
			book.setBid(rs.getInt("Bid"));
			book.setBname(rs.getString("Bname"));
			book.setBnumber(rs.getInt("Bnumber"));
			// 最后把这本书添加到书的集合列表 booksList
			booksList.add(book);
		}
		/*
		 * n 本书的集合列表 BooksBean
		 * 声明 BooksBean 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.BooksBean
		 */
		BooksBean booksBean = new BooksBean();
		// 图书的列表
		booksBean.setRows(booksList);
		// 图书的总数
		booksBean.setTotal(String.valueOf(booksList.size()));
		
		getClose(connection, null, ps, rs);
		return gson.toJson(booksBean);
	}

	/*
	 * 删除操作
	 * 参数：n个或1个图书的ID
	 * 参数类型：字符串
	 * 例子："1,3,5"
	 * 返回值：true
	 * 返回值类型：字符串
	 * 例子："true"
	 */
	public static String DELETE(String Bid) throws ClassNotFoundException, SQLException {
		Statement st = null;
		getConnection();
		
		st = connection.createStatement();
		String sql = "DELETE FROM books WHERE \"Bid\" in (" + Bid + ");";
		System.out.println(sql);
		// executeUpdate 不同于 executeQuery
		// executeUpdate 执行更新操作，不返回任何结果
		st.executeUpdate(sql);
		
		getClose(connection, st, null, null);
		return "true";
	}

	/*
	 * 更新操作
	 * 参数：JSON，一本书的数据
	 * 参数类型：字符串
	 * 例子：{"Bid":5,"Bname":"Javascript 高级编程","Bnumber":4}
	 * 返回值：true
	 * 返回值类型：字符串
	 * 例子："true"
	 */
	public static String UPDATE(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();
		
		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		Book book = gson.fromJson(json, Book.class);
		String sql = "UPDATE books SET \"Bname\" = ?,\"Bnumber\" = ? WHERE \"Bid\" = ?;";
		System.out.println(sql);
		
		ps = connection.prepareStatement(sql);
		ps.setString(1, book.getBname());
		ps.setInt(2, book.getBnumber());
		ps.setInt(3, book.getBid());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}

	/*
	 * 增加操作
	 * 参数：JSON，一本书的数据
	 * 参数类型：字符串
	 * 例子：{"Bid":1,"Bname":"Javascript 高级编程","Bnumber":4}
	 * 返回值：true
	 * 返回值类型：字符串
	 * 例子："true"
	 */
	public static String INSERT(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();

		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		Book book = gson.fromJson(json, Book.class);
		String sql = "INSERT INTO books (\"Bid\", \"Bname\", \"Bnumber\") VALUES (?, ?, ?);";
		 System.out.println(sql);
		 
		ps = connection.prepareStatement(sql);
		ps.setInt(1, book.getBid());
		ps.setString(2, book.getBname());
		ps.setInt(3, book.getBnumber());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 查询
		System.out.println(Query());
		// 删除
//		System.out.println(DELETE("1,2,3"));
		// 增加
//		System.out.println(INSERT("{\"Bid\":1,\"Bname\":\"Javascript 高级编程\",\"Bnumber\":4}"));
		// 更新
//		System.out.println(UPDATE("{\"Bid\":1,\"Bname\":\"颈椎病康复\",\"Bnumber\":40}"));
		// 查询
//		System.out.println(Query());
	}
}
