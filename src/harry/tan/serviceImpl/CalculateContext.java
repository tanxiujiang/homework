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



    public int calculateByCompletePath(final String pCompleteRoute) {

        return this.calculateImpl.calculate(pCompleteRoute);
    }



    public int calculateBySpot(final String pStartTown, final String pEndTown,final String...pOthers) {
        return this.calculateImpl.calculate(pStartTown, pEndTown,pOthers);
    }

}
