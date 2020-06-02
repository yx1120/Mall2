package indi.xu.service;

import indi.xu.domain.Category;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:24
 */
public interface CategoryService {

    void add(Category bean);

    void delete(int cid);

    void deleteByParentId(int parentId);

    void update(Category bean);

    Category get(int cid);

    List<Category> findByLevel(int level);

    List<Category> findByParentId(int parentId);

    Category findByName(String cname);

    List<Category> findByFidAndSize(int fid, int size);
}
