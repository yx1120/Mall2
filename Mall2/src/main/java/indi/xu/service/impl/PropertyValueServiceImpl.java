package indi.xu.service.impl;

import indi.xu.dao.PropertyDao;
import indi.xu.dao.PropertyValueDao;
import indi.xu.domain.Product;
import indi.xu.domain.Property;
import indi.xu.domain.PropertyValue;
import indi.xu.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
@Service("properyValueService")
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private PropertyDao propertyDao;

    @Override
    public PropertyValue get(int vid) {
        return propertyValueDao.get(vid);
    }


    @Override
    public void add(PropertyValue bean) {
        propertyValueDao.add(bean);
    }

    @Override
    public void update(PropertyValue bean) {
        propertyValueDao.update(bean);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        return propertyValueDao.list(pid);
    }

    @Override
    public PropertyValue findByPidAndPyid(int pid, int pyid) {
        return propertyValueDao.findByPidAndPyid(pid,pyid);
    }

    /**
     * 初始化某个产品对应的属性值，初始化逻辑：
     *      1. 根据分类获取所有的属性
     *      2. 遍历每一个属性
     *          2.1 根据属性和产品，获取属性值
     *          2.2 如果属性值不存在，就创建一个属性值对象
     */
    @Override
    public void init(Product p) {
        // 初始化商品的属性值记录
        List<Property> properties = propertyDao.list(p.getCategory().getCid());
        for (Property property : properties) {
            if (propertyValueDao.findByPidAndPyid(p.getPid(), property.getPyid()) == null) {
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setProduct(p);
                propertyValue.setProperty(property);

                propertyValueDao.add(propertyValue);
            }
        }
    }
}
