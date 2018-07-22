package com.vakhnenko.departments;

import com.vakhnenko.departments.config.AppInit;
import com.vakhnenko.departments.config.RootConfig;
import com.vakhnenko.departments.config.WebConfig;
import com.vakhnenko.departments.config.WebInit;
import com.vakhnenko.departments.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInit.class, RootConfig.class, WebConfig.class, WebInit.class})
public class AutowiredTest {

    @Autowired
    @Qualifier("departmentService")
    DepartmentService departmentService;

    @Test
    public void test_ml_always_return_true() {
        assertThat(departmentService, instanceOf(DepartmentService.class));
        assertThat(departmentService.list(), isA(List.class));
    }
}
