package org.gradle;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.gradle.domain.Make;
import org.gradle.domain.Model;
import org.gradle.service.MakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Testing service for work with make entity.
 * @author Artsiom_Buyevich
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/context.xml")
@Transactional
public class MakeTestService {

    @Autowired
    private MakeService makeService;

    @Test
    public void test() {
        Make make = createMake("VW");
        Model model1 = createModel("GOLF IV",
                DateUtils.createDate(1, Calendar.JANUARY, 1999),
                DateUtils.createDate(1, Calendar.JANUARY, 2003));
        Model model2 = createModel("POLO",
                DateUtils.createDate(1, Calendar.JANUARY, 1997),
                DateUtils.createDate(1, Calendar.JANUARY, 2015));
        make.setModels(new ArrayList<Model>(Arrays.asList(model1, model2)));

        int firstFindAll = makeService.findAll().size();

        makeService.save(make);
        Long makeId = make.getId();

        assertNotNull("Object should exist", makeService.get(makeId));
        assertTrue(compareMakeValues(make, makeService.get(makeId)));

        Make updateMake = createMakeWithOneModel("Audi", "100",
                DateUtils.createDate(1, Calendar.JANUARY, 1990),
                DateUtils.createDate(1, Calendar.JANUARY, 2003));
        updateMake.setId(makeId);

        makeService.save(updateMake);
        assertTrue(compareMakeValues(updateMake, makeService.get(makeId)));

        int secondFindAll = makeService.findAll().size();
        if (firstFindAll + 1 != secondFindAll) {
            fail("The collection size should have increased by 1");
        }

        makeService.delete(makeId);
        assertNull("Object should exist", makeService.get(makeId));

        int thirdFindAll = makeService.findAll().size();

        if (firstFindAll != thirdFindAll) {
            fail("The collection size should have be the same as original");
        }

    }

    /**
     * Create make which contains one model.
     * @param makeName name of make
     * @param modelName name of model
     * @param startDate start date
     * @param endDate end date
     * @return make instance
     */
    private Make createMakeWithOneModel(String makeName, String modelName,
            Date startDate, Date endDate) {
        Make make = createMake(makeName);
        Model model = createModel(modelName, startDate, endDate);
        make.setModels(new ArrayList<Model>(Arrays.asList(model)));
        return make;
    }

    /**
     * Create make car.
     * @param makeName name of make name
     * @return make instance
     */
    private Make createMake(String makeName) {
        Make make = new Make();
        make.setName(makeName);
        return make;
    }

    /**
     * Create model.
     * @param name name of model
     * @param startDate start date
     * @param endDate end date
     * @return model instance
     */
    private Model createModel(String name, Date startDate, Date endDate) {
        Model model = new Model();
        model.setName(name);
        model.setStartDate(startDate);
        model.setEndDate(endDate);
        return model;
    }

    /**
     * Compare two makes by field. Also compare contains list of models.
     * @param make1 first make
     * @param make2 second make
     * @return true, if compare, else false
     */
    private boolean compareMakeValues(Make make1, Make make2) {
        boolean eq = new EqualsBuilder()
                .append(make1.getId(), make2.getId())
                .append(make1.getName(), make2.getName())
                .isEquals();
        return eq && compareCollections(make1.getModels(), make2.getModels());
    }

    /**
     * Compare two list of models.
     * @param models1 first collection of models
     * @param models2 second collection of models
     * @return true, if compare, else false
     */
    private boolean compareCollections(Collection<Model> models1, Collection<Model> models2) {
        if (models1.size() != models2.size()) {
            return false;
        }
        List<Model> notEqModels = new ArrayList<>();
        notEqModels.addAll(models2);
        for (Model model1 : models1) {
            for (Model model2 : models2) {
                boolean eq = new EqualsBuilder()
                        .append(model1.getId(), model2.getId())
                        .append(model1.getName(), model2.getName())
                        .append(model1.getStartDate(), model2.getStartDate())
                        .append(model1.getEndDate(), model2.getEndDate())
                        .isEquals();
                if (eq) {
                    notEqModels.remove(model2);
                }
            }
        }
        return notEqModels.isEmpty();
    }

}
