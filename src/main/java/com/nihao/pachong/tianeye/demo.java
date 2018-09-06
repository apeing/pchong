package com.nihao.pachong.tianeye;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class demo {
	public static void main(String[] args){
		Tianweb app = new Tianweb();
		Connection conn = Jsoup.connect("https://sc.tianyancha.com/search/oe01-s5");
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", "TYCID=4488d7e0707a11e89329f931a4758275; undefined=4488d7e0707a11e89329f931a4758275; ssuid=8147053520; aliyungf_tc=AQAAALVftw7+FAwAPLaLthxarKiOZ1vS; csrfToken=seQzz96c0LJD9ie8JdQDwp-b; _ga=GA1.2.948029875.1536112595; _gid=GA1.2.1254898475.1536112595; RTYCID=e28a7fd8dda5466b90f1eb9de745425c; CT_TYCID=c9fc9998a71e4a25acac73362865822d; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1536112594,1536126067; token=e82b3179cdeb444a9e09446860cc96bd; _utm=ca0bb19eec9342db9cc5d3ba2a36b3ba; tyc-user-info=%257B%2522token%2522%253A%2522eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA%2522%252C%2522integrity%2522%253A%25220%2525%2522%252C%2522state%2522%253A%25220%2522%252C%2522redPoint%2522%253A%25220%2522%252C%2522vipManager%2522%253A%25220%2522%252C%2522vnum%2522%253A%25220%2522%252C%2522monitorUnreadCount%2522%253A%25220%2522%252C%2522onum%2522%253A%25220%2522%252C%2522mobile%2522%253A%252218628257945%2522%257D; auth_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODYyODI1Nzk0NSIsImlhdCI6MTUzNjEzMTIyNywiZXhwIjoxNTUxNjgzMjI3fQ.COqha6k7nlUp2beucer0oRsoU--J_TJ4LKbkA2GBu4z2rVamQjTh-qAz4leg8d1P7ZEBwXH1yRNh9bBrSs09ZA; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1536132083; cloud_token=40bb6c538ca24e34abae8f8a815e7081; cloud_utm=8254858cdf6a4962a853430bb395c964");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        
        try {
			doc = conn.get();
			Elements result = doc.getElementsByClass("result-list");
			for(Element item : result.select("div.search-result-single")){
				//item.select("div.content>div.header>a>text");
				System.out.println("公司名称 : " + item.select("div.content>div.header>a").text());
				System.out.println("公司状态 : " + item.select("div.content>div.header>div").text());
				System.out.println("信用分数 : " + item.select("div.score>span").text().trim());
				System.out.println(item.select("div[class=\"title text-ellipsis\"]").text());
				System.out.println(item.select("div[class=\"title  text-ellipsis\"]").text());
				System.out.println(item.select("a[class=\"name \"]").attr("href").toString());
				List<String> infos = app.runcraw2(item.select("a[class=\"name \"]").attr("href").toString());
				System.out.println("工商注册号:" + infos.get(0));
				System.out.println("组织机构代码:" + infos.get(1));
				System.out.println("统一信用代码:" + infos.get(2));
				System.out.println("公司类型:" + infos.get(3));
				System.out.println("纳税人识别号:" + infos.get(4));
				System.out.println("行业:" + infos.get(5));
				System.out.println("营业期限:" + infos.get(6));
				System.out.println("核准日期:" + infos.get(7));
				System.out.println("纳税人资质:" + infos.get(8));
				System.out.println("人员规模:" + infos.get(9));
				System.out.println("实缴资本:" + infos.get(10));
				System.out.println("登记机关:" + infos.get(11));
				System.out.println("参保人数:" + infos.get(12));
				System.out.println("英文名称:" + infos.get(13));
				System.out.println("注册地址:" + infos.get(14));
				System.out.println("经营范围:" + infos.get(15));
			}
	//		System.out.println("str : " + result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
