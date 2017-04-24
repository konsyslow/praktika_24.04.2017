package tests;

import com.company.SumCalculating;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by admin on 10.04.2017.
 */
public class MainTest {

    private static SumCalculating sumCalculating;

    @Test
    public void calculateSumTest(){

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(-4);
        list.add(4);

        Class c = sumCalculating.getClass();
        Class[] paramTypes = new Class[] { Integer.class};
        try {
            Method method = c.getDeclaredMethod("calculateSum", paramTypes);
            method.setAccessible(true);

            int sum = 0;
            for (Integer num : list) {
                //sum =  sumCalculating.calculateSum(num);
                Object[] args = new Object[]{new Integer(num)};
                sum = (Integer) method.invoke(sumCalculating, args);

            }

            assertEquals(6,sum);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void isIntegerTest(){

        boolean num1 = sumCalculating.isInteger("235");
        boolean num2 = sumCalculating.isInteger("asd5");
        assertTrue(num1);
        assertFalse(num2);
    }

    @Test
    public void readFileTest(){

        List<Integer> list = new ArrayList<>();
        sumCalculating.readFile("file1.txt");
        List<Integer> list2 = new ArrayList<>();
        list2.add(-2);
        list2.add(-3);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(2);

        assertEquals(6, list.size());
        assertArrayEquals(list2.toArray(),list.toArray());
    }

    @Test
    public void readFromURLTest(){

        List<Integer> list = new ArrayList<>();
        try {
            URL url1 = new URL("https://gist.githubusercontent.com/anonymous/356beefaaa42e07873ef6f61b21b160b/" +
                    "raw/c5dacb906d5f3ffc8d13e767594f661cfaeb43d9/file1.txt");
            sumCalculating.readFromURL(url1);

            List<Integer> list2 = new ArrayList<>();
            list2.add(-2);
            list2.add(-3);
            list2.add(3);
            list2.add(4);
            list2.add(5);
            list2.add(2);

            assertEquals(6, list.size());
            assertArrayEquals(list2.toArray(),list.toArray());

        }catch (MalformedURLException e){
            System.out.println(" wrong url");
        }

    }

    @After
    public  void clearAll(){
        sumCalculating = new SumCalculating("file1.txt");
    }

    @BeforeClass
    public static void init(){

        sumCalculating = new SumCalculating("file1.txt");
    }
}