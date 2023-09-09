package com.trendyol.shipment;
import java.util.List;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        ShipmentSizeController controller = new ShipmentSizeController(this);
        return controller.findShipmentSize();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getBasketSize(){ return products.size(); }
}
