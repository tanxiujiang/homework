package harry.tan.test;

import harry.tan.serviceImpl.CalcuateByCircular;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test04 {
    
    @Test
    public void testCalcuateByCircular(){
        CalculateInter calculate = new CalcuateByCircular();
        CalculateContext context = new CalculateContext(calculate);
        System.out.println(context.calculateBySpot("C", "C"));
        System.out.println(context.calculateBySpot("D", "D"));
        System.out.println(context.calculateBySpot("A", "A"));
    }
}
