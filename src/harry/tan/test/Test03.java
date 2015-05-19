package harry.tan.test;

import harry.tan.serviceImpl.CalcuateShortestRoute;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test03 {
    
    @Test
    public void testShortestDistance(){
        CalculateInter calculate = new CalcuateShortestRoute();
        CalculateContext context = new CalculateContext(calculate);
        String testData[] = new String[]{"A-C","A-A","C-C","D-D"};
        for(String data:testData){
            String d[] = data.split("-");
            int distance = context.calculateBySpot(d[0], d[1]);
            System.out.println(distance);
        }
        
    }
    
}
