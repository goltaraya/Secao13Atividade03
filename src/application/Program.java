package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItems;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {
	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter client data:");
		System.out.print("Name: ");
		String clientName = sc.nextLine();

		System.out.print("Email: ");
		String clientEmail = sc.nextLine();

		System.out.print("Birth date (DD/MM/YYYY): ");
		Date clientBirthDate = sdf.parse(sc.nextLine());

		Client client = new Client(clientName, clientEmail, clientBirthDate);

		System.out.println("Enter order data:");
		System.out.print("Status: ");
		String status = sc.nextLine();

		System.out.print("How many items to this order? ");
		int n = sc.nextInt();

		Order order = new Order(new Date(), OrderStatus.valueOf(status), client);

		for (int i = 0; i < n; i++) {
			System.out.println("\nEnter " + (i + 1) + "# item data:");
			System.out.print("Product name: ");
			sc.nextLine();
			String productName = sc.nextLine();

			System.out.print("Product price: ");
			double productPrice = sc.nextDouble();

			System.out.print("Quantity: ");
			int productQuantity = sc.nextInt();

			Product product = new Product(productName, productPrice);
			OrderItems item = new OrderItems(productQuantity, productPrice, product);
			order.addItem(item);
		}

		System.out.println(order.toString());

		sc.close();
	}
}
