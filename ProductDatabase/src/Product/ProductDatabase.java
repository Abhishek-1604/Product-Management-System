package Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ProductDatabase {

    public static void printMenu() {

        System.out.println("\t\t\t\t Product Creation\n");

       

            System.out.println("1. To Create a new product");
            System.out.println("2. To Remove a  product");
            System.out.println("3. To Update a  product");
            System.out.println("4. To Display a  product");
            System.out.println("5. To Search a  product");
            System.out.println("6. To Exit the Application");

    }
       

    private static void ToCreateProduct() {

        System.out.println("Welcome - Creating a new Product");
        Scanner s = new Scanner(System.in);
        ProductClass obj = new ProductClass();
 


        System.out.print("Enter Product name: ");
        obj.setPrdName(s.next());

        System.out.print("Enter Product Cost: ");
        obj.setPrdCost(s.nextFloat());

        System.out.print("Enter Product Quantity: ");
        obj.setPrdQuantity(s.nextInt());

        System.out.print("Enter Product Expiry: ");
        obj.setPrdExpiry(s.next());

        DbOperations.toAddProductData(obj);

    }

    private static void ToRemoveProduct() {
        System.out.println("Welcome - Remove a  Product");
        Scanner input = new Scanner(System.in);
        System.out.println("Product Id is: ");
        int id = input.nextInt();

       DbOperations.toRemoveProduct(id);
    }

    private static void ToUpdateProduct() {

        System.out.println("\t\t\t\tWelcome to Update a Product");
        Scanner input = new Scanner(System.in);
        System.out.println("Product Id is: ");
        int id = input.nextInt();

        if (DbOperations.PrdExists(id)) {
            System.out.println("1. To Update a Product Name");
            System.out.println("2. To Update a Product Cost");
            System.out.println("3. To Update a Product Quantity");
            System.out.println("4. To Update a Product Expiry");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the Updated Name");
                    String name = input.next();
                    DbOperations.toUpdatePrdName(name,id);
                    break;
                case 2:
                    System.out.println("Enter the Updated cost");
                    Float cost = input.nextFloat();
                    DbOperations.toUpdatePrdCost(cost, id);
                    break;
                case 3:
                    System.out.println("Enter the Updated quantity");
                    int Quantity = input.nextInt();
                    DbOperations.toUpdatePrdQuantity(Quantity, id);
                    break;
                case 4:
                    System.out.println("Enter the Updated expiry date");
                    String Expiry = input.next();
                    DbOperations.toUpdatePrdExpiry(Expiry, id);
                    break;

                default:
                    System.err.println("Invalid Choice\n");
                    return;
            }
            System.out.println("Updated \n");
            ToDisplayProduct();
        } else {
            System.out.println(" Id Doesn't Exist\n");
        }

    }

    private static void ToDisplayProduct() {
    HashMap<Integer, ProductClass> ProductData1 = DbOperations.toDisplayProduct();
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------");
        System.out.println("Product ID \t\t Product Name \t\t Product Cost \t\t Product Quantity \t\t Product Expiry");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, ProductClass> ProductData : ProductData1.entrySet()) {
            System.out.print(ProductData.getKey() + "\t\t\t");
            System.out.print(ProductData.getValue().getPrdName() + "\t\t\t");
            System.out.print(ProductData.getValue().getPrdCost() + "\t\t\t");
            System.out.print(ProductData.getValue().getPrdQuantity() + "\t\t\t");
            System.out.println(ProductData.getValue().getPrdExpiry());
        }

        System.out.println(
                "------------------------------------------------------------------------------------------------------------------");
        System.out.println("Printed Product Data Successfully\n");

    }

    private static void ToSearchProduct() {

        System.out.println("Welcome - Search a  Product");
        Scanner input = new Scanner(System.in);
        System.out.println("Product Id is: ");
        int id = input.nextInt();
        if (DbOperations.PrdExists(id)) {
        	ProductClass ProductData = DbOperations.toGetPrdData(id);
            System.out.println("Product name : " + ProductData.getPrdName());
            System.out.println("Product Cost : " + ProductData.getPrdCost());
            System.out.println("Product Quality : " + ProductData.getPrdQuantity());
            System.out.println("Product Expiry : " + ProductData.getPrdExpiry());
            System.out.println("Displayed successfully\n");

        } else {
            System.out.println("ID doesn't Exist\n");
        }
    }
    
    public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("\t\t\tProduct Database Application\n");
		boolean flag = true;
		while (flag) {
			printMenu();
			System.out.println("Enter your choice");
			int choice = s.nextInt();
			switch (choice) {
			case 1:
				ToCreateProduct();
				break;
			case 2:
				ToRemoveProduct();
				break;
			case 3:
				ToUpdateProduct();
				break;
			case 4:
				ToDisplayProduct();
				break;
			case 5:
				ToSearchProduct();
				break;
			case 6:
				flag = false;
				break;

			default:
				System.err.println("Invalid choice");
				break;
			} 
		}
		System.out.println("Have a good day");
	}
   
}




