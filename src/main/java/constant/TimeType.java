package constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TimeType {
    hour(1, "时"),
    minute(2, "分"),
    second(3, "秒"),
    ;

    public final int value;
    public final String text;


    public static TimeType getByValue(final Integer value) {
        final TimeType[] enums = TimeType.values();
        for (TimeType one : enums) {
            if (one.value == value) {
                return one;
            }
        }
        return null;
    }
}
