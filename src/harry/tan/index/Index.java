package harry.tan.index;

/**
 * @description test main
 * @author harry tan
 * 
 */
public class Index {

    /**
     * @param args
     */
    public static void main(String[] args) {

        SysResult sys = new SysResult();

        // 1-5
        sys.runByComputeByClearRoute();

        // 6
        sys.runComputeStopsByMax();

        // 7
        sys.runComputeStopsByMaxExactly();

        // 8-9
        sys.runComputeShortestRoute();

        // 10
        sys.runComputeRoutesNumbersByCircular();
    }
}
