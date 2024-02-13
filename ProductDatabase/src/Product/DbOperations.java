package Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;



public class DbOperations {
	private static String host = "jdbc:mysql://localhost:3306/productdata";
	private static String userName = "root";
	private static String userPassword = "Abhishek@16";

	private static Connection toCreateConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(host, userName, userPassword);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    
    public static void toAddProductData(ProductClass obj) {
    	try {
    		Connection con = toCreateConnection();
    		String query = "Insert into products(PrdName, PrdCost, PrdQuantity, PrdExpiry) values (?,?,?,?)";
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setString(1, obj.getPrdName());
    		stmt.setFloat(2, obj.getPrdCost());
    		stmt.setInt(3, obj.getPrdQuantity());
    		stmt.setString(4, obj.getPrdExpiry());
    		
    		stmt.executeUpdate();
    		con.close();
    		System.out.println("Thank you- Product Created Successfully\n");
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static HashMap<Integer, ProductClass> toDisplayProduct(){
    	try {
    		Connection con = toCreateConnection();
    		PreparedStatement stmt = con.prepareStatement("Select * from products");
    		
    		ResultSet rowData = stmt.executeQuery();
    		HashMap<Integer, ProductClass> productData = new HashMap<>();
    		while(rowData.next()) {
    			ProductClass obj = new ProductClass();
    			obj.setPrdId(rowData.getInt(1));
    			obj.setPrdName(rowData.getString(2));
    			obj.setPrdCost(rowData.getFloat(3));
    			obj.setPrdQuantity(rowData.getInt(4));
    			obj.setPrdExpiry(rowData.getString(5));
    			productData.put(obj.getPrdId(), obj);
    		}
    		con.close();
    		return productData;
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static boolean PrdExists(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from products where PrdId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			return rowData.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

    public static ProductClass toGetPrdData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from products where PrdId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			rowData.next();
			ProductClass obj = new ProductClass();
			obj.setPrdId(rowData.getInt(1));
			obj.setPrdName(rowData.getString(2));
			obj.setPrdCost(rowData.getFloat(3));
			obj.setPrdQuantity(rowData.getInt(4));
			obj.setPrdExpiry(rowData.getString(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static void toRemoveProduct(int id) {

		try {
			if (PrdExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Delete from products where PrdId = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("Product is Removed Successfully ");
			} else {
				System.out.println("Product does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void toUpdatePrdName(String name, int id) {

		try {
			if (PrdExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Update products set PrdName = ? where PrdId = ? ");
				stmt.setString(1, name);
				stmt.setInt(2, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("ProductName is Updated Successfully ");
			} else {
				System.out.println("Product does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void toUpdatePrdCost(Float Cost, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update products set PrdCost = ? where PrdId = ?");
			stmt.setFloat(1, Cost);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void toUpdatePrdQuantity(int Quantity, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update products set PrdQuantity = ? where PrdId = ?");
			stmt.setInt(1, Quantity);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void toUpdatePrdExpiry(String Expiry, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update products set PrdExpiry = ? where PrdId = ?");
			stmt.setString(1, Expiry);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
