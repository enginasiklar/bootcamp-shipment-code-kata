package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;
    public ShipmentSize upgradeShipmentSize() {
        int nextSizeOrdinal = Math.min(this.ordinal() + 1, values().length - 1);
        return values()[nextSizeOrdinal];
    }

}