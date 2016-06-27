package org.gradle.service;

import java.util.ArrayList;
import java.util.List;

import org.gradle.dao.MakeDao;
import org.gradle.dao.ModelDao;
import org.gradle.domain.Make;
import org.gradle.domain.Model;
import org.gradle.exception.NoRequiredAttributesException;
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
    public void save(Make make) {
        if (make == null) {
            throw new IllegalArgumentException();
        }
        validate(make);
        if (make.getId() == null) {
            create(make);
        } else {
            update(make);
        }
    }

    private void validate(Make make) {
        List<String> errors = new ArrayList<>();
        if (make.getName() == null || make.getName().trim().isEmpty()) {
            errors.add("make.name");
        }
        if (make.getModels() == null || make.getModels().isEmpty()) {
            errors.add("make.models");
        } else {
            for (Model model : make.getModels()) {
                if (model.getName() == null
                        || model.getName().trim().isEmpty()) {
                    errors.add("model.name");
                }
                if (model.getStartDate() == null) {
                    errors.add("model.startDate");
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new NoRequiredAttributesException("null value in field(s):"
                    + errors);
        }
    }

    /**
     * Create make with models.
     * @param make make
     */
    private void create(Make make) {
        makeDao.create(make);
            for (Model model : make.getModels()) {
                model.setMake(make);
                modelDao.create(model);
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
