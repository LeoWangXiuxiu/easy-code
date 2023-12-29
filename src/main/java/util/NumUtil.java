package util;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumUtil {
    public static BigDecimal div(Long v1, Long v2, int scale, RoundingMode roundingMode) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), scale, roundingMode);
    }

    public static String formatCoverage(Long v1) {
        BigDecimal b = div(v1, 100l, 0, RoundingMode.DOWN);
        return NumberUtil.decimalFormat(",###", b);
    }

    public static String formatFee(Long v1) {
        BigDecimal b = div(v1, 100l, 2, RoundingMode.UP);
        if (b.intValue() >= 1) {
            return NumberUtil.decimalFormat(",###.00", b);
        } else {
            return b.toString();
        }
    }
}
