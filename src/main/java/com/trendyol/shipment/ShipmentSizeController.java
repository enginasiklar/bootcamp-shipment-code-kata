package com.trendyol.shipment;
import java.util.HashMap;

class ShipmentSizeController {
    private static final Integer GROUP_THRESHOLD = 3;
    private final HashMap<ShipmentSize, Integer> shipmentSizeMap = new HashMap<>();
    private final Basket basket;
    private final ShipmentSize[] sizes;

    public ShipmentSizeController(Basket basket) {
        this.basket = basket;
        this.sizes = ShipmentSize.values();
    }

    public ShipmentSize findShipmentSize() {
        countShipmentSizes();
        return (basket.getBasketSize() >= GROUP_THRESHOLD) ? findLargestGroup() : findLargestSize();
    }

    private void countShipmentSizes() {
        for (Product item : basket.getProducts()) {
            shipmentSizeMap.put(item.getSize(), shipmentSizeMap.getOrDefault(item.getSize(), 0) + 1);
        }
    }

    private ShipmentSize findLargestGroup() {
        for (int i = sizes.length - 1; i >= 0; i--) {
            if (shipmentSizeMap.getOrDefault(sizes[i], 0) >= GROUP_THRESHOLD) {
                return sizes[i].upgradeShipmentSize();
            }
        }
        return findLargestSize();
    }

    private ShipmentSize findLargestSize() {
        for (int i = sizes.length - 1; i >= 0; i--) {
            if (shipmentSizeMap.getOrDefault(sizes[i], 0) > 0) {
                return sizes[i];
            }
        }
        return null;
    }
}
