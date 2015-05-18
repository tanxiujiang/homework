package harry.tan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import harry.tan.dao.DBManager;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateLeastStops implements CalculateInter {

    private DBManager    dbManager = null;

    private List<String> routePath = new ArrayList<String>();

    private int          array[][];

    private String       tempPath  = "";

    private int          amount    = 0;

    private int          end       = 0;



    public CalcuateLeastStops() {
        this.dbManager = new DBManager();
    }



    @Override
    public int calculate(String startTown, String endTown) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        int statNode = (startTown.charAt(0) - 16) - 48;

        this.end = (endTown.charAt(0) - 16) - 48;

        // get the amount of letters
        this.amount = dbManager.getLettersAmount();

        dbManager.init(this.amount);
        this.array = dbManager.getArray();

        this.tempPath = statNode + "";

        this.dfs(statNode, 0);

        int result = this.getStops();

        // clear all data

        this.clear();

        return result;

    }



    @Override
    public int calculate(String completeRoute) {

        return 0;
    }



    private void dfs(int pCurrent, int pDis) {

        if ((pCurrent == this.end) && (pDis != 0)) {
            int len = this.tempPath.length();

            StringBuffer routePath = new StringBuffer("");
            for (int j = 0; j < len; j++) {
                char letter = (char) (this.tempPath.charAt(j) + 16);
                routePath.append(letter);
            }

            this.routePath.add(routePath.toString());
        }

        if ((pCurrent == this.end && pDis > 0)) {
            return;
        }

        for (int i = 1; i <= this.amount; i++) {
            if ((this.array[pCurrent][i] != HomeWorkConstant.INFO_PASS)
                    && (this.array[pCurrent][i] != HomeWorkConstant.INFO_ORIGIN)) {
                char ch = (char) (48 + i);

                this.tempPath += ch;
                dfs(i, pDis + this.array[pCurrent][i]);
                this.tempPath = this.tempPath.substring(0, this.tempPath.length() - 1);
            }
        }
    }



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



    private void clear() {
        this.array = null;
        this.tempPath = "";
        this.amount = 0;
        this.end = 0;
        this.routePath = new ArrayList<String>();
    }
}
