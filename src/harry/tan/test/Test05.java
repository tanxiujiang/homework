package harry.tan.test;

import harry.tan.serviceImpl.CalcuateRoutesNumbers;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test05 {

    @Test
    public void testCalcuateByCircular() {
        CalculateInter calculate = new CalcuateRoutesNumbers();
        CalculateContext context = new CalculateContext(calculate);
        String testData[] = new String[] { "C-C", "D-D", "A-A" };
        for (String data : testData) {
            String[] d = data.split("-");
            int result = context.calculateBySpot(d[0], d[1]);
            System.out.println(result);
        }
    }
}
