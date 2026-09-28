package dev.wonslab.practice.core.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ReceiptRepository {

    private final List<Receipt> receipts = new ArrayList<>();

    public void save(Receipt receipt) {
        receipts.add(receipt);
    }

    public List<Receipt> findAll() {
        return List.copyOf(receipts);
    }

    public long totalSales() {
        return receipts.stream().mapToLong(Receipt::total).sum();
    }

    public Map<String, Long> salesByCustomer() {
        return receipts.stream().collect(Collectors.groupingBy(
                r -> r.order().customer(),
                TreeMap::new,
                Collectors.summingLong(Receipt::total)));
    }

    public List<String> topItems(int n) {
        Map<String, Integer> quantityByItem = receipts.stream().collect(Collectors.groupingBy(
                r -> r.order().item(),
                Collectors.summingInt(r -> r.order().quantity())));
        return quantityByItem.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }
}
