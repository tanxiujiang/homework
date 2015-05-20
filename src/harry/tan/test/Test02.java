package harry.tan.test;

import harry.tan.serviceImpl.CalcuateStopsByMax;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test02 {

    // test 6
    @Test
    public void testStopsByMax() {

        CalculateInter calculate = new CalcuateStopsByMax(3);
        CalculateContext context = new CalculateContext(calculate);
        String testData[] = new String[] { "C-C","A-C","A-D" };
        for (String data : testData) {
            String d[] = data.split("-");
            int distance = context.calculateBySpot(d[0], d[1]);
            System.out.println(distance);
        }
    }
}
