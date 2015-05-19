package harry.tan.index;

import harry.tan.serviceImpl.CalcuateByCircular;
import harry.tan.serviceImpl.CalcuateStops;
import harry.tan.serviceImpl.CalcuateShortestRoute;
import harry.tan.serviceImpl.CalculateByClearRoute;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;

/**
 * 
 * <class description>
 * 
 * @author: harrytan
 * @version: 1.0, May 18, 2015
 */
public class SysResult {
    /**
     * 1-5
     */
    public void runByComputeByClearRoute() {

        // the first algorithm to solve the question 1-5

        // decided to use which class to solve the question
        CalculateContext context = new CalculateContext(new CalculateByClearRoute());

        String[] testData = new String[] { "A-B-C", "A-D", "A-D-C", "A-E-B-C-D", "A-E-D" };

        for (int i = 0; i < testData.length; i++) {
            int result = context.calculateByCompletePath(testData[i]);
            String showResult = result > 0 ? result + "" : HomeWorkConstant.RESULT_INFO;
            System.out.println(HomeWorkConstant.RESULT_OUT + (i + 1) + HomeWorkConstant.RESULT_COLON + showResult);
        }
    }



    /**
     * 
     * <method description> 6-7
     */
    public void runComputeStops() {

        // the first algorithm to solve the question 6-7
        CalculateContext context = new CalculateContext(new CalcuateStops());

        String[] data = new String[] { "C-C", "A-C" };
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split("-");
            int result = context.calculateBySpot(temp[0], temp[1]);
            System.out.println(HomeWorkConstant.RESULT_OUT + (i + 6) + HomeWorkConstant.RESULT_COLON + result);
        }
    }



    /**
     * 
     * <method description> 8-9
     */
    public void runComputeShortestRoute() {
        CalculateContext context = new CalculateContext(new CalcuateShortestRoute());
        String[] data = new String[] { "A-C", "B-B"};
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split("-");
            int result = context.calculateBySpot(temp[0], temp[1]);
            System.out.println(HomeWorkConstant.RESULT_OUT + (i + 8) + HomeWorkConstant.RESULT_COLON + result);
        }
    }
    
    /**
     * 10
    * <method description>
    *
     */
    public void runComputeRoutesNumbersByCircular(){
        CalculateContext context = new CalculateContext(new CalcuateByCircular());
        String[] data = new String[]{"C-C"};
        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split("-");
            int result = context.calculateBySpot(temp[0], temp[1]);
            System.out.println(HomeWorkConstant.RESULT_OUT + (i + 10) + HomeWorkConstant.RESULT_COLON + result);
        }
    }
}
