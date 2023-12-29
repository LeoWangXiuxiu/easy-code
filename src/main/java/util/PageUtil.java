package util;

import cn.hutool.core.lang.Dict;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 封装分页方法
 */
public class PageUtil {

    public static <T> Dict page(List<T> list, Integer pageNum, Integer pageSize) {
        int total = list.size();
        List<T> pageList = list.stream() // 将list转换成Stream
                .skip(pageSize * (pageNum - 1)) // 跳过前面的元素
                .limit(pageSize) // 取出指定数量的元素
                .collect(Collectors.toList());  // 转换成List集合
        Dict dict = new Dict();
        dict.set("total", total);
        dict.set("data", pageList);
        return dict;
    }
}
