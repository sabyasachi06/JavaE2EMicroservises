package com.eazybytes.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record CardsInfoRecord(String message, Map<String, String> contactDetails, ArrayList<String> OnCallSupport) {
}
