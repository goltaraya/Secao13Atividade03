package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	private Date moment;
	private OrderStatus status;
	private Client client;
	private List<OrderItems> orderList = new ArrayList<OrderItems>();

	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void addItem(OrderItems item) {
		orderList.add(item);
	}

	public void removeItem(OrderItems item) {
		orderList.remove(item);
	}

	public Double total() {
		double sum = 0;

		for (OrderItems oi : orderList) {
			sum += oi.subTotal();
		}
		return sum;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n === ORDER SUMMARY ===");
		sb.append("\nOrder Moment: " + sdf2.format(getMoment()));
		sb.append("\nOrder Status: " + getStatus());
		sb.append("\nClient: " + client.getName() + " (" + sdf.format(client.getBirthDate()) + ") - "
				+ client.getEmail());
		sb.append("\nOrder items:");

		for (OrderItems item : orderList) {
			sb.append("\n" + item.getProduct().getName() + ", $" + item.getProduct().getPrice() + ", Quantity: "
					+ item.getQuantity() + ", Subtotal: $" + String.format("%.2f", item.subTotal()));
		}
		sb.append("\nTotal price: $" + total());

		return sb.toString();
	}
}
