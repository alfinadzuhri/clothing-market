package com.maju_mundur.clothing_market.service;

import com.maju_mundur.clothing_market.entity.OrderedHistory;

import java.util.List;

public interface OrderedHistoryService {
    List<OrderedHistory> createBulk (List<OrderedHistory> orderedHistory);
}
