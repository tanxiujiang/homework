package harry.tan.test;

import harry.tan.serviceImpl.CalcuateStopsByExactly;
import harry.tan.serviceImpl.CalcuateStopsByMax;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test03 {

    // test 7
    @Test
    public void testStopsExactly() {

        CalculateInter calculate = new CalcuateStopsByExactly(4);
        CalculateContext context = new CalculateContext(calculate);
        String testData[] = new String[] { "A-C","A-D","A-B","A-E","C-C" };
        for (String data : testData) {
            String d[] = data.split("-");
            int distance = context.calculateBySpot(d[0], d[1]);
            System.out.println(distance);
        }
    }
}
