package com.domanski.backend.admin.order.service;

import com.domanski.backend.admin.order.model.AdminOrder;
import com.domanski.backend.admin.order.model.dto.AdminOrderStats;
import com.domanski.backend.admin.order.repository.AdminOrderRepository;
import com.domanski.backend.common.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsService {

    private final AdminOrderRepository adminOrderRepository;

    public AdminOrderStats getStatistics() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        List<AdminOrder> orders = adminOrderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(
                from,
                to,
                OrderStatus.COMPLETED);

        TreeMap<Integer, AdminOrderStatsValue> result =
                IntStream.rangeClosed(from.getDayOfMonth(), to.getDayOfMonth())
                        .boxed()
                        .map(i -> aggregateValues(i, orders))
                        .collect(toMap(
                                AdminOrderStatsValue::day,
                                value -> value,
                                (t, t2) -> {
                                    throw new IllegalArgumentException();
                                },
                                TreeMap::new
                        ));

        return AdminOrderStats.builder()
                .labels(result.keySet().stream().toList())
                .order(result.values().stream()
                        .map(AdminOrderStatsValue::orders).toList())
                .sales(result.values().stream()
                        .map(AdminOrderStatsValue::sales).toList())
                .ordersCount(getOrdersCount(result))
                .salesSum(getSalesSum(result))
                .build();
    }

    private static BigDecimal getSalesSum(TreeMap<Integer, AdminOrderStatsValue> result) {
        return result.values().stream().map(AdminOrderStatsValue::sales).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    private static Long getOrdersCount(TreeMap<Integer, AdminOrderStatsValue> result) {
        return result.values().stream().map(AdminOrderStatsValue::orders).reduce(Long::sum).orElse(0L);
    }

    private AdminOrderStatsValue aggregateValues(Integer i,
                                                 List<AdminOrder> orders) {
        return orders.stream()
                .filter(adminOrder -> adminOrder.getPlaceDate().getDayOfMonth() == i)
                .map(AdminOrder::getGrossValue)
                .reduce(new AdminOrderStatsValue(i, BigDecimal.ZERO, 0L),
                        (AdminOrderStatsValue o, BigDecimal v) -> new AdminOrderStatsValue(i, o.sales().add(v), o.orders() + 1),
                        (o, o2) -> null
                );
    }
    private record AdminOrderStatsValue(Integer day, BigDecimal sales, Long orders) {}
}
