package com.json.ignore.util;

import com.json.ignore.filter.file.FileConfig;
import com.json.ignore.filter.file.FileConfigTest;
import com.json.ignore.mock.MockClasses;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class AnnotationUtilTest {

    @Test
    public void testClassExists() {
        Class clazz = AnnotationUtil.getClassByName("com.json.ignore.util.FileUtilTest");
        assertNotNull(clazz);
    }

    @Test
    public void testClassNotExists() {
        Class clazz = AnnotationUtil.getClassByName("com.json.ignore.NotExistedClass");
        assertNull(clazz);
    }

    @Test
    public void testClassEmpty() {
        Class clazz = AnnotationUtil.getClassByName("");
        assertNull(clazz);
    }

    @Test
    public void testClassNull() {
        Class clazz = AnnotationUtil.getClassByName(null);
        assertNull(clazz);
    }

    @Test
    public void testGetStrategyFieldsNotNull() {
        FileConfig config = MockClasses.getMockAdminFileConfig();
        assertNotNull(config);

        Map<Class, List<String>> fields = AnnotationUtil.getStrategyFields(config.getControllers().get(0).getStrategies().get(0));
        assertTrue(fields.keySet().size() > 0);
    }

    @Test
    public void testGetStrategyNull() {
        FileConfig config = MockClasses.getMockAdminFileConfig();
        assertNotNull(config);

        Map<Class, List<String>> fields = AnnotationUtil.getStrategyFields(null);
        assertEquals(0, fields.keySet().size());
    }

    @Test
    public void testGetStrategyFieldsEmptyStrategy() {
        FileConfig.Strategy strategy = new FileConfig.Strategy();
        Map<Class, List<String>> fields = AnnotationUtil.getStrategyFields(strategy);
        assertEquals(0, fields.keySet().size());


    }

    @Test
    public void testGetStrategyFieldsNull() {
        FileConfig.Strategy strategy = new FileConfig.Strategy();
        Map<Class, List<String>> fields = AnnotationUtil.getStrategyFields(strategy);
        assertEquals(0, fields.keySet().size());
    }

    @Test
    public void testGetStrategyFieldsMultiple() {
        FileConfig mockConfig = MockClasses.getMockAdminFileConfig();

        FileConfig.Strategy strategy = mockConfig.getControllers().get(0).getStrategies().get(0);

        FileConfig.Filter filter = new FileConfig.Filter();
        filter.setClassName(strategy.getFilters().get(0).getClassName());
        filter.getFields().add(new FileConfig.Field().setName("password"));
        filter.getFields().add(new FileConfig.Field().setName("email"));
        filter.getFields().add(new FileConfig.Field().setName("email"));


        strategy.getFilters().add(filter);
        strategy.getFilters().add(filter);

        Map<Class, List<String>> fields = AnnotationUtil.getStrategyFields(strategy);
        assertEquals(3, fields.get(FileConfigTest.class).size());
    }



}