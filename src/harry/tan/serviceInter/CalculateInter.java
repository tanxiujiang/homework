package harry.tan.serviceInter;

/**
 * @description the definition of computing interface
 * @author harry tan
 * 
 */
public interface CalculateInter {

    /**
     * 
     * <method description> deal the data such A C or A B or
     * 
     * @param startTown
     * @param endTown
     * @return
     */
    public int calculate(final String pStartTown, final String pEndTown);



    /**
     * 
     * <method description> deal the data such A-B-C or A-D-B
     * 
     * @param completeRoute
     * @return
     */
    public int calculate(final String pCompleteRoute);
}
