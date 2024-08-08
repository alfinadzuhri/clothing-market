package com.maju_mundur.clothing_market.service.Impl;

import com.maju_mundur.clothing_market.entity.OrderedHistory;
import com.maju_mundur.clothing_market.repository.OrderedHistoryRepository;
import com.maju_mundur.clothing_market.service.OrderedHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderedHistoryServiceImpl implements OrderedHistoryService {

    private final OrderedHistoryRepository orderedHistoryRepository;

    @Override
    public List<OrderedHistory> createBulk(List<OrderedHistory> orderedHistory) {
        return orderedHistoryRepository.saveAllAndFlush(orderedHistory);
    }
}
