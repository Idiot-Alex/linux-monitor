package com.hotstrip.linux.monitor.admin.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private PageParams pageParams;
    private List<T> dataList;

    /**
     * get PageResult
     * @param pageParams {@linkplain PageParams}
     * @param count totalCaount
     * @param supplier supplier
     * @param <T> class
     * @return {@linkplain PageResult}
     */
    public static <T> PageResult<T> result(final PageParams pageParams, final int count, final Supplier<List<T>> supplier) {
        if (count > 0) {
            return new PageResult(new PageParams(pageParams.getPageNo(), pageParams.getPageSize(), count), supplier.get());
        }
        return new PageResult(new PageParams(pageParams.getPageNo(), pageParams.getPageSize(), 0), Collections.emptyList());
    }
}
