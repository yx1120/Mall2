package indi.xu.service;


import indi.xu.domain.ProductImage;

import java.util.List;

/**
 * @author a_apple
 * @create 2019-12-03 16:26
 */
public interface ProductImageService {
    String TYPE_SINGLE = "type_single";
    String TYPE_DETAIL = "type_detail";

    void add(ProductImage bean);

    void delete(int gid);

    ProductImage get(int gid);

    List<ProductImage> list(int pid, String type);
}
