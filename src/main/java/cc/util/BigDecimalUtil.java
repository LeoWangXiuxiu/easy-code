package cc.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static BigDecimal add(String a, String b) {
        return BigDecimal.valueOf(Double.valueOf(a)).add(BigDecimal.valueOf(Double.valueOf(b)));
    }

    public static BigDecimal multiply(String a, String b) {
        return BigDecimal.valueOf(Double.valueOf(a)).multiply(BigDecimal.valueOf(Double.valueOf(b)));
    }

    public static BigDecimal divide(String a, String b) {
        return BigDecimal.valueOf(Double.valueOf(a)).divide(BigDecimal.valueOf(Double.valueOf(b)), 2);
    }

    public static BigDecimal subtract(String a, String b) {
        return BigDecimal.valueOf(Double.valueOf(a)).subtract(BigDecimal.valueOf(Double.valueOf(b)));
    }

    public static BigDecimal getBigDecimal(String a){
        return BigDecimal.valueOf(Double.valueOf(a));
    }
}
