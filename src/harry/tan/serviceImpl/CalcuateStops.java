package harry.tan.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.DynAnyPackage.Invalid;

import harry.tan.dao.DBManager;
import harry.tan.factory.DataFactory;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateStops implements CalculateInter {

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

    private int matrixSize = 0;
    
    private int operator = 0;
    
    private int stops=0;

    public CalcuateStops() {
        this.dbManager = DataFactory.getDbManager();
    }



    @Override
    public int calculate(final String startTown, final String endTown,final String...pOthers) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("the startTown is empty");
        }

        if(pOthers == null || pOthers.length < 2){
        	throw new RuntimeException("the extra is error");
        }
        
        // letter turned into digital
        final int startNode = (startTown.charAt(0) - 16) - 48;

        // letter turned into digital
        final int endNode = (endTown.charAt(0) - 16) - 48;

        this.operator = (int)(pOthers[0].charAt(0));
        
        this.stops = Integer.valueOf(pOthers[1]);
        
        // get the amount of letters
        this.matrixSize = dbManager.getLettersAmount();

        dbManager.init(this.matrixSize);

        this.array = dbManager.getArray();

        this.tempPath = startNode + HomeWorkConstant.GLOBAL_EMPTY;

        this.dfs(startNode, endNode, this.tempPath);

        int result = this.getStops();
        
        System.out.println(this.routePath);
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
    private void dfs(final int pCurrent, final int pEnd, String pTemp) {
    	 
//    	if(pTemp.length() > 4){
    	if(pTemp.length() > 5){
    		return;
    	}
//    	 if ((pCurrent == pEnd) && (pTemp.length() != 1)) {
    	if ((pCurrent == pEnd) && (pTemp.length() == 5)) {
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
            	pTemp+=i;
//               if(pTemp.length() > 4){
            	if(pTemp.length() > 5){
            	   continue;
               }
                dfs(i, pEnd, pTemp);
                
                pTemp = pTemp.substring(0, pTemp.length() - 1);
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
}
