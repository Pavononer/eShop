package Model;

import java.sql.SQLException;
import java.util.List;

import application.Product;
import application.User;
/**
 * 
 * @author arturopavon and raquelnoblejas
 *
 */
public interface daoModel {
	/**
	 * 
	 * daoModel interface to proceed with the façade pattern
	 *
	 */
	public void createTables() throws Exception, SQLException;

	public void insertUser(User u) throws Exception;

	public void insertProduct(Product p, User u) throws Exception;

	public void insertRating(double rate, Product p) throws Exception;
	
	public List<Product> getCart(User u) throws Exception;

	public void insertOrder(Product p, User u) throws Exception;

	public void insertCart(Product p, User u) throws Exception;

	public void insertProductList(Product p, User u) throws Exception;
	
	public int returnUser(User u) throws Exception;

	public int searchIdUser(User u) throws Exception;

	public int searchIdProduct(Product p) throws Exception;

	public void deleteUser(User u) throws Exception;

	public void deleteProduct(Product p) throws Exception;

	public List<Product> getProducts() throws Exception;

	public void showTable(String nametable) throws Exception;
	
	public List<Product> searchProductbyName(String name) throws Exception;

}
