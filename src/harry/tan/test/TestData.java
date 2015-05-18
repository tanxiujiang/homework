package harry.tan.test;

import harry.tan.dao.DBManager;
import harry.tan.pojo.Node;

import java.util.Set;

import org.junit.Test;

public class TestData {
    
    /**
     * obj
    * <method description>
    *
     */
    @Test
    public void buildStructureObj(){
        DBManager dbManager = new DBManager();
        dbManager.init();
        Set<Node> nodes = dbManager.getNodes();
        for(Node node:nodes){
                System.out.println(node.getName()+":"+node.getChild().toString());
        }
    }
    
    /**
     * test array
    * <method description>
    *
     */
    @Test
    public void buildStructureArray(){
        DBManager dbManager = new DBManager();
        dbManager.init(5);
        int[][]  data = dbManager.getArray();
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                System.out.print(data[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        
    } 
}
