package com.nihao.pachong.tianeye;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tianweb {
	
	public List<Company> runcraw(String url) throws InterruptedException{
		Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", "TYCID=4488d7e0707a11e89329f931a4758275; undefined=4488d7e0707a11e89329f931a4758275; ssuid=8147053520; aliyungf_tc=AQAAALVftw7+FAwAPLaLthxarKiOZ1vS; csrfToken=seQzz96c0LJD9ie8JdQDwp-b; _ga=GA1.2.948029875.1536112595; _gid=GA1.2.1254898475.1536112595; RTYCID=e28a7fd8dda5466b90f1eb9de745425c; CT_TYCID=c9fc9998a71e4a25acac73362865822d; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1536112594,1536126067; token=e82b3179cdeb444a9e09446860cc96bd; _utm=ca0bb19eec9342db9cc5d3ba2a36b3ba; tyc-user-info=%257B%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA%2522%252C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522mobile%2522%253A%252218628257945%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1536132083; cloud_token=40bb6c538ca24e34abae8f8a815e7081; cloud_utm=8254858cdf6a4962a853430bb395c964");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        List<Company> obj = new ArrayList<Company>();
       
        try {
			doc = conn.get();
			Thread.sleep(5000);
			Elements result = doc.getElementsByClass("result-list");
			for(Element item : result.select("div.search-result-single")){
				Company tmp = new Company();
				//item.select("div.content>div.header>a>text");
		//		System.out.println("公司名称 : " + item.select("div.content>div.header>a").text());
				tmp.setName(item.select("div.content>div.header>a").text());
		//		System.out.println("公司状态 : " + item.select("div.content>div.header>div").text());
				tmp.setStatus(item.select("div.content>div.header>div").text());
		//		System.out.println("信用分数 : " + item.select("div.score>span").text().trim());
				tmp.setScore(item.select("div.score>span").text().trim());
		//		System.out.println(item.select("a[class=\"legalPersonName hover_underline\"]").text());
				tmp.setBoss(item.select("a[class=\"legalPersonName hover_underline\"]").text());
				System.out.println(item.select("div[class=\"title  text-ellipsis\"]").text());
				tmp.setInfos(item.select("div[class=\"title  text-ellipsis\"]").text());
				System.out.println(item.select("a[class=\"name \"]").attr("href").toString());
				tmp.setUrl(item.select("a[class=\"name \"]").attr("href").toString());
				System.out.println(item.select("div[class=\"contact\"]").text());
				tmp.setContact(item.select("div[class=\"contact\"]").text());
				List<String> infos = runcraw2(item.select("a[class=\"name \"]").attr("href").toString());
				Thread.sleep(5000);
		//		System.out.println("工商注册号:" + infos.get(0));
				tmp.setGszch(infos.get(0));
		//		System.out.println("组织机构代码:" + infos.get(1));
				tmp.setZzjgdm(infos.get(1));
		//		System.out.println("统一信用代码:" + infos.get(2));
				tmp.setTyxydm(infos.get(2));
		//		System.out.println("公司类型:" + infos.get(3));
				tmp.setIndustry(infos.get(3));
		//		System.out.println("纳税人识别号:" + infos.get(4));
				tmp.setNsrsbh(infos.get(4));
		//		System.out.println("行业:" + infos.get(5));
				tmp.setIndustry(infos.get(5));
		//		System.out.println("营业期限:" + infos.get(6));
				tmp.setYyqx(infos.get(6));
		//		System.out.println("核准日期:" + infos.get(7));
				tmp.setHzsj(infos.get(7));
		//		System.out.println("纳税人资质:" + infos.get(8));
				tmp.setNsrzz(infos.get(8));
		//		System.out.println("人员规模:" + infos.get(9));
				tmp.setRygm(infos.get(9));
		//		System.out.println("实缴资本:" + infos.get(10));
				tmp.setSjzb(infos.get(10));
		//		System.out.println("登记机关:" + infos.get(11));
				tmp.setDjjg(infos.get(11));
		//		System.out.println("参保人数:" + infos.get(12));
				tmp.setCbrs(infos.get(12));
		//		System.out.println("英文名称:" + infos.get(13));
				tmp.setYwmc(infos.get(13));
		//		System.out.println("注册地址:" + infos.get(14));
				tmp.setAddress(infos.get(14));
		//		System.out.println("经营范围:" + infos.get(15));
				tmp.setJyfw(infos.get(15));
				obj.add(tmp);
			}
		
	//		System.out.println("str : " + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	
	public List<String> runcraw2(String url){
		Connection conn = Jsoup.connect(url);
		conn.header("Cookie", "TYCID=4488d7e0707a11e89329f931a4758275; undefined=4488d7e0707a11e89329f931a4758275; ssuid=8147053520; aliyungf_tc=AQAAALVftw7+FAwAPLaLthxarKiOZ1vS; csrfToken=seQzz96c0LJD9ie8JdQDwp-b; _ga=GA1.2.948029875.1536112595; _gid=GA1.2.1254898475.1536112595; RTYCID=e28a7fd8dda5466b90f1eb9de745425c; CT_TYCID=c9fc9998a71e4a25acac73362865822d; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1536112594,1536126067; token=e82b3179cdeb444a9e09446860cc96bd; _utm=ca0bb19eec9342db9cc5d3ba2a36b3ba; tyc-user-info=%257B%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA%2522%252C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522mobile%2522%253A%252218628257945%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1536132083; cloud_token=40bb6c538ca24e34abae8f8a815e7081; cloud_utm=8254858cdf6a4962a853430bb395c964");
		conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        List<String> infos = new ArrayList<String>();//
        try {
			doc = conn.get();
			
			//工商信息
			Element baseInfo = doc.getElementById("_container_baseInfo");
			Elements trs = baseInfo.select("table[class=\"table -striped-col -border-top-none\"]>tbody>tr");
			
			for(int i = 0;i < trs.size();i++){
				Elements tds = trs.get(i).select("td");
	            for (int j=0; j<tds.size(); j++){
	                String txt = tds.get(j).text();
	             //   System.out.print("td : " + txt);
	                if(j%2 == 1)
	                	infos.add(txt);
	            }
			}
			Elements trs2 = baseInfo.select("table[class=\"table\"]>tbody>tr");
			
		//	System.out.println("doc : " + doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;
	}
	
	public List<Company> runfirst(String url) throws InterruptedException{
		Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", "TYCID=4488d7e0707a11e89329f931a4758275; undefined=4488d7e0707a11e89329f931a4758275; ssuid=8147053520; aliyungf_tc=AQAAALVftw7+FAwAPLaLthxarKiOZ1vS; csrfToken=seQzz96c0LJD9ie8JdQDwp-b; _ga=GA1.2.948029875.1536112595; _gid=GA1.2.1254898475.1536112595; RTYCID=e28a7fd8dda5466b90f1eb9de745425c; CT_TYCID=c9fc9998a71e4a25acac73362865822d; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1536112594,1536126067; token=e82b3179cdeb444a9e09446860cc96bd; _utm=ca0bb19eec9342db9cc5d3ba2a36b3ba; tyc-user-info=%257B%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA%2522%252C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522mobile%2522%253A%252218628257945%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1536132083; cloud_token=40bb6c538ca24e34abae8f8a815e7081; cloud_utm=8254858cdf6a4962a853430bb395c965"); //4
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        List<Company> obj = new ArrayList<Company>();
       
        try {
			doc = conn.get();
			Thread.sleep(5000);
			Elements result = doc.getElementsByClass("result-list");
			for(Element item : result.select("div.search-result-single")){
				Company tmp = new Company();
				//item.select("div.content>div.header>a>text");
		//		System.out.println("公司名称 : " + item.select("div.content>div.header>a").text());
				tmp.setName(item.select("div.content>div.header>a").text());
		//		System.out.println("公司状态 : " + item.select("div.content>div.header>div").text());
				tmp.setStatus(item.select("div.content>div.header>div").text());
		//		System.out.println("信用分数 : " + item.select("div.score>span").text().trim());
				tmp.setScore(item.select("div.score>span").text().trim());
		//		System.out.println(item.select("a[class=\"legalPersonName hover_underline\"]").text());
				tmp.setBoss(item.select("a[class=\"legalPersonName hover_underline\"]").text());
		//		System.out.println(item.select("div[class=\"title  text-ellipsis\"]").text());
				tmp.setInfos(item.select("div[class=\"title  text-ellipsis\"]").text());
		//		System.out.println(item.select("a[class=\"name \"]").attr("href").toString());
				tmp.setUrl(item.select("a[class=\"name \"]").attr("href").toString());
		//		System.out.println(item.select("div[class=\"contact\"]").text());
				tmp.setContact(item.select("div[class=\"contact\"]").text());
				
				obj.add(tmp);
			}
		
	//		System.out.println("str : " + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
}
