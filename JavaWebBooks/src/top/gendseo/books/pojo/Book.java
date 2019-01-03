package top.gendseo.books.pojo;

/*
 * 定义 pojo 实体类使用 Gson 直接将 Class 转换成 JSON 
 * pojo 实体类 Books，结构如下：
 * 
 * {
 *     "Bid": 1, 
 *     "Bname": "Java网络编程设计实训", 
 *     "Bnumber": 62
 * }
 * 
 * */

public class Book {

	private int Bid;
	private String Bname;
	private int Bnumber;

	public void setBid(int Bid) {
		this.Bid = Bid;
	}

	public int getBid() {
		return Bid;
	}

	public void setBname(String Bname) {
		this.Bname = Bname;
	}

	public String getBname() {
		return Bname;
	}

	public void setBnumber(int Bnumber) {
		this.Bnumber = Bnumber;
	}

	public int getBnumber() {
		return Bnumber;
	}

}