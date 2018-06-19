package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Connector;
import application.Movie;
import application.Electronic;
import application.Music;
import application.Product;
import application.Customer;
import application.Seller;
import application.User;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
public class daoModelImpl implements daoModel {
	
	/**
	 * 
	 * Variables to handle connection with the DB
	 *
	 */
	
	Connector connect = new Connector();
	private Statement statement = null;

	/**
	 * 
	 * Method to create all the tables needed and described in the ERD in the document
	 *
	 */
	public void createTables() throws Exception, SQLException {
		try {
			statement = connect.getConnection().createStatement();
			/**
			 * Creation of user-related tables
			 */

			String sql = "CREATE TABLE customers_ar " + " (customer_id INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(20), " + " password VARCHAR(20), " + " isAdmin VARCHAR(3), "
					+ " PRIMARY KEY ( customer_id ))";

			statement.executeUpdate(sql);

			sql = "CREATE TABLE sellers_ar " + "(seller_id INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(20), " + " password VARCHAR(20), " + " isAdmin VARCHAR(3), "
					+ " PRIMARY KEY ( seller_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of product-related tables
			 */
			sql = "CREATE TABLE music_ar " + "(music_id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(255), "
					+ " description VARCHAR(255), " + " image VARCHAR(255), " + " price NUMERIC(20,2), "
					+ " rate NUMERIC(1), " + " stock_counter NUMERIC(8), " + " purchase_date VARCHAR(25), "
					+ " author VARCHAR(80), " + " album_name VARCHAR(100), " + " PRIMARY KEY ( music_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE movie_ar " + "(movie_id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(255), "
					+ " description VARCHAR(255), " + " image VARCHAR(255), " + " price NUMERIC(20,2), "
					+ " rate NUMERIC(1), " + " stock_counter NUMERIC(8), " + " purchase_date VARCHAR(25), "
					+ " duration VARCHAR(4), " + " trailer VARCHAR(255), " + " PRIMARY KEY ( movie_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE electronic_ar " + "(electronic_id INTEGER not NULL AUTO_INCREMENT, "
					+ " name VARCHAR(255), " + " description VARCHAR(255), " + " image VARCHAR(255), "
					+ " price NUMERIC(20,2), " + " rate NUMERIC(1), " + " stock_counter NUMERIC(8), "
					+ " purchase_date VARCHAR(25), " + " specifications VARCHAR(255), " + " brand VARCHAR(50), "
					+ " PRIMARY KEY ( electronic_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE products_ar " + "(product_id INTEGER not NULL AUTO_INCREMENT, "
					+ " PRIMARY KEY (product_id), " + " music_id INTEGER, " + " electronic_id INTEGER, "
					+ " movie_id INTEGER, "  + " seller_id INTEGER, "+" FOREIGN KEY (music_id) REFERENCES music_ar(music_id), "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id),"+" FOREIGN KEY(seller_id) REFERENCES sellers_ar(seller_id), " + "PRIMARY KEY ( product_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of order-related tables
			 */
			sql = "CREATE TABLE orders_ar " + "(order_id INTEGER not NULL AUTO_INCREMENT, " + " customer_id INTEGER, "
					+ " product_id INTEGER, " + " PRIMARY KEY ( order_id )" + ")";
			statement.executeUpdate(sql);
			sql = "ALTER TABLE orders_ar ADD FOREIGN KEY (customer_id) REFERENCES customers_ar(customer_id)";
			// statement.executeUpdate(sql);
			sql = "ALTER TABLE orders_ar ADD FOREIGN KEY (product_id) REFERENCES products_ar(product_id)";
			statement.executeUpdate(sql);
			sql = "CREATE TABLE cart_ar " + "(cart_id INTEGER not NULL AUTO_INCREMENT, " + " customer_id INTEGER, "
					+ " product_id INTEGER, " + " FOREIGN KEY (customer_id) REFERENCES customers_ar(customer_id), "
					+ " FOREIGN KEY (product_id) REFERENCES products_ar(product_id), " + " PRIMARY KEY ( cart_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE product_list_ar " + "(list_id INTEGER not NULL AUTO_INCREMENT, "
					+ " seller_id INTEGER, " + " product_id INTEGER, "
					+ " FOREIGN KEY (seller_id) REFERENCES sellers_ar(seller_id), "
					+ " FOREIGN KEY (product_id) REFERENCES products_ar(product_id), " + " PRIMARY KEY ( list_id ))";
			statement.executeUpdate(sql);
			/**
			 * Creation of rate-related tables
			 */
			sql = "CREATE TABLE ratemusic_ar " + "(ratem_id INTEGER not NULL AUTO_INCREMENT, " + " rate NUMERIC(1), "
					+ " music_id INTEGER, " + " FOREIGN KEY (music_id) REFERENCES music_ar(music_id), "
					+ " PRIMARY KEY ( ratem_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE rateelectronic_ar " + "(ratee_id INTEGER not NULL AUTO_INCREMENT, "
					+ " rate NUMERIC(1), " + " electronic_id INTEGER, "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " PRIMARY KEY ( ratee_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE ratemovie_ar " + "(ratemo_id INTEGER not NULL AUTO_INCREMENT, " + " rate NUMERIC(1), "
					+ " movie_id INTEGER, " + " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id), "
					+ " PRIMARY KEY ( ratemo_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of review-related tables
			 */
			sql = "CREATE TABLE musicreview_ar " + "(reviewm_id INTEGER not NULL AUTO_INCREMENT, "
					+ " music_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (music_id) REFERENCES music_ar(music_id), " + " PRIMARY KEY ( reviewm_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE electronicreview_ar " + "(reviewe_id INTEGER not NULL AUTO_INCREMENT, "
					+ " electronic_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " PRIMARY KEY ( reviewe_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE moviereview_ar " + "(reviewmov_id INTEGER not NULL AUTO_INCREMENT, "
					+ " movie_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id), " + " PRIMARY KEY ( reviewmov_id ))";
			statement.executeUpdate(sql);

			statement.close();
		}catch (SQLException e) {
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println(e.getMessage());

			}

	}

	/**
	 * Method to insert user in the DB
	 * 
	 * @param User
	 * @throws SQLException, Exception
	 */

	public void insertUser(User u) throws Exception, SQLException {
		try {

			statement = connect.getConnection().createStatement();

			// System.out.println(u instanceof Customer);
			if (u instanceof Customer) {

				String sql = "INSERT INTO customers_ar(username, password, isAdmin) " + "VALUES ('" + u.getUsername()
						+ "', '" + u.getPassword() + "', '" + u.isAdmin() + "')";
				statement.executeUpdate(sql);
			} else {

				String sql = "INSERT INTO sellers_ar(username, password, isAdmin) " + "VALUES ('" + u.getUsername()
						+ "', '" + u.getPassword() + "', '" + u.isAdmin() + "')";
				statement.executeUpdate(sql);
			}

			statement.close();
		}

		catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());

		}

	}
	/**
	 * Method to insert products in the db
	 * Include products in products_ar and its category
	 * @param Product
	 * @param User
	 * @throws Exception
	 */
	public void insertProduct(Product p, User u) throws SQLException, Exception {
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				String sql = "INSERT INTO music_ar(name, description, image, price, rate, stock_counter, purchase_date, author, album_name) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" + ((Music) p).getAuthor() + "', '" + ((Music) p).getAlbumName() + "')";
				statement.executeUpdate(sql);
				statement.close();
				int idp = searchIdProduct(p);
				System.out.println("The id of the product inserted is" + idp);
				
				int idu = searchIdUser(u);
				System.out.println("The id of the user inserting is" + idu);
				
				statement = connect.getConnection().createStatement();
				sql = "INSERT INTO products_ar(music_id, seller_id)"
					+"VALUES('"+idp+"', '"+idu+"')";
				//Statement statement2 = connect.getConnection().createStatement();
				statement.executeUpdate(sql);
				System.out.println("Inserted in Products");

			} else if (p instanceof Electronic) {
				String sql = "INSERT INTO electronic_ar(name, description, image, price, rate, stock_counter, purchase_date, specifications, brand) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" +((Electronic) p).getSpecifications() + "', '" + ((Electronic) p).getBrand() + "')";
				statement.executeUpdate(sql);
				statement.close();
				int idp = searchIdProduct(p);
				int idu = searchIdUser(u);
				statement = connect.getConnection().createStatement();
				sql = "INSERT INTO products_ar(electronic_id, seller_id)"
						+"VALUES('"+idp+"','"+idu+"')";
				statement.executeUpdate(sql);
			} else if (p instanceof Movie){
				Movie p2 = (Movie) p;
				String sql = "INSERT INTO movie_ar(name, description, image, price, rate, stock_counter, purchase_date, duration, trailer) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" + p2.getDuration() + "', '" + p2.getTrailer() + "')";
				statement.executeUpdate(sql);
				statement.close();
				int idp = searchIdProduct(p);
				int idu = searchIdUser(u);
				statement = connect.getConnection().createStatement();
				sql = "INSERT INTO products_ar(movie_id, seller_id)"
						+"VALUES('"+idp+"', '"+idu+"')";
				statement.executeUpdate(sql);
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}

	}
	/**
	 * Method to insert ratings in the db from a product
	 * Include products in products_ar and its category
	 * @param Product
	 * @param Rate
	 * @throws SQLException, Exception
	 */
	public void insertRating(double rate, Product p) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				Music p2 = (Music) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO ratemusic_ar(rate, music_id) " + "VALUES ('" + rate + "', '" + id + "')";
				statement.executeUpdate(sql);
			} else if (p instanceof Electronic) {
				Electronic p2 = (Electronic) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO rateelectronic_ar(rate, electronic_id) " + "VALUES ('" + rate + "', '" + id
						+ "')";
				statement.executeUpdate(sql);
			} else {
				Movie p2 = (Movie) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO ratemovie_ar(rate, movie_id) " + "VALUES ('" + rate + "', '" + id + "')";
				statement.executeUpdate(sql);
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method to insert orders in the db from a user
	 * @param Product
	 * @param User
	 * @throws SQLException, Exception
	 */
	public void insertOrder(Product p, User u) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			int idU = searchIdUser(u);
			String sql = "INSERT INTO orders_ar(customer_id, product_id) " + "VALUES ('" + idU + "', '" + id + "')";
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method to insert product in the cart from user
	 * @param Product
	 * @param User
	 * @throws SQLException, Exception
	 */
	public void insertCart(Product p, User u) throws SQLException, Exception {
		try {
			Statement statement2 = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			String sql;
			System.out.println("El id del nosabemos product es: "+ id);
			if(p instanceof Music){
				sql = "SELECT product_id FROM products_ar WHERE music_id = '"+id+"'";
				ResultSet set = statement2.executeQuery(sql);
				set.next();
				id = set.getInt("product_id");
			}else if(p instanceof Electronic){
				sql = "SELECT product_id FROM products_ar WHERE electronic_id = '"+id+"'";
				ResultSet set = statement2.executeQuery(sql);
				set.next();
				id = set.getInt("product_id");
			}else if (p instanceof Movie){
				sql = "SELECT product_id FROM products_ar WHERE movie_id = '"+id+"'";
				ResultSet set = statement2.executeQuery(sql);
				set.next();
				id = set.getInt("product_id");
			}
			System.out.println("El id del product es: "+ id);
			int idU = searchIdUser(u);
			System.out.println("El id del user es: "+ idU);
			sql = "INSERT INTO cart_ar(customer_id, product_id) " + "VALUES ('" + idU + "', '" + id + "')";
			statement2.executeUpdate(sql);
			statement2.close();

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method to obtain the cart from the db from a user
	 * Include products in products_ar and its category
	 * @param User
	 * @throws SQLException, Exception
	 */
	public List<Product> getCart(User u) throws SQLException, Exception {
		
		try {
			if (u instanceof Customer){
				statement = connect.getConnection().createStatement();
				System.out.println("My username: "+ u.getUsername());
				int idU = searchIdUser(u);
				List<Product> products = new ArrayList<Product>();
				List<Integer> productIds = new ArrayList<Integer>();
				List<Product> musicIds = new ArrayList<Product>();
				List<Product> electronicIds = new ArrayList<Product>();
				List<Product> movieIds = new ArrayList<Product>();
				statement = connect.getConnection().createStatement();
				String sql = "SELECT * FROM cart_ar WHERE customer_id = '"+idU+"'";
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()){
					productIds.add(Integer.valueOf(rs.getString("product_id")));
				}
				for(int i=0; i<productIds.size();i++){
					statement = connect.getConnection().createStatement();
					sql = "SELECT * FROM products_ar WHERE product_id = '"+ productIds.get(i)+"'";
					
					ResultSet rs2 = statement.executeQuery(sql);
					while(rs2.next()){
						if(!(String.valueOf(rs2.getInt("music_id")).equals("0"))){
							int musicID = rs2.getInt("music_id");
							System.out.println("HE encontrado muisc");
							products.add(getProduct(musicID, "music"));
						}else if (!(String.valueOf(rs2.getInt("electronic_id")).equals("0"))){
							int electronicID = rs2.getInt("music_id");
							System.out.println("HE encontrado electronics");
							products.add(getProduct(electronicID, "electronic"));
						}else if(!(String.valueOf(rs2.getInt("movie_id")).equals("0"))){
							int movieID = rs2.getInt("movie_id");
							System.out.println("HE encontrado movie");
							products.add(getProduct(movieID, "movie"));
						}
					}
					
				statement.close();
				}
				return products;
			}
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return null;
	}
	/**
	 * Method to obtain a product attributes with its product_id and type
	 * @param ID Product_id
	 * @param Type of product
	 * @throws SQLException, Exception
	 */
	public Product getProduct(int id, String type) throws SQLException, Exception{
		Product p;
		String sql;
		ResultSet rs2;
		try{
			if(type.equals("music")){
				statement = connect.getConnection().createStatement();
				sql = "SELECT * FROM music_ar WHERE music_id = '"+id+"'";
				rs2 = statement.executeQuery(sql);
				rs2.next();
				p = new Music(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("author"), rs2.getString("album_name"));
				return p;
			}else if (type.equals("electronic")){
				statement = connect.getConnection().createStatement();
				sql = "SELECT * FROM electronicc_ar WHERE electronic_id = '"+id+"'";
				rs2 = statement.executeQuery(sql);
				rs2.next();
				p = new Music(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("specifications"), rs2.getString("brand"));
				return p;
			}else if(type.equals("movie")){
				
				statement = connect.getConnection().createStatement();
				sql = "SELECT * FROM movie_ar WHERE movie_id = '"+id+"'";
				rs2 = statement.executeQuery(sql);
				rs2.next();
				p = new Music(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("duration"), rs2.getString("trailer"));
				return p;
			}
		statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return null;
	}
	/**
	 * Method to search for a music based on id
	 * @param Id
	 * @throws SQLException, Exception
	 */
	public Product searchProductIdMusic(int id) throws SQLException, Exception{
		try{
		statement = connect.getConnection().createStatement();
		String sql = "SELECT * FROM music_ar WHERE music_id ='"+id+"'";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			Product p = new Music(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("author"), rs.getString("album_name"));
			return p;
		}
		}catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return null;
		
	}
	/**
	 * Method to search for a electronic based on id
	 * @param Id
	 * @throws SQLException, Exception
	 */
	public Product searchProductIdElectronic(int id) throws SQLException, Exception{
		try{
		statement = connect.getConnection().createStatement();
		String sql = "SELECT * FROM electronic_ar WHERE electronic_id = '"+id+"'";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			Product p = new Electronic(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("specifications"), rs.getString("brand"));
			return p;
		}
		}catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return null;
		
	}
	/**
	 * Method to search for a movie based on id
	 * @param Id
	 * @throws SQLException, Exception
	 */
	public Product searchProductIdMovie(int id) throws SQLException, Exception{
		try{
		statement = connect.getConnection().createStatement();
		String sql = "SELECT * FROM movie_ar WHERE movie_id = '"+id+"'";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			Product p = new Movie(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("duration"), rs.getString("trailer"));
			return p;
		}
		}catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return null;
		
	}
	/**
	 * Method to insert in ProductList
	 * @param Product
	 * @param User
	 * @throws SQLException, Exception
	 */
	public void insertProductList(Product p, User u) throws SQLException, Exception {
		try {
			statement = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			int idU = searchIdUser(u);
			String sql = "INSERT INTO product_list_ar(customer_id, product_id) " + "VALUES ('" + id + "', '" + idU
					+ "')";
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method return userid
	 * @param User
	 * @throws SQLException, Exception
	 */
	
	public int returnUser(User u) throws SQLException,Exception {
		int id=0;
		try {
			statement = connect.getConnection().createStatement();
			if (u instanceof Customer) {
				String sql = "SELECT customer_id FROM customers_ar WHERE username='" + u.getUsername() + "' AND password='" + u.getPassword()+"'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				id = rs.getInt("customer_id");

			} else {
				String sql = "SELECT seller_id FROM sellers_ar WHERE username= '" + u.getUsername() + "' AND password='" + u.getPassword()+"'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				//System.out.println("El id de " + u.getUsername() + " es " + rs.getInt("seller_id"));
				id = rs.getInt("seller_id");
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	/**
	 * Method to search iduser based in Product
	 * @param Product
	 * @throws SQLException, Exception
	 */
	public int searchIdUserProduct(Product p) throws SQLException, Exception{
		int id = 0;
		int idp = searchIdProduct(p);
		System.out.println(idp);
		try{
			statement = connect.getConnection().createStatement();
			if(p instanceof Music){
				String sql = "SELECT product_id FROM products_ar WHERE music_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				id = rs.getInt("music_id");
			}else if(p instanceof Electronic){
				String sql = "SELECT product_id FROM products_ar WHERE electronic_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				id = rs.getInt("electronic_id");
			}else if (p instanceof Movie){
				String sql = "SELECT product_id FROM products_ar WHERE movie_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				id = rs.getInt("movie_id");
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	
	/**
	 * Method to search id from user
	 * @param User
	 * @throws SQLException, Exception
	 */
	public int searchIdUser(User u) throws SQLException, Exception {
		int id = 0;
		System.out.println(u.getUsername());
		try {
			statement = connect.getConnection().createStatement();
			// System.out.println("Hola, he conectado");
			if (u instanceof Customer) {
				// System.out.println("Hola, he entrado");
				// System.out.println(u.getUsername());
				String sql = "SELECT customer_id FROM customers_ar WHERE username='" + u.getUsername() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				// System.out.println("El id de "+ u.getUsername()+ " es
				// "+rs.getInt("customer_id"));
				id = rs.getInt("customer_id");

			} else if(u instanceof Seller) {
				String sql = "SELECT seller_id FROM sellers_ar WHERE username= '" + u.getUsername() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				//System.out.println("El id de " + u.getUsername() + " es " + rs.getInt("seller_id"));
				id = rs.getInt("seller_id");
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	/**
	 * Method to search id of a product
	 * @param Product
	 * @throws SQLException, Exception
	 */
	public int searchIdProduct(Product p) throws SQLException, Exception {
		int id = 0;
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				Music p2 = (Music) p;
				String sql = "SELECT music_id FROM music_ar WHERE name ='" + p2.getName()+"'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next(); 
				//System.out.println("El id de " + p2.getName() + " es " + rs.getInt("music_id"));
				id = rs.getInt("music_id");
			} else if (p instanceof Electronic) {
				Electronic p2 = (Electronic) p;
				String sql = "SELECT electronic_id FROM electronic_ar WHERE name='" + p2.getName() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				//System.out.println("El id de " + p2.getName() + " es " + rs.getInt("electronic_id"));
				id = rs.getInt("electronic_id");
			} else {
				Movie p2 = (Movie) p;
				String sql = "SELECT movie_id FROM movie_ar WHERE name='" + p2.getName() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				//System.out.println("El id de " + p2.getName() + " es " + rs.getInt("movie_id"));
				id = rs.getInt("movie_id");
			}
			statement.close();
		} catch (Exception e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	/**
	 * Method to delete user
	 * @param User
	 * @throws SQLException, Exception
	 */
	public void deleteUser(User u) throws SQLException, Exception {
		int id = searchIdUser(u);
		try {
			statement = connect.getConnection().createStatement();
			if (u instanceof Customer) {
				String sql = "DELETE FROM orders_ar WHERE customer_id = '" + id+ "'";
				statement.executeUpdate(sql);
				sql = "DELETE FROM cart_ar WHERE customer_id = '" + id+ "'";
				statement.executeUpdate(sql);
				sql = "DELETE FROM customers_ar WHERE customer_id = '" + id + "'";
				statement.executeUpdate(sql);
				System.out.println("The following customer has been deleted: "+ u.getUsername());
			} else {
				String sql = "DELETE FROM product_list_ar WHERE seller_id = '" + id + "'";
				statement.executeUpdate(sql);
				sql = "DELETE FROM sellers_ar WHERE seller_id = '" + id + "'";
				statement.executeUpdate(sql);
				System.out.println("The following seller has been deleted: "+ u.getUsername());
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}

	}
	/**
	 * Method to delete product from db
	 * @param Product
	 * @throws SQLException, Exception
	 */
	public void deleteProduct(Product p) throws SQLException, Exception {
		int id = searchIdProduct(p);
		try {
			
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				String sql = "DELETE FROM products_ar WHERE music_id = '" + id+ "'";
				statement.executeUpdate(sql);
				sql = "DELETE FROM musicreview_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM ratemusic_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM music_ar WHERE music_id ='" + id+ "'";
				statement.executeUpdate(sql);
				System.out.println("The following Music product has been deleted:" + p.getName());

			} else if (p instanceof Electronic) {
				String sql = "DELETE FROM products_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM electronicreview_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM rateelectronic_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM electronic_ar WHERE electronic_id ='" + id+ "'";
				statement.executeUpdate(sql);
				System.out.println("The following Electronic product has been deleted:" + p.getName());
			} else {
				String sql = "DELETE FROM products_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM moviereview_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM ratemovie_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM movie_ar WHERE movie_id ='" + id+ "'";
				statement.executeUpdate(sql);
				System.out.println("The following Movie product has been deleted:"  + p.getName());
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method to retrieve all the products from db with all of its characteristics
	 * @throws SQLException, Exception
	 */
	public List<Product> getProducts() throws SQLException, Exception {
		List<Product> products = new ArrayList<>();
		try {
			statement = connect.getConnection().createStatement();
			
			String sql = "SELECT * FROM music_ar";
			ResultSet rs2 = statement.executeQuery(sql);
			List<String> reviews = new ArrayList<>();
			List<Integer> ratings = new ArrayList<>();
			double rate = 0.0;
			int rowcount = 0;
			int control = 0;
			
			if (rs2.last()) {
				  rowcount = rs2.getRow();
				  rs2.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
				}
			//System.out.println("Number of songs: " + rowcount);
			int[]ids = new int[rowcount];
			int counter = 0;
			
			while(rs2.next()){
				Product p;
				ids[counter] = rs2.getInt("music_id");
				p = new Music(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("author"), rs2.getString("album_name"));
				//System.out.println("Music id is: "+rs2.getInt("music_id"));
				products.add(p);
				counter++;
			}

			setReviewsMusic(products, ids, control);
			setRatingMusic(products, ids, control);

			
			control = counter+control;
			
			statement.close();
			statement = connect.getConnection().createStatement();
			sql = "SELECT * FROM electronic_ar";
			rs2 = statement.executeQuery(sql);
			reviews = new ArrayList<>();
			ratings = new ArrayList<>();
			rate = 0.0;
			rowcount = 0;
			if (rs2.last()) {
				  rowcount = rs2.getRow();
				  rs2.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
				}
			ids = new int[rowcount];
			counter = 0;
			
			while(rs2.next()){
				Product p;
				ids[counter] = rs2.getInt("electronic_id");
				p = new Electronic(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("brand"), rs2.getString("specifications"));
				//System.out.println("Electronic id is: "+rs2.getInt("electronic_id"));
				products.add(p);
				counter++;
			}
			setReviewsElectronic(products, ids, control);
			setRatingElectronic(products, ids, control);
			
			control = counter+control;
			
			sql = "SELECT * FROM movie_ar";
			rs2 = statement.executeQuery(sql);
			reviews = new ArrayList<>();
			ratings = new ArrayList<>();
			rate = 0.0;
			rowcount = 0;
			if (rs2.last()) {
				  rowcount = rs2.getRow();
				  rs2.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
				}
			ids = new int[rowcount];
			counter = 0;
			
			while(rs2.next()){
				Product p;
				ids[counter] = rs2.getInt("movie_id");
				p = new Movie(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("duration"), rs2.getString("trailer"));
				//System.out.println("Movie id is: "+rs2.getInt("movie_id"));
				products.add(p);
				counter++;
			}
			
			setReviewsMovie(products, ids, control);
			setRatingMovie(products, ids, control);
			
//			for(Product p:products){
//				System.out.println(p.getName());
//			}
			
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		statement.close();
		return products;
	}


	/**
	 * Method to setreviews music
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	private void setReviewsMusic(List<Product> products, int[] ids, int control) throws SQLException, Exception {
		for(int i = 0; i<ids.length;i++){
			String sql = "SELECT review FROM musicreview_ar WHERE music_id ='" + ids[i]+ "'";
			List<String> reviews = new ArrayList<String>();
			Statement statement2 = connect.getConnection().createStatement();
			ResultSet rs3 = statement2.executeQuery(sql);
			while (rs3.next()){
				System.out.println(rs3.getString("review"));
				reviews.add(rs3.getString("review"));
			}
			products.get(i).setReviews(reviews);
			
			statement2.close();
		}
	}
	/**
	 * Method to setreviews electronic
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	private void setReviewsElectronic(List<Product> products, int[] ids, int control) throws SQLException, Exception {
		for(int i = 0; i<ids.length;i++){
			String sql = "SELECT review FROM electronicreview_ar WHERE electronic_id ='" + ids[i]+ "'";
			List<String> reviews = new ArrayList<String>();
			Statement statement2 = connect.getConnection().createStatement();
			ResultSet rs3 = statement2.executeQuery(sql);
			while (rs3.next()){
				System.out.println(rs3.getString("review"));
				reviews.add(rs3.getString("review"));
			}
			products.get(i+control).setReviews(reviews);
			
			statement2.close();
		}
	}
	/**
	 * Method to setreviews movie
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	
	private void setReviewsMovie(List<Product> products, int[] ids, int control) throws SQLException, Exception {
		for(int i = 0; i<ids.length;i++){
			String sql = "SELECT review FROM moviereview_ar WHERE movie_id ='" + ids[i]+ "'";
			List<String> reviews = new ArrayList<String>();
			Statement statement2 = connect.getConnection().createStatement();
			ResultSet rs3 = statement2.executeQuery(sql);
			while (rs3.next()){
				System.out.println(rs3.getString("review"));
				reviews.add(rs3.getString("review"));
			}
			products.get(i+control).setReviews(reviews);
			
			statement2.close();
		}
	}
	/**
	 * Method to setreratings music
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	
	private void setRatingMusic(List<Product> products, int[] ids, int control) throws Exception {
		for (int i = 0; i<ids.length; i++){
			Statement statement3 = connect.getConnection().createStatement();
			
			String sql = "SELECT rate FROM ratemusic_ar WHERE music_id ='" + ids[i]+ "'";
			ResultSet rs4 = statement3.executeQuery(sql);
			List<Integer> ratings = new ArrayList<Integer>();
			int nratings = 0;
			double rate = 0;
			while(rs4.next()){
				ratings.add(rs4.getInt("rate"));
				rate += rs4.getInt("rate");
				nratings = nratings + 1;
			}
			rate = rate/nratings;
			products.get(i).setRate(rate);
		}
		
	}
	/**
	 * Method to setreviews electronic
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	private void setRatingElectronic(List<Product> products, int[] ids, int control) throws Exception {
		for (int i = 0; i<ids.length; i++){
			Statement statement3 = connect.getConnection().createStatement();
			
			String sql = "SELECT rate FROM rateelectronic_ar WHERE electronic_id ='" + ids[i]+ "'";
			ResultSet rs4 = statement3.executeQuery(sql);
			List<Integer> ratings = new ArrayList<Integer>();
			int nratings = 0;
			double rate = 0;
			while(rs4.next()){
				ratings.add(rs4.getInt("rate"));
				rate += rs4.getInt("rate");
				nratings = nratings + 1;
			}
			rate = rate/nratings;
			products.get(i+control).setRate(rate);
		}
		
	}
	/**
	 * Method to setreviews movie
	 * @param ProductList
	 * @param ids
	 * @param control:To keep control of the position
	 * @throws SQLException, Exception
	 */
	private void setRatingMovie(List<Product> products, int[] ids, int control) throws Exception {
		for (int i = 0; i<ids.length; i++){
			Statement statement3 = connect.getConnection().createStatement();
			
			String sql = "SELECT rate FROM ratemovie_ar WHERE movie_id ='" + ids[i]+ "'";
			ResultSet rs4 = statement3.executeQuery(sql);
			List<Integer> ratings = new ArrayList<Integer>();
			int nratings = 0;
			double rate = 0;
			while(rs4.next()){
				ratings.add(rs4.getInt("rate"));
				rate += rs4.getInt("rate");
				nratings = nratings + 1;
			}
			rate = rate/nratings;
			products.get(i+control).setRate(rate);
		}
		
	}
	
	/**
	 * Method to showtables from db to see the elements
	 * @param Name of the table you want to see main elements
	 * @throws SQLException, Exception
	 */
	
	public void showTable(String nametable) throws SQLException, Exception {
		ResultSet rs;
		String sql;
		try {
			statement = connect.getConnection().createStatement();
			switch (nametable) {
			case "customers_ar":
				sql = "SELECT * FROM customers_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("id") + rs.getString("username"));
				}
				break;
			case "sellers_ar":
				sql = "SELECT * FROM sellers_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("username"));
				}
				break;
			case "music_ar":
				sql = "SELECT * FROM music_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "electronic_ar":
				sql = "SELECT * FROM electronic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "movie_ar":
				sql = "SELECT * FROM movie_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "products_ar":
				sql = "SELECT * FROM products_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("PID:" + rs.getString("product_id"));
					System.out.println("MUID:" + rs.getString("music_id"));
					System.out.println("ELID:" + rs.getString("electronic_id"));
					System.out.println("MOID:" + rs.getString("movie_id"));
				}
				break;
			case "orders_ar":
				sql = "SELECT * FROM orders_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("CID:" + rs.getString("customer_id"));
					System.out.println("OID:" + rs.getString("order_id"));
				}
				break;	
			case "productlist_ar":
				sql = "SELECT * FROM product_list_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("seller_id"));
					System.out.println("PID:" + rs.getString("product_id"));
				}
				break;
			case "musicreview_ar":
				sql = "SELECT * FROM musicreview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("music_id"));
					System.out.println("PID:" + rs.getString("reviewm_id"));
				}
				break;
			case "electronicreview_ar":
				sql = "SELECT * FROM musicreview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("electronic_id"));
					System.out.println("PID:" + rs.getString("reviewe_id"));
				}
				break;
			case "moviereview_ar":
				sql = "SELECT * FROM moviereview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("movie_id"));
					System.out.println("PID:" + rs.getString("reviewmov_id"));
				}
				break;
			case "ratemusic_ar":
				sql = "SELECT * FROM ratemusic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("music_id"));
					System.out.println("PID:" + rs.getString("ratem_id"));
				}
				break;
			case "rateelectronic_ar":
				sql = "SELECT * FROM rateelectronic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("electronic_id"));
					System.out.println("PID:" + rs.getString("ratee_id"));
				}
				break;
			case "ratemovie_ar":
				sql = "SELECT * FROM ratemovie_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("movie_id"));
					System.out.println("PID:" + rs.getString("ratemo_id"));
				}
				break;
			}
				statement.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Method to search product by name
	 * @param Name
	 * @throws SQLException, Exception
	 */
	@Override
	public List<Product> searchProductbyName(String name) throws SQLException, Exception {
		ResultSet rs;
		String sql;
		List<Product> products = new ArrayList<>();
		try {
			statement = connect.getConnection().createStatement();
			sql = "SELECT * FROM music_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Music(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("author"), rs.getString("album_name"));
				products.add(p);
			}
			sql = "SELECT * FROM electronic_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Electronic(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("specifications"), rs.getString("brand"));
				products.add(p);
			}
			sql = "SELECT * FROM movie_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Movie(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("duration"), rs.getString("trailer"));
				products.add(p);
			}
			return products;
		}
		catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		statement.close();
		return null;
	}
}
