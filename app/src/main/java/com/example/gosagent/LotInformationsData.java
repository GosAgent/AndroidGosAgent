package com.example.gosagent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LotInformationsData {
    public List<String> lotConfigs = new ArrayList<>();
    public String numberLot;
    public String linkLot;
    public String locationLot;
    public String initialPriceLot;
    public String monthlyPaymentLot;

    public static List<String> getTeg() {
        List<String> allTeg = Arrays.asList(
                "organizers",
                "number",
                "fossil",
                "name",
                "description",
                "location",
                "work",
                "square",
                "object",
                "transmission_methods",
                "initial_price",
                "term",
                "services",
                "monthly_payment",
                "link"
        );
        return allTeg;
    }

    public LotInformationsData(List<String> lotConfigs) {
        this.lotConfigs.addAll(lotConfigs);
    }

    public LotInformationsData() { }

}