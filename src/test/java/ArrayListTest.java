import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    @Test
    public void test_one_element() throws Exception {
        List<String> list = new ArrayList();
        list.add("test");
        if (list.size() != 1) {
            throw new AssertionError();
        }
    }
}
