package com.trendyol.shipment;
import java.util.HashMap;
import java.util.Map;

class ShipmentSizeController {
    HashMap<ShipmentSize, Integer> shipmentSizeMap = new HashMap<>();
    Basket basket;
    Integer groupThreshold = 3;
    public ShipmentSizeController(Basket basket){
        this.basket = basket;
    }
    public ShipmentSize findShipmentSize() {
        countShipmentSizes();
        return (basket.getBasketSize() >= groupThreshold) ? findLargestGroup() : findLargestSize();
    }
    private void countShipmentSizes(){
        for (Product item : basket.getProducts())
            shipmentSizeMap.put(item.getSize(), shipmentSizeMap.getOrDefault(item.getSize(), 0) + 1);
    }
    private ShipmentSize findLargestGroup()  {
        ShipmentSize largestSize = null;

        for (Map.Entry<ShipmentSize, Integer> entry : shipmentSizeMap.entrySet()) {
            if (entry.getValue() >= groupThreshold) {
                if (largestSize == null || entry.getKey().compareTo(largestSize) > 0) {
                    largestSize = entry.getKey();
                }
            }
        }
        return (largestSize != null) ? largestSize.upgradeShipmentSize() : findLargestSize();
    }
    private ShipmentSize findLargestSize() {
        ShipmentSize largestSize = null;

        for (ShipmentSize size : shipmentSizeMap.keySet()) {
            if (largestSize == null || size.compareTo(largestSize) > 0) {
                largestSize = size;
            }
        }
        return largestSize;
    }
}
