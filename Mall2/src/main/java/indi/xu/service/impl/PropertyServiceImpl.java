package indi.xu.service.impl;

import indi.xu.dao.PropertyDao;
import indi.xu.domain.Property;
import indi.xu.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:26
 */
@Service("propertyService")
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Override
    public void add(Property bean) {
        propertyDao.add(bean);
    }

    @Override
    public void delete(int pyid) {
        propertyDao.delete(pyid);
    }

    @Override
    public void update(Property bean) {
        propertyDao.update(bean);
    }

    @Override
    public Property get(int pyid) {
        return propertyDao.get(pyid);
    }

    @Override
    public List<Property> list(int cid) {
        return propertyDao.list(cid);
    }
}
