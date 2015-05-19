package harry.tan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import harry.tan.dao.DBManager;
import harry.tan.factory.DataFactory;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateByCircular implements CalculateInter {

    private DBManager    dbManager = null;

    /**
     * save all possible route
     */
    private List<String> routePath = new ArrayList<String>();

    /**
     * save the base info
     */
    private int          array[][];

    private int          weightSum = 30;

    /**
     * save every path
     */
    private String       tempPath  = "";

    /**
     * the amount the letters
     */
    private int          amount    = 0;

    private int          statNode  = 0;



    public CalcuateByCircular() {
        this.dbManager = DataFactory.getDbManager();
    }



    @Override
    public int calculate(final String startTown, final String endTown,final String...pOthers) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("the startTown is empty");
        }
        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("the endTown is empty");
        }
        if (!startTown.equals(endTown)) {
            throw new RuntimeException("the startTown is not equal endTown");
        }

        statNode = (startTown.charAt(0) - 16) - 48;

        // get the amount of letters
        this.amount = dbManager.getLettersAmount();

        dbManager.init(this.amount);
        this.array = dbManager.getArray();

        this.tempPath = statNode + "";
        this.dfs(statNode, 0);

        int result = this.routePath.size();
        this.clear();
        return result;
    }



    @Override
    public int calculate(final String completeRoute) {
        return 0;
    }



    /**
     * 
     * <method description>
     * 
     */
    private void dfs(int pCurrent, int pDis) {
        if (pDis >= this.weightSum) {
            return;
        }

        if ((pCurrent == statNode) && (pDis != 0)) {
            int len = this.tempPath.length();

            StringBuffer routePath = new StringBuffer("");
            for (int i = 0; i < len; i++) {
                char ch = (char) (this.tempPath.charAt(i) + 16);
                routePath.append(ch);
                // System.out.print(ch);
            }

            this.routePath.add(routePath.toString());
        }

        for (int i = 1; i <= this.amount; i++) {
            if ((this.array[pCurrent][i] != HomeWorkConstant.INFO_PASS)
                    && (this.array[pCurrent][i] != HomeWorkConstant.INFO_ORIGIN)) {
                if (pDis + this.array[pCurrent][i] > 30) {
                    continue;
                }
                char ch = (char) (48 + i);
                this.tempPath += ch;
                dfs(i, pDis + this.array[pCurrent][i]);
                this.tempPath = this.tempPath.substring(0, this.tempPath.length() - 1);
            }
        }
    }



    private void clear() {
        this.routePath = new ArrayList<String>();
        this.array = null;
        this.tempPath = "";
        this.amount = 0;
    }
}
