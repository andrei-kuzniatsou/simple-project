package org.gradle.service;

import java.util.ArrayList;
import java.util.List;

import org.gradle.dao.MakeDao;
import org.gradle.dao.ModelDao;
import org.gradle.domain.Make;
import org.gradle.domain.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation service for work with make entity.
 * @author Artsiom_Buyevich
 */
@Service
public class MakeServiceImpl implements MakeService {

    @Autowired
    private MakeDao makeDao;
    @Autowired
    private ModelDao modelDao;

    @Override
    @Transactional
    public void save(Make entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }

    /**
     * Create make with models.
     * @param make make
     */
    private void create(Make make) {
        makeDao.create(make);
        if (make.getModels() != null) {
            for (Model model : make.getModels()) {
                model.setMake(make);
                modelDao.create(model);
            }
        }
    }

    /**
     * Update make with models.
     * @param make make
     */
    private void update(Make make) {
        makeDao.update(make);
        Long makeId = make.getId();

        List<Long> ids = new ArrayList<Long>();
        List<Model> oldModels = modelDao.findByMakeId(makeId);
        for (Model model : oldModels) {
            ids.add(model.getId());
        }
        if (make.getModels() != null) {
            for (Model model : make.getModels()) {
                model.setMake(make);
                if (model.getId() == null) {
                    modelDao.create(model);
                } else {
                    modelDao.update(model);
                    ids.remove(model.getId());
                }
            }
            if (ids.size() > 0) {
                modelDao.deleteOrphanModel(ids);
            }
        }
    }

    @Override
    @Transactional
    public Make get(Long key) {
        return makeDao.read(key);
    }

    @Override
    @Transactional
    public void delete(Long makeId) {
        modelDao.deleteByMakeId(makeId);
        makeDao.delete(makeId);
    }

    @Override
    @Transactional
    public List<Make> findAll() {
        return makeDao.findAll();
    }

}
