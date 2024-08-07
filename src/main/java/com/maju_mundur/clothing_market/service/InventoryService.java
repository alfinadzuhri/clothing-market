package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.dto.request.InventoryRequest;
import com.maju_mundur.clothing_market.dto.response.InventoryResponse;

public interface InventoryService {
    InventoryResponse create (InventoryRequest inventoryRequest);
}
