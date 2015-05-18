package harry.tan.test;

import harry.tan.serviceImpl.CalculateByClearRoute;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;

import org.junit.Test;

public class Test01 {

    @Test
    public void testCompletePath() {

        // implement class
        CalculateInter calculate = new CalculateByClearRoute();

        CalculateContext context = new CalculateContext(calculate);
        String[] testData = new String[] { "A-E", "A-C", "C-D-C", "A-B-C-D-E-B-C" };
        for (String path : testData) {
            int distance = context.calculateByCompletePath(path);
            String result = distance > 0 ? distance + "" : HomeWorkConstant.RESULT_INFO;
            System.out.println(result);
        }
    }
}
