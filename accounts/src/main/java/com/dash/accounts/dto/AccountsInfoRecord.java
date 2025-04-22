package com.dash.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsInfoRecord(String message, Map<String, String> contactDetails, ArrayList<String> OnCallSupport) {
}
