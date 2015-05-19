package harry.tan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import harry.tan.dao.DBManager;
import harry.tan.factory.DataFactory;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateLeastStops implements CalculateInter {

    private DBManager    dbManager = null;

    /**
     * provide a container to save all routes
     */
    private List<String> routePath = new ArrayList<String>();

    /**
     * the base data
     */
    private int          array[][];

    /**
     * provide a variable to save every temporary route
     */
    private String       tempPath  = "";

    /**
     * save a temporary weight
     */
    private int          weight    = 0;

    private int          startNode = 0;



    public CalcuateLeastStops() {
        this.dbManager = DataFactory.getDbManager();
    }



    @Override
    public int calculate(String startTown, String endTown) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        // letter turned into digital
        this.startNode = (startTown.charAt(0) - 16) - 48;

        // letter turned into digital
        final int endNode = (endTown.charAt(0) - 16) - 48;

        // get the amount of letters
        final int amount = dbManager.getLettersAmount();

        dbManager.init(amount);

        this.array = dbManager.getArray();

        // init a value
        this.weight = this.array[this.startNode][endNode];

        this.tempPath = this.startNode + HomeWorkConstant.GLOBAL_EMPTY;

        this.dfs(this.startNode, endNode, 0, amount);

        int result = this.getStops();

        // clear all data
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
    private void dfs(final int pCurrent, final int pEnd, final int pDis, final int pNumber) {

        if ((pCurrent == pEnd) && (pDis != 0)) {
            int len = this.tempPath.length();

            StringBuffer routePath = new StringBuffer("");
            for (int j = 0; j < len; j++) {
                char letter = (char) (this.tempPath.charAt(j) + 16);
                routePath.append(letter);
            }

            this.routePath.add(routePath.toString());
        }

        if ((pCurrent == pEnd && pDis > 0)) {
            return;
        }

        for (int i = 1; i <= pNumber; i++) {
            if ((this.array[pCurrent][i] != HomeWorkConstant.INFO_PASS)
                    && (this.array[pCurrent][i] != HomeWorkConstant.INFO_ORIGIN)) {
                if (( this.startNode != pEnd) && (pDis > 0) && (Math.abs(pCurrent - i) == 1) && (this.weight == this.array[pCurrent][i])) {
                    continue;
                }

                this.weight = this.array[pCurrent][i];

                // digital to digital character
                char ch = (char) (48 + i);
                this.tempPath += ch;
                dfs(i, pEnd, pDis + this.array[pCurrent][i], pNumber);
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
        int stopNumber = Integer.MAX_VALUE;
        for (String route : this.routePath) {
            if (route.length() < stopNumber) {
                if (route.charAt(0) == route.charAt(route.length() - 1)) {
                    stopNumber = route.length() - 1;
                } else {
                    stopNumber = route.length();
                }
            }
        }
        return stopNumber;
    }



    /**
     * init data <method description>
     * 
     */
    private void clear() {
        this.array = null;
        this.tempPath = "";
        this.weight = 0;
        this.routePath = new ArrayList<String>();
    }
}
