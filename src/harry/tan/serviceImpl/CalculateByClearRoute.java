package harry.tan.serviceImpl;

import java.util.Set;

import harry.tan.dao.DBManager;
import harry.tan.factory.DataFactory;
import harry.tan.pojo.Node;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkConstant;
import harry.tan.utils.HomeWorkUtil;

/**
 * @description calculate the path,such as A-B-C
 * @author harry tan
 * 
 */
public class CalculateByClearRoute implements CalculateInter {

    DBManager dbManager = null;



    public CalculateByClearRoute() {
        this.dbManager = DataFactory.getDbManager();
    }



    @Override
    public int calculate(final String startTown, final String endTown,final String...pOthers) {

        return 0;
    }



    @Override
    public int calculate(final String completeRoute) {
        String[] towns = HomeWorkUtil.splitStr(completeRoute, HomeWorkConstant.DATA_LINE);
        if (towns.length < 1) {
            throw new RuntimeException("the input info is empty");
        }

        String startTown = towns[0];

        // load the first node
        Node startNode = null;

        // statistical sum distance
        int distance = 0;

        // init data from configuration file
        dbManager.init();

        Set<Node> nodes = dbManager.getNodes();

        // find the first node
        for (Node node : nodes) {
            if (node.getName().equals(startTown)) {
                startNode = node;
                break;
            }
        }

        // true show the route is ok
        boolean flag = true;

        for (int i = 1; i < towns.length; i++) {

            if (startNode.getChild().size() < 1) {
                flag = false;
                break;
            }

            // count the time enter into the under for
            int count = 0;
            for (Node node : startNode.getChild().keySet()) {
                count++;
                if (node.getName().equals(towns[i])) {

                    distance += startNode.getChild().get(node);

                    // statNode need update
                    startNode = node;
                    break;
                }

                if (count == startNode.getChild().size()) {
                    flag = false;
                }
            }

            // flag is false represent the route is not exist
            if (!flag) {
                break;
            }

        }

        if (!flag) {
            distance = 0;
        }

        return distance;
    }
}
