package indi.xu.service.impl;

import indi.xu.dao.PropertyValueDao;
import indi.xu.domain.Product;
import indi.xu.domain.PropertyValue;
import indi.xu.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:27
 */
@Service("properyValueService")
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueDao propertyValueDao;

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
        return null;
    }

    @Override
    public void init(Product p) {

    }
}
