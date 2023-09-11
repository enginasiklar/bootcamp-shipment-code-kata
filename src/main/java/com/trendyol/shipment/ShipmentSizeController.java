package com.trendyol.shipment;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

class ShipmentSizeController {
    private static final Integer GROUP_THRESHOLD = 3;
    private final HashMap<ShipmentSize, Integer> shipmentSizeMap = new HashMap<>();
    private final Basket basket;
    private final ShipmentSize[] sizes;

    public ShipmentSizeController(Basket basket) {
        this.basket = basket;
        this.sizes = ShipmentSize.values();
        populateShipmentSizeMap();
    }

    public ShipmentSize findShipmentSize() {
        return (basket.getBasketSize() >= GROUP_THRESHOLD) ? findGroupWithLargestShipmentSize() : findLargestShipmentSize();
    }

    private void populateShipmentSizeMap() {
        basket.getProducts().forEach(item -> shipmentSizeMap.merge(item.getSize(), 1, Integer::sum));
    }

    private ShipmentSize findGroupWithLargestShipmentSize() {
        return Arrays.stream(sizes)
                .sorted(Comparator.reverseOrder())
                .filter(size -> shipmentSizeMap.getOrDefault(size, 0) >= GROUP_THRESHOLD)
                .findFirst()
                .map(ShipmentSize::upgradeShipmentSize)
                .orElseGet(this::findLargestShipmentSize);
    }

    private ShipmentSize findLargestShipmentSize() {
        return Arrays.stream(sizes)
                .sorted(Comparator.reverseOrder())
                .filter(size -> shipmentSizeMap.getOrDefault(size, 0) > 0)
                .findFirst()
                .orElse(null);
    }

}

