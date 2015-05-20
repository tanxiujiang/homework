package harry.tan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import harry.tan.dao.DBManager;
import harry.tan.factory.DataFactory;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateStopsByMax implements CalculateInter {

    private DBManager    dbManager  = null;

    /**
     * provide a container to save all routes
     */
    private List<String> routePath  = new ArrayList<String>();

    /**
     * the base data
     */
    private int          array[][];

    /**
     * provide a variable to save every temporary route
     */
    private String       tempPath   = "";

    private int          matrixSize = 0;

    /**
     * the maximum of stops
     */
    private int          maximum    = 0;



    public CalcuateStopsByMax() {
        this.dbManager = DataFactory.getDbManager();
    }



    public CalcuateStopsByMax(int pMaximum) {
        this();
        this.maximum = pMaximum + 1;
    }



    @Override
    public int calculate(final String startTown, final String endTown) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        // letter turned into digital
        final int startNode = (startTown.charAt(0) - 16) - 48;

        // letter turned into digital
        final int endNode = (endTown.charAt(0) - 16) - 48;

        // get the amount of letters
        this.matrixSize = dbManager.getLettersAmount();

        dbManager.init(this.matrixSize);

        this.array = dbManager.getArray();

        this.tempPath = startNode + HomeWorkConstant.GLOBAL_EMPTY;

        this.dfs(startNode, endNode, this.tempPath);

        int result = this.getStops();

        // Just debug,or delete the print info
        // System.out.println(this.getRoutePath());

        // clear all data for reuse the same class
        this.clear();

        return result;

    }



    @Override
    public int calculate(String completeRoute) {

        return 0;
    }



    /**
     * use Depth-First-Search to find every node <method description>
     * 
     * @param pCurrent
     *            current node
     * @param pEnd
     *            the end node
     * @param pDis
     *            the distance
     * @param pNumber
     *            the size of the matrix(array[][])
     */
    private void dfs(final int pCurrent, final int pEnd, final String pTemp) {

        if (pTemp.length() > this.maximum) {
            return;
        }
        if ((pCurrent == pEnd) && (pTemp.length() != 1)) {
            int len = pTemp.length();
            StringBuffer routePath = new StringBuffer("");
            for (int j = 0; j < len; j++) {
                char letter = (char) (pTemp.charAt(j) + 16);
                routePath.append(letter);
            }

            this.routePath.add(routePath.toString());
        }

        for (int i = 1; i <= matrixSize; i++) {
            if ((this.array[pCurrent][i] != HomeWorkConstant.INFO_PASS)
                    && (this.array[pCurrent][i] != HomeWorkConstant.INFO_ORIGIN)) {
                this.tempPath += i;
                if (pTemp.length() > this.maximum) {
                    continue;
                }
                dfs(i, pEnd, pTemp + i);

                this.tempPath = this.tempPath.substring(0, this.tempPath.length() - 1);
            }
        }
    }



    /**
     * get the least node from routePath <method description>
     * 
     * @return
     */
    private int getStops() {
        return this.routePath.size();
    }



    /**
     * init data <method description>
     * 
     */
    private void clear() {
        this.array = null;
        this.tempPath = "";
        this.routePath = new ArrayList<String>();
    }



    public List<String> getRoutePath() {
        return routePath;
    }



    public void setRoutePath(List<String> pRoutePath) {
        routePath = pRoutePath;
    }
}
