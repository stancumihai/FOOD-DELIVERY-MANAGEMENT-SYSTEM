package org.stancu.service;

import org.stancu.model.BaseProduct;
import org.stancu.model.MenuItem;
import org.stancu.dao.BaseProductDao;
import java.util.List;

/***
 * The business logic for the BaseProd Class
 */
public class ProductService {

    public static BaseProductDao baseProdDataAccessService = new BaseProductDao();
    public static ProductService baseProdService = null;

    private ProductService( BaseProductDao baseProdDataAccessService) {
        ProductService.baseProdDataAccessService = baseProdDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of BaseProdService
     */
    public static ProductService getInstance() {
        if (baseProdService == null) {
            baseProdService = new ProductService(baseProdDataAccessService);
        }
        return baseProdService;
    }
    /**
     * @param id Integer id
     * @pre id != null
     */
    public MenuItem findById(Integer id) {
        return baseProdDataAccessService.findById(id);
    }
    /**
     * @param id  to update in orders
     * @post return != null
     */
    public MenuItem update(Integer id, BaseProduct model) {
        return baseProdDataAccessService.update(model, id);
    }

    public void insert(MenuItem model) {
        baseProdDataAccessService.insert(model);
    }
    /**
     * @param id  to search in items
     * @post return != null
     */
    public MenuItem delete(Integer id) {
        return baseProdDataAccessService.delete(id);
    }

    public List<MenuItem> selectAll() {
        if (baseProdDataAccessService.selectAll().size() == 0) {
            baseProdDataAccessService.setBaseProducts();
        }
        return baseProdDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return baseProdDataAccessService.getLastId();
    }
}
