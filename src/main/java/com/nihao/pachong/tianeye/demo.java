package com.nihao.pachong.tianeye;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nihao.pachong.MysqlConnPool;
import com.nihao.pachong.MysqlHelper;

public class demo {
	public static void main(String[] args) throws SQLException, InterruptedException{
		Tianweb app = new Tianweb();
		Connection connection = MysqlConnPool.getInstance().getConnection();
		String url = "https://sh.tianyancha.com/search/os1";
		for(int i = 1 ;i <= 5;i++){
			String tmpurl = "";
			if(i == 1)
				tmpurl = url;
			else
				tmpurl = url + "/p" + i;
				List<Company> list = app.runcraw(tmpurl);
			for(Company item : list){
				ResultSet rs = MysqlHelper.executeQuery(connection, "select id from company where companyname = '" + item.getName() + "'");
				if(!rs.next()){
					System.out.println("公司名称222 : " + item.getName());
					String insertstr = "insert into company(companyname,status,industry,score,boss,zhijing,gszch,zzjgdm,tyxydm,nsrsbh,yyqx,hzsj,rygm,sjzb,djjg,address,jyfw,url,infos,contact)";
					insertstr +=" value('" + item.getName() + "','" + item.getStatus() + "','" + item.getIndustry() + "','" + item.getScore() + "','";
					insertstr +=item.getBoss() + "','" + item.getZhijing() + "','" + item.getGszch() + "','" + item.getZzjgdm() + "','" + item.getTyxydm() + "','" + item.getNsrsbh();
					insertstr +="','" + item.getYyqx() + "','" + item.getHzsj() + "','" + item.getRygm() + "','" + item.getSjzb() + "','" + item.getDjjg() + "','" + item.getAddress() + "','" + item.getJyfw() + "','" +item.getUrl() + "','" + item.getInfos()+ "','" +item.getContact() + "')";
					MysqlHelper.executeUpdate(connection, insertstr);
				}
			}
		}
		connection.close();
	}
}
