package com.trendyol.shipment;
import java.util.List;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        if (isBasketEmpty()) {
            throw new IllegalStateException("Cannot determine size for an empty shipment.");
        }
        ShipmentSizeController controller = new ShipmentSizeController(this);
        return controller.findShipmentSize();
    }

    public boolean isBasketEmpty() { return this.getProducts().isEmpty(); }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getBasketSize(){ return products.size(); }
}
