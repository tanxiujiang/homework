package harry.tan.serviceImpl;

import harry.tan.serviceInter.CalculateInter;

/**
 * @description build CalculateInter by different instance
 * @author harry tan
 * 
 */
public class CalculateContext {

    private CalculateInter calculateImpl;



    public CalculateContext(CalculateInter calculateImpl) {
        this.calculateImpl = calculateImpl;
    }



    public int calculateByCompletePath(final String completeRoute) {

        return this.calculateImpl.calculate(completeRoute);
    }



    public int calculateBySpot(final String startTown, final String endTown) {
        return this.calculateImpl.calculate(startTown, endTown);
    }

}
