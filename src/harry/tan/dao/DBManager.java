package harry.tan.dao;

import harry.tan.pojo.Node;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @description load proterties and create data structure
 * @author harrytan
 * 
 */
public class DBManager {

    /**
     * init the data,and cache data
     */
    private  Set<Node> nodes;

    /**
     * save the primary data
     */
    private  int       array[][];

    /**
     * remove duplicate
     */
    private List<String>     townContainer = new ArrayList<String>();

    /**
     * singleton(hungry type)
     */
    private static DBManager instance      = new DBManager();



    private DBManager() {
    }



    /**
     * init the data if the nodes empty
     */
    public void init() {

        // execute init data
        if (isReload() || nodes == null || nodes.size() < 1) {
            this.buildStructure();
        }
    }



    /**
     * buildData <method description>
     * 
     * @param amout
     *            show how many the towns
     */
    public void init(final int pAmout) {
        if (array == null) {
            this.buildStructure(pAmout);
        }
    }



    /**
     * compute the amount of letters <method description>
     * 
     * @return
     */
    public int getLettersAmount() {
        this.init();
        return nodes.size();
    }



    /**
     * 
     * <method description> get current only a instance
     * 
     * @return
     */
    public static DBManager getInstance() {
        return instance;
    }



    /**
     * @description determine whether reload the configuration file or not
     * @return
     */
    private boolean isReload() {
        Properties pro = HomeWorkUtil.loadConfigure(HomeWorkConstant.DB_PATH);
        Integer flag = 0;
        if (pro != null) {
            flag = Integer.valueOf(pro.getProperty(HomeWorkConstant.DB_FLAG));
        }

        return flag > 0 ? true : false;
    }



    /**
     * @description load data from configuration
     * @return String[]
     */
    private String[] loadData() {
        Properties pro = HomeWorkUtil.loadConfigure(HomeWorkConstant.DB_PATH);
        String[] nodes = null;
        if (pro != null) {
            String data = pro.getProperty(HomeWorkConstant.DB_DATA);
            nodes = data.split(",");
        }

        return nodes;
    }



    /**
     * @description build the String[] data and add the datas into nodes
     */
    private void buildStructure() {
        nodes = new HashSet<Node>();
        String[] dataStrs = this.loadData();
        for (int i = 0; i < dataStrs.length; i++) {
            String nodeStr = dataStrs[i].trim();
            if (nodeStr.length() != 3) {
                throw new RuntimeException("the data is error");
            }

            Node nodeStart = null;
            Node nodeEnd = null;

            String start = nodeStr.substring(0, 1);
            String end = nodeStr.substring(1, 2);
            Integer distance = Integer.valueOf(nodeStr.substring(2, 3));
            if (!this.townContainer.contains(start)) {
                this.townContainer.add(start);
                nodeStart = new Node(start);
                nodes.add(nodeStart);
            } else {
                for (Node node : nodes) {
                    if (node.getName().equals(start)) {
                        nodeStart = node;
                    }
                }
            }

            if (!this.townContainer.contains(end)) {
                this.townContainer.add(end);
                nodeEnd = new Node(end);
                nodes.add(nodeEnd);
            } else {

                // must use before object
                // nodeEnd = new Node(end);

                for (Node node : nodes) {
                    if (node.getName().equals(end)) {
                        nodeEnd = node;
                    }
                }

                // express nodeEnd is exist,and so must find from townContainer
                nodeStart.getChild().put(nodeEnd, distance);
            }

            // express nodeStart is new and nodeEnd is new
            nodeStart.getChild().put(nodeEnd, distance);
        }
    }



    private void buildStructure(final int pAmout) {
        int amount = pAmout;
        array = new int[amount + 1][amount + 1];
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j <= amount; j++) {
                // express the A,B,C,D,E
                if (i == j) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = -1;
                }
            }
        }

        if (nodes == null || nodes.size() < 1) {
            this.buildStructure();
        }

        for (Node node : nodes) {
            int row = (node.getName().toCharArray()[0] - 16) - 48;
            for (Node child : node.getChild().keySet()) {
                int col = (child.getName().toCharArray()[0] - 16) - 48;
                array[row][col] = node.getChild().get(child);
            }
        }
    }



    public Set<Node> getNodes() {
        return nodes;
    }



    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }



    public int[][] getArray() {
        return array;
    }



    public void setArray(int[][] pArray) {
        array = pArray;
    }
}
