package harry.tan.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @descripton the town + edge
 * @author harry tan
 * 
 */
public class Node {

    /**
     * the name of town
     */
    private String             name;

    /**
     * the route of town,and include weight
     */
    private Map<Node, Integer> child = new HashMap<Node, Integer>();



    public Node() {
    }

    public Node(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }



    public void setName(String pName) {
        name = pName;
    }



    public Map<Node, Integer> getChild() {
        return child;
    }



    public void setChild(Map<Node, Integer> pChild) {
        child = pChild;
    }



    @Override
    public String toString() {
        return "Node [child=" + name + "]";
    }

}
