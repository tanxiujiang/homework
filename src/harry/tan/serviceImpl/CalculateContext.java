package harry.tan.serviceImpl;

import harry.tan.serviceInter.CalculateInter;

/**
 * @description build CalculateInter by different instance
 * @author harry tan
 * 
 */
public class CalculateContext {

    private CalculateInter calculateImpl;



    /**
     * 
     * @param the
     *            class must implement CalculateInter
     */
    public CalculateContext(CalculateInter calculateImpl) {
        this.calculateImpl = calculateImpl;
    }



    /**
     * 
     * <method description>
     * 
     * @param pCompleteRoute
     *            a complete route
     * @return
     */
    public int calculateByCompletePath(final String pCompleteRoute) {

        return this.calculateImpl.calculate(pCompleteRoute);
    }



    /**
     * 
     * <method description>
     * 
     * @param pStartTown
     *            start node
     * @param pEndTown
     *            end node
     * @return
     */
    public int calculateBySpot(final String pStartTown, final String pEndTown) {
        return this.calculateImpl.calculate(pStartTown, pEndTown);
    }

}
