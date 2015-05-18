package harry.tan.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import harry.tan.dao.DBManager;
import harry.tan.pojo.Node;
import harry.tan.serviceInter.CalculateInter;
import harry.tan.utils.HomeWorkUtil;

public class CalcuateShortestRoute implements CalculateInter {

    /**
     * save the node of visiting
     */
    private Set<Node>            openNode  = new HashSet<Node>();

    /**
     * save the node of visited
     */
    private Set<Node>            closeNode = new HashSet<Node>();

    /**
     * to save the shortest distance of startNode to everyNode
     */
    private Map<String, Integer> path      = new HashMap<String, Integer>();

    /**
     * to save the shortest path of startNode to everyNode
     */
    private Map<String, String>  pathInfo  = new HashMap<String, String>();

    DBManager                    dbManager = null;



    public CalcuateShortestRoute() {
        dbManager = new DBManager();
    }



    @Override
    public int calculate(final String startTown, final String endTown) {
        if (HomeWorkUtil.IsEmpty(startTown)) {
            throw new RuntimeException("startTown is empty");
        }

        if (HomeWorkUtil.IsEmpty(endTown)) {
            throw new RuntimeException("endTown is empty");
        }

        dbManager.init();

        Set<Node> nodes = dbManager.getNodes();
        this.openNode.addAll(nodes);

        Node startNode = null;
        Node endNode = null;

        for (Node node : nodes) {
            if (node.getName().equals(startTown)) {
                startNode = node;
            }

            if (node.getName().equals(endTown)) {
                endNode = node;
            }

            if (startNode != null && endNode != null) {
                break;
            }
        }

        if (startNode == null) {
            throw new RuntimeException("the startTown is not exist");
        }

        if (endNode == null) {
            throw new RuntimeException("the endTown is not exist");
        }

        this.initPathInfo(startNode);
        this.computeCore(startNode);

        int result = this.path.get(endTown) == null ? 0 : this.path.get(endTown);
        this.clear();
        return result;
    }



    @Override
    public int calculate(String completeRoute) {

        return 0;
    }



    /**
     * 
     * @param node
     *            startNode
     * @return relate node where the distance is shortest
     */
    private Node getChildShortestNode(Node node) {
        Node resut = null;
        Map<Node, Integer> childs = node.getChild();
        Integer temp = Integer.MAX_VALUE;
        for (Node child : childs.keySet()) {
            if (openNode.contains(child)) {
                int distance = childs.get(child);
                if (distance < temp) {
                    temp = distance;
                    resut = child;
                }
            }
        }

        return resut;
    }



    /**
     * @description core of the algorithm
     * @param start
     *            startNode
     */
    private void computeCore(Node start) {
        Node childNode = this.getChildShortestNode(start);
        if (childNode == null) {
            return;
        }

        this.closeNode.add(childNode);
        this.openNode.remove(childNode);
        Map<Node, Integer> childs = childNode.getChild();
        for (Node child : childs.keySet()) {
            if (this.openNode.contains(child)) {
                Integer distanceTemp = this.path.get(childNode.getName()) + childs.get(child);

                if (this.path.get(child.getName()) == null) {
                    this.path.put(child.getName(), distanceTemp);
                    this.pathInfo.put(child.getName(), start.getName() + "-" + child.getName());
                }

                if (this.path.get(child.getName()) > distanceTemp) {
                    this.path.put(child.getName(), distanceTemp);
                    this.pathInfo.put(child.getName(), this.pathInfo.get(childNode.getName()) + "-" + child.getName());
                }
            }
        }

        computeCore(start);
        computeCore(childNode);
    }



    private void initPathInfo(Node startNode) {
        for (Node node : startNode.getChild().keySet()) {
            path.put(node.getName(), startNode.getChild().get(node));
            pathInfo.put(node.getName(), startNode.getName() + "-" + node.getName());
        }
    }



    private void clear() {
        this.openNode = new HashSet<Node>();
        this.closeNode = new HashSet<Node>();
        this.path = new HashMap<String, Integer>();
        this.pathInfo = new HashMap<String, String>();
    }
}
