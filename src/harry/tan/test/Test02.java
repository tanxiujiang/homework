package harry.tan.test;

import harry.tan.serviceImpl.CalcuateStops;
import harry.tan.serviceImpl.CalculateContext;
import harry.tan.serviceInter.CalculateInter;

import org.junit.Test;

public class Test02 {

    @Test
    public void testStops() {
        CalculateInter calculate = new CalcuateStops();
        CalculateContext context = new CalculateContext(calculate);
//        System.out.println(context.calculateBySpot("C", "C","<","4"));
        System.out.println(context.calculateBySpot("A", "C","=","4"));
//        System.out.println(context.calculateBySpot("A", "C"));
//        System.out.println(context.calculateBySpot("A", "E"));
//        System.out.println(context.calculateBySpot("A", "D"));
    }
}
