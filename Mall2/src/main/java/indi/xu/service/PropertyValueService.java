package indi.xu.service;


import indi.xu.domain.Product;
import indi.xu.domain.PropertyValue;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
public interface PropertyValueService {

    PropertyValue get(int vid);

    void add(PropertyValue bean);

    void update(PropertyValue bean);

    List<PropertyValue> list(int pid);

    void init(Product p);

    PropertyValue findByPidAndPyid(int pid, int pyid);

}
