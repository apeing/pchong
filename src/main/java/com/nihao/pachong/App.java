package com.nihao.pachong;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Hello world!
 *
 */
public class App 
{
//	int index = 1;//解析层次
	List<String> info_urls = new ArrayList<String>();//爬取的详情的url集合
	List<String> info_urls2 = new ArrayList<String>();//爬取商户的url集合
	
	public Dianping runcraw(String urlstr,int index){
		Dianping obj = new Dianping();
		Connection conn = Jsoup.connect(urlstr);
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", "_lxsdk_cuid=164f9acba13c8-0d814121742ee8-6f16107f-1fa400-164f9acba13c8; _lxsdk=164f9acba13c8-0d814121742ee8-6f16107f-1fa400-164f9acba13c8; _hc.v=2f9d8e40-7d78-3cec-428c-c73ff7951921.1533197204; __mta=46071579.1533197962954.1533197962954.1533198217226.2; _tr.u=szUIdZ0APn0Wxxvk; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1533197796,1533518649; cityid=1; citypinyin=shanghai; cityname=5LiK5rW3; m_flash2=1; aburl=1; cy=8; cye=chengdu; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; Hm_lvt_4c4fc10949f0d691f3a2cc4ca5065397=1533198412,1533263864,1533518743,1533623158; s_ViewType=10; _lxsdk_s=16517257503-fcb-74e-c3a%7C%7C8");
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        try {
            doc = conn.get();
         //   System.out.println("str : " + doc);
            Elements elements1 = doc.getElementsByClass("shop-name");
            Elements shopname = elements1.select("h1");
            Elements shopaddress = doc.select("div.address");
            Elements shopphone = doc.getElementsByClass("item J-phone-hide");
            Elements shopinfo = doc.getElementsByClass("mod shop-info");
            Elements brief = shopinfo.select("ul.con>li");
            ArrayList<String> tmpinfo = new ArrayList<String>();
            ArrayList<Course> shopcourses = new ArrayList<Course>();
            Elements shopcourses1 = doc.getElementsByClass("item notag");
        //    System.out.println("shopcourses : " + shopcourses);
            for(Element item : shopcourses1){
            	Course tmpcourse = new Course();
            	String img = item.select("img").attr("lazy-src");
            	tmpcourse.setTitle(item.select("p.title").text());
            	tmpcourse.setImg(img);
            	tmpcourse.setDesc(item.select("p.desc").text());
            	tmpcourse.setCur(item.select("div.price>span.cur").text());
            	tmpcourse.setAddress("http://www.dianping.com" + item.select("a.block-link").attr("href"));
            	shopcourses.add(tmpcourse);
//            	System.out.println("tmpcourse : " + tmpcourse.getCur() + tmpcourse.getTitle() + tmpcourse.getDesc() + tmpcourse.getAddress());
            }
            
            for(Element item : brief){
          //  	 System.out.println("item : " + item.text());
          //  	 System.out.println("itemspan : " + item.select("span").text());
            	 tmpinfo.add(item.text());
            }
            
            obj.setshopname(shopname.text());
            obj.setShopAddress(shopaddress.text());
            obj.setShopPhone(shopphone.attr("data-phone"));
            obj.setShopInfo(tmpinfo);
            obj.setShopCourses(shopcourses);
//            System.out.println("shopname : " + obj.getshopname());
//            System.out.println("shopaddress : " + obj.getShopAddress());
//            System.out.println("shopphone : " + obj.getShopPhone());
//            System.out.println("shopinfo : " + obj.getShopInfo());
      //      return obj;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return obj;
	}
    /**
     * 爬取网页
     * 返回符合规则的新url
     * @param urlStr
     * @return
     */
    public String crawler(String urlStr,int index){
        String str = "";  //用于接收爬取到的网页
        String newUrl = "";  //用于接收返回的符合规则的新url
        URL url;
        int responsecode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String line;
        try{
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url=new URL(urlStr);
            //打开URL
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
            //获取服务器响应代码
            responsecode=urlConnection.getResponseCode();
            if(responsecode==200){
                //得到输入流，即获得了网页的内容
            	System.out.println("return 200 : " + urlConnection.getInputStream());
                reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((line=reader.readLine())!=null){
                    str += line;
                }
   //             System.out.println("str : " + str);
                //解析html
                if(index == 1){
                    newUrl = analysis(str);
                }if(index == 2){
                	newUrl = analysis2(str);
                }else{
                    newUrl = analysis3(str);
                }

            }else{
                System.out.println("获取不到网页的源码，服务器响应代码为："+responsecode);
            }
        }catch(Exception e){
            System.out.println("获取不到网页的源码,出现异常："+e);
        }
        return newUrl;

    }
    /**
     * ================================================================================================================
     * 解析html
     * 返回新url
     */
    public String analysis(String str){

        //假设我们获取的HTML的字符内容如下
        String html = str;

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
        Elements elements1 = doc.getElementsByClass("edu-nav J_edu-nav");
        Elements doc1 = elements1.select("a.name");
//        System.out.println("elements1 : " + doc1);
        for ( Element element : doc1 ){
        	info_urls.add("http://www.dianping.com" + element.attr("href"));
        }
        return "ok";
    }
    /**
     * 解析第二层html
     *
     */
    public String analysis2(String str){

        //假设我们获取的HTML的字符内容如下
        String html = str;

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
        Elements elements1 = doc.getElementsByClass("tit");
        Elements doc1 = elements1.select("a[data-hippo-type=\"shop\"]");
     //   System.out.println("analysis2 : " + doc1);
        for ( Element element : doc1 ){
        	info_urls2.add(element.attr("href"));
        }
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容

    //    System.out.println("doc1 : " + info_urls);
        return "ok";
    }  
    
    /**
     * 解析第三层html
     *
     */
    public String analysis3(String str){

        //假设我们获取的HTML的字符内容如下
        String html = str;

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
//        Elements elements1 = doc.getElementsByClass("shop-name");
//        Elements shopname = elements1.select("h1");
        System.out.println("shopname : " + doc);
      
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容

//        System.out.println("doc1 : " + info_urls);
        return "OK";
    }      
    /**
     * 解析第三层html
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     *
     */
    public String writeobj(String str) throws IOException{
    	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\2.txt",true),"UTF-8"));
        out.write(str);
        out.write("\r\n");
        out.close();
    	return "ok";
    }
  /*  public static void main( String[] args ) throws IOException{
    	//写入相应的文件
    	App app = new App();
    	app.writeobj("hello world!\r\n");
        System.out.println("ok");
    }*/
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	App app = new App();
    	String urlnew = app.crawler("http://www.dianping.com/chengdu/education",1);//http://www.dianping.com/chengdu/ch75/g2872
//    	String urlnew = app.crawler("http://www.dianping.com/chengdu/ch75/g2872",2);
//    	String urlnew = app.runcraw("http://www.dianping.com/shop/100980726",3);
    	
    	System.out.println("urlnew : " + urlnew);
 //   	app.crawler(app.info_urls.get(0),2);
    	for(String item : app.info_urls){
    	//	System.out.println(item);
    		String urlnew2 = app.crawler(item,2);
    //		System.out.println("urlnew2 : " + urlnew2);
    	}
    	System.out.println("info_urls2.size : " + app.info_urls2.size());
    	for(String item : app.info_urls2){
    		Dianping dobj = app.runcraw(item,3);
        	String dstr = "shopname:" + dobj.getshopname() + "\r\n";
        	dstr += "shopaddress:" + dobj.getShopAddress() + "\r\n";
        	dstr += "shopphone:" + dobj.getShopPhone() + "\r\n";
        	dstr += "shopinfo:";
        	for(String item1 : dobj.getShopInfo()){
        		dstr += item1;
        	}
        	dstr += "\r\nshopcourses:";
        	for(Course item2 : dobj.getShopCourses()){
        		dstr += " 价格:" + item2.getCur();
        		dstr += " 课程名称:" + item2.getTitle();
        		dstr += " 描述:" + item2.getDesc();
        		dstr += " 封面图片" + item2.getImg();
        		dstr += " 地址:" + item2.getAddress() + "\r\n";
        	}
        	app.writeobj(dstr);
        	System.out.println("say hello to world at " + new Date());
        	Thread.sleep(5000);
    	}
    	
    	
    	System.out.println("ok");
//    	for(String item : app.info_urls2){
//    		String urlnew = app.runcraw("http://www.dianping.com/shop/100980726",3);
//    	}
//    	app.crawler("http://www.dianping.com/chengdu/ch25/g136",1);
        
    } 
    
}
