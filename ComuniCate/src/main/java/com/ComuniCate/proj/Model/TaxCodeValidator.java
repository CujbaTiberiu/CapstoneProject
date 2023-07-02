package com.ComuniCate.proj.Model;

import java.util.regex.Pattern;

public class TaxCodeValidator {

    private static final String CF_PATTERN = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";
    private static final Pattern PATTERN = Pattern.compile(CF_PATTERN);

    public static boolean validateTaxCode(String codiceFiscale) {
        if (!PATTERN.matcher(codiceFiscale).matches()) {
            return false;
        }
        
        String evenMap = "BAFHJNPRTVCESULDGIMOQKWZYX";
        int s = 0;
        
        for (int i = 0; i < 15; i++) {
            char c = codiceFiscale.charAt(i);
            int n = 0;
            
            if (Character.isDigit(c)) {
                n = Character.getNumericValue(c);
            } else {
                n = c - 'A';
            }
            
            if ((i & 1) == 0) {
                n = evenMap.charAt(n) - 'A';
            }
            
            s += n;
        }
        
        char expectedChecksum = (char) ((s % 26) + 'A');
        char actualChecksum = codiceFiscale.charAt(15);
        
        return expectedChecksum == actualChecksum;
    }
}
