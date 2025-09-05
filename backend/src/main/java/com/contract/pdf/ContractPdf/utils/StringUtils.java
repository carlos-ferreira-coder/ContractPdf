package com.contract.pdf.ContractPdf.utils;

public class StringUtils {
    public static String sanitize(String input) {
        if (input == null) return "";
        input = input.trim().replaceAll("[\\n\\r\\t]", " ");
        input = input.replaceAll("[^\\p{L}\\p{N}@.\\- ]", "");
        return input;
    }
}
