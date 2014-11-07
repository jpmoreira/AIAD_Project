package Others;

import Products.Product;

public class Bid {
	
	Product product;


	int quantity;
	int deadline;
	int unitaryPrice;
	
	public Bid(Product p,int qtt, int d,int unPrice){
		
		product=p;
		quantity=qtt;
		deadline=d;
		unitaryPrice=unPrice;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(int unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

}
