package com.eazybytes.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoanInfoRecord(String message, Map<String,String>  contactDetails, ArrayList<String> OnCallSupport) {
}
