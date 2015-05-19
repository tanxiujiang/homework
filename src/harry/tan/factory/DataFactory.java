package harry.tan.factory;

import harry.tan.dao.DBManager;

/**
 * 
 * <class description> get data by factory
 * 
 * @author: harrytan
 * @version: 1.0, May 19, 2015
 */
public class DataFactory {
    private static DBManager dbManager;



    public static DBManager getDbManager() {
        if (dbManager == null) {
            dbManager = DBManager.getInstance();
            return dbManager;
        } else {
            return dbManager;
        }
    }
}
