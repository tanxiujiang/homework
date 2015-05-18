package harry.tan.utils;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description provide a common  tool
 * @author harry tan
 *
 */
public class HomeWorkUtil{
	
	
	/**
	 * @description	 load configure data
	 * @param path   the path info of configure
	 * @return properties
	 */
	public static Properties  loadConfigure(final String path){
		Properties props = new Properties();
		InputStream input = null;
		try {
			input = new BufferedInputStream(HomeWorkUtil.class.getClassLoader().getResourceAsStream(path));
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	/**
	 * @description splite String data into String[] data 
	 * @param dataStr
	 * @param regex
	 * @return
	 */
	public static String[] splitStr(final String dataStr,final String regex){
		if(dataStr == null ){
			throw new RuntimeException("splitStr method is call,but the dataStr is null");
		}
		
		return dataStr.split(regex);
	}
	
	/**
	 * @descritpion colse the source of implements Closeable
	 * @param io	the class 
	 */
	 public static <T extends Closeable>void close(T... io){
	    	for(Closeable temp : io)
			{
				if(null != null)
				{
					try 
					{
						temp.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	    }
	 
	 /**
	  * @descripton check the pStr is empty or not
	  * @param pStr
	  * @return
	  */
	 public static boolean IsEmpty(final String pStr){
		 return pStr == null || pStr.length() == 0;
	 }
	 
	 /**
	  * @descripton check the pStr is not empty or empty
	  * @param pStr
	  * @return
	  */
	 public static boolean IsNotEmpty(final String pStr){
		 return !IsEmpty(pStr);
	 }
}
