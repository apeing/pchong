package com.nihao.pachong;

import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;
import java.util.Map;  
import com.nihao.pachong.Pachong;

public class jdbcdemo {
	public static void main(String[] args) throws SQLException {  
	     Pachong app = new Pachong();
	     Dianping obj = new Dianping();
	     //建立数据库连接
	     JdbcUtils jdbcUtils = new JdbcUtils();  
	     jdbcUtils.getConnection(); 
	     obj = app.runcraw("http://www.dianping.com/shop/9038889",3);
	        System.out.println("ok");
	        System.out.println("obj : " + obj.getshopname());   
	        String sql = "insert into ShopInfo(shopphone,shopaddress,shopname,lng,lat,shopinfo,rank) values(?,?,?,?,?,?,?)";  
	        List<Object> params = new ArrayList<Object>();
	        
	        params.add(obj.getShopPhone());  
	        params.add(obj.getShopAddress());
	        params.add(obj.getshopname());
	        params.add(obj.getLng());
	        params.add(obj.getLat());
	        String shopinfostr = "";
	        for(String item : obj.getShopInfo()){
	        	shopinfostr += item.trim();
	        }
	        
	        params.add(shopinfostr);
	        params.add(obj.getRank());
	        System.out.println("shopinfostr : "+shopinfostr);
	        System.out.println("getRank : "+obj.getRank());
	        try {  
	            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);      
	            System.out.println("添加一条记录： "+flag);  
	            if(flag){
	            	
	            }
	        } catch (Exception e) {  
	            // TODO: handle exception  
	            e.printStackTrace();  
	        }finally{  
	            jdbcUtils.releaseConn();  
	        } 
		/**添加数据表记录**/
     /*   JdbcUtils jdbcUtils = new JdbcUtils();  
        jdbcUtils.getConnection();  
          
        //增加一条记录。新增一个用户信息 uername = "jack" , password = "admin"   
        String sql = "insert into userinfo(username,pswd) values(?,?)";  
        List<Object> params = new ArrayList<Object>();  
        params.add("root");  
        params.add("123");  
          
      
        try {  
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);      
            System.out.println("添加一条记录： "+flag);  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }finally{  
            jdbcUtils.releaseConn();  
        }   */
		
		/** 返回多条记录 
	     * @param args 
	     * @throws Exception  
	     */  
	     
        // TODO Auto-generated method stub  
     /*   JdbcUtils jdbcUtils = new JdbcUtils();  
        jdbcUtils.getConnection();    
        String sql = "select * from userinfo";    
          
        try {  
            List<Map<String, Object>> list = jdbcUtils.findMoreResult(sql, null);  
            System.out.println(list);  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }finally{  
            jdbcUtils.releaseConn();  
        }   */  
	  
	  
        // TODO Auto-generated method stub  
 /*       JdbcUtils jdbcUtils = new JdbcUtils();  
        jdbcUtils.getConnection();    
        String sql = "select * from userinfo";    
          
        try {  
            List<UserInfo> list = jdbcUtils.findMoreRefResult(sql, null , UserInfo.class);  
            System.out.println(list);  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }finally{  
            jdbcUtils.releaseConn();  
        }  */


}  


}
