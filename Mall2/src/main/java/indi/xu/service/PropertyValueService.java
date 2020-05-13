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

    PropertyValue findByPidAndPyid(int pid, int pyid);

    /**
     * 初始化某个产品对应的属性值，初始化逻辑：
     * 1. 根据分类获取所有的属性
     * 2. 遍历每一个属性
     * 2.1 根据属性和产品，获取属性值
     * 2.2 如果属性值不存在，就创建一个属性值对象
     * <p>
     * 放到service
     *
     * @param p
     */
    void init(Product p);
}
