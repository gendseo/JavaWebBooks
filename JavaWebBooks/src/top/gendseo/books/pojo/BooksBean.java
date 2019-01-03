package top.gendseo.books.pojo;

import java.util.List;

/*
 * 定义 pojo 实体类使用 Gson 直接将 Class 转换成 JSON 
 * pojo 实体类 BooksBean，结构如下：
 * 
 * {
 * 	 "total":"2",
 *   "rows": [
 *     {
 *       "Bid": 4, 
 *       "Bname": "Java网络编程设计实训", 
 *       "Bnumber": 6
 *     }, 
 *     {
 *       "Bid": 5, 
 *       "Bname": "Javascript 高级编程", 
 *       "Bnumber": 4
 *     }
 *   ]
 * }
 * 
 * */

public class BooksBean {

	private String total;
	private List<Book> rows;

	public void setRows(List<Book> books) {
		this.rows = books;
	}

	public List<Book> getRows() {
		return rows;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}