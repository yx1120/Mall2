package indi.xu.service;


import indi.xu.domain.Property;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:26
 */
public interface PropertyService {

    void add(Property bean);

    void delete(int pyid);

    void update(Property bean);

    Property get(int pyid);

    List<Property> list(int cid);
}
