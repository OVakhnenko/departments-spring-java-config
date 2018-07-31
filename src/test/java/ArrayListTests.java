import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ArrayListTests {
    private List<String> list;

    @Before
    public void before_test() {
        list = new ArrayList();
    }

    @Test
    public void test_one_element() {
        list.add("test");
        if (list.size() != 1) {
            throw new AssertionError();
        }
    }

    @Test
    public void test_one_element_assert() throws Exception {
        list.add("test");
        Assert.assertTrue(list.get(0).equals("test"));
    }

    @Test
    public void test_one_element_hamcrest_long() throws Exception {
        list.add("test");
        Assert.assertThat(list.get(0), Matchers.is("test"));
    }

    @Test
    public void test_one_element_hamcrest_short() throws Exception {
        list.add("test");
        assertThat(list.get(0), is("test"));
    }

}
