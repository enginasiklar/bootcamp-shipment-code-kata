package com.trendyol.shipment;
import java.util.Arrays;
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
        return (basket.getBasketSize() >= GROUP_THRESHOLD) ? findGroupWithLargestShipmentSize() : findLargestShipmentSize();
    }

    private void countShipmentSizes() {
        for (Product item : basket.getProducts()) {
            shipmentSizeMap.put(item.getSize(), shipmentSizeMap.getOrDefault(item.getSize(), 0) + 1);
        }
    }
    private ShipmentSize findGroupWithLargestShipmentSize() {
        return Arrays.stream(sizes)
                .filter(size -> shipmentSizeMap.getOrDefault(size, 0) >= GROUP_THRESHOLD)
                .findFirst()
                .map(ShipmentSize::upgradeShipmentSize)
                .orElseGet(this::findLargestShipmentSize);
    }

    private ShipmentSize findLargestShipmentSize()  {
        return Arrays.stream(sizes)
                .filter(size -> shipmentSizeMap.getOrDefault(size, 0) > 0)
                .findFirst()
                .orElse(null);
    }
}
