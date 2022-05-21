package org.oka.springmvc.dao;

import java.util.Collections;
import java.util.List;

/**
 * Provides common features to paginate generic <V> lists
 *
 * @param <V>
 */
public class Pageable<V> {

    protected List<V> paginate(final List<V> elements, final int pageSize, final int pageNum) {
        int init = pageNum * pageSize;
        int end = pageNum * pageSize + pageSize;

        if (init > elements.size() || end > elements.size()) {
            return Collections.emptyList();
        }

        return elements.subList(init, end);
    }
}
