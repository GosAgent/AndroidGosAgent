package com.example.gosagent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllLotData {
    public List<String> lotConfigs = new ArrayList<>();

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

    public AllLotData(List<String> lotCongigs) {
        this.lotConfigs.addAll(lotCongigs);
    }

    public AllLotData() { }

}