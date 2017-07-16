package epam_11;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void testSum() {
        epam_11.Main main = new epam_11.Main();
        Assert.assertEquals("Something went wrong", 4, main.sum(2,2));
    }

    @Test
    public void testGetJackName(){
        epam_11.Main main = new epam_11.Main();
        Assert.assertEquals("Jack", main.getJackName("Jack"));
    }
}
