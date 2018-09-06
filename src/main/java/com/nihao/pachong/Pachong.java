package com.nihao.pachong;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nihao.pachong.model.Comment;
import com.nihao.pachong.model.Shopmedia;
import com.nihao.pachong.model.Shoppicture;
import com.nihao.pachong.model.Teacher;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pachong {
	List<String> info_urls = new ArrayList<String>();//爬取的详情的url集合
	List<String> info_urls2 = new ArrayList<String>();//爬取商户的url集合
	private String cookies = "_lxsdk_cuid=164f9acba13c8-0d814121742ee8-6f16107f-1fa400-164f9acba13c8; _lxsdk=164f9acba13c8-0d814121742ee8-6f16107f-1fa400-164f9acba13c8; _hc.v=2f9d8e40-7d78-3cec-428c-c73ff7951921.1533197204; __mta=46071579.1533197962954.1533197962954.1533198217226.2; _tr.u=szUIdZ0APn0Wxxvk; cityid=1; citypinyin=shanghai; cityname=5LiK5rW3; aburl=1; __utma=1.1738877636.1534238653.1534238653.1534238653.1; __utmz=1.1534238653.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); cy=8; cye=chengdu; s_ViewType=10; __utma=205923334.611835561.1534382841.1534382841.1534382841.1; __utmz=205923334.1534382841.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); looyu_id=c9f67c91afae8acb262c64411ff9a6b3f8_51868%3A1; Hm_lvt_dbeeb675516927da776beeb1d9802bd4=1533197796,1533518649,1533778098,1534471239; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; Hm_lvt_4c4fc10949f0d691f3a2cc4ca5065397=1535531020,1535593033,1535943039,1536025375; Hm_lpvt_4c4fc10949f0d691f3a2cc4ca5065397=1536025375; _lxsdk_s=165a31c819f-2ef-554-5c2%7C%7C25";
//	List<Map<String, String>> mapones = new ArrayList<Map<String, String>>();
	//爬取课程网页信息
	public com.nihao.pachong.bean.Course runcourse(String urlstr){
		com.nihao.pachong.bean.Course obj = new com.nihao.pachong.bean.Course();
		Connection conn = Jsoup.connect(urlstr);
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", cookies);
        conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        Document doc = null;
        try{
        	doc = conn.get();
        	//System.out.println("str : " + doc);
        	Element info = doc.getElementById("courseSummary");
        	Elements courseinfo = info.select("ul>li>div.item");
        	Map<String, String> infos = new HashMap<String, String>();
        	for(Element item : courseinfo){
        		String title = item.select("span[class=\"title\"]").text();
        		String infotext = item.select("span[class=\"info\"]").text();
        		infos.put(title,infotext);
        	}
        	obj.setInfos(infos);
        	System.out.println("info : " + info);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		return obj;
	}
	//爬取商户网页信息
	public Dianping runcraw(String urlstr,int index){
		Dianping obj = new Dianping();
		Connection conn = Jsoup.connect(urlstr);
        // 修改http包中的header,伪装成浏览器进行抓取
		conn.header("Cookie", cookies);
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
            Element banner = doc.getElementById("basicInfoPic");
            //banner图片
            Elements bannerpic = banner.select("img");
            System.out.println("bannerpic : " + bannerpic.attr("src").toString());
            obj.setBanner(bannerpic.attr("src").toString());
            //爬取经纬度
            Elements shopjw = doc.select("script");
            for(Element itemstr : shopjw){
            //	System.out.println(itemstr);
            	if(itemstr.html().contains("var gid=")){
            		String jwstr = itemstr.html().toString();
              //      System.out.println("shopjw: " + jwstr);
                    int li = jwstr.indexOf("lng:");
                    String[] arrstr = jwstr.substring(li+4).split(",");
                    obj.setLng(arrstr[0]);
                    int wi = arrstr[1].indexOf("}");
                    String lat = arrstr[1].substring(4,wi);
                    obj.setLat(lat);
              //      System.out.println("shopjw.j,w : " + arrstr[0] +" " + lat);
                    break;
            	}
            }            
            //教师风采
            ArrayList<Teacher> shoptheachers = new ArrayList<Teacher>();    
            Elements eteachers = doc.getElementsByClass("mod teachers J_teachers");
       //     System.out.println("eteachers : " + eteachers);
            Elements teachers = eteachers.select("ul>li");
            for(Element item : teachers){
            	Teacher teacherobj = new Teacher();
            	teacherobj.setName(item.select("div>div.name").text());
            	teacherobj.setImg(item.select("div>img").attr("lazy-src"));
            	teacherobj.setYear(item.select("div>div.intro>span.year").text());
            	teacherobj.setType(item.select("div>div.intro>span.type").text());
            	teacherobj.setDetail(item.select("div>div.intro>span.type").text());
            	shoptheachers.add(teacherobj);
            }
            //分校信息
            Elements nodeschool = doc.getElementsByClass("shop-branchs J_shop-branchs J_shop-branchs Hide");
            String branch = "";
            if(nodeschool.isEmpty()){
            
            }else{
            	Elements anodes = nodeschool.select("a.item");            	
            	for(Element item : anodes){
            		branch += item.select("h3.name").text().trim() + ":" + item.select("p.address").text().trim() + ";";
            	}
            }
            System.out.println("branch : " + branch);
            obj.setBranch(branch);
            //官方图片
            ArrayList<Shoppicture> shoppictures = new ArrayList<Shoppicture>();
            Elements epics = doc.getElementsByClass("mod pics J_pics");
            Elements pics = epics.select("ul>li");
            for(Element item : pics){
            	Shoppicture picobj = new Shoppicture();
      //      	System.out.println("item.name : " + item.select("div>img").attr("lazy-src"));
            	picobj.setImg(item.select("div>img").attr("lazy-src"));
            	shoppictures.add(picobj);
            }
            // 官方课程
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
            // 官方视频
            ArrayList<Shopmedia> shopmedias = new ArrayList<Shopmedia>();
            Elements emedias = doc.getElementsByClass("videolist").select("ul>li");
            for(Element item : emedias){
            	Shopmedia mediaobj = new Shopmedia();
            	String url = item.select("div.item").attr("data-video");
            	String title = item.select("div.item>div.title").text();
     //       	System.out.println("item-media : " + url + " " + title);
            	mediaobj.setUrl(url);
            	mediaobj.setTitle(title);
            	shopmedias.add(mediaobj);
            }
            // 商户简介
            for(Element item : brief){
          //  	 System.out.println("item : " + item.text());
          //  	 System.out.println("itemspan : " + item.select("span").text());
            	 tmpinfo.add(item.text());
            }
            //用户点评
            ArrayList<Comment> shopcomments = new ArrayList<Comment>();
            Elements eranks = doc.getElementsByClass("comment-star");
   //         System.out.println("comment-star : " + eranks.select("em").text());
            Elements ecomments = doc.getElementsByClass("content othercontent J_content");
            for(Element item : ecomments){
            	Comment objcomment = new Comment();
            	objcomment.setUsername(item.select("p.name").text());
            	objcomment.setRank(item.select("span").attr("class") +"-"+ item.select("span").attr("title"));
            	objcomment.setRst(item.select("div.comment-rst").text());
            	objcomment.setRemark(item.getElementsByClass("desc J_brief-cont").text());
            	Elements compics = item.getElementsByClass("shop-photo").select("li");
            	ArrayList<String> pictmps = new ArrayList<String>();
            	for(Element itempic : compics){
            		pictmps.add(itempic.select("img").attr("lazy-src"));
        //    		System.out.println("comment-img : " + itempic.select("img").attr("lazy-src"));
            	}
            	objcomment.setImgurls(pictmps);
            	shopcomments.add(objcomment);
            	
            }
            
            obj.setRank(eranks.select("em").text());
            obj.setshopname(shopname.text());
            obj.setShopAddress(shopaddress.text());
            obj.setShopPhone(shopphone.attr("data-phone"));
            obj.setShopInfo(tmpinfo);
            obj.setShopCourses(shopcourses);
            obj.setShopTeachers(shoptheachers);
            obj.setShopPictures(shoppictures);
            obj.setShopmedias(shopmedias);
            obj.setShopcomments(shopcomments);

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
    public List<Map<String, String>> crawler(String urlStr,int index){
        String str = "";  //用于接收爬取到的网页
     //   String newUrl = "";  //用于接收返回的符合规则的新url
        List<Map<String, String>> maptmp = new ArrayList<Map<String, String>>();
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
     //       	System.out.println("return 200 : " + urlConnection.getInputStream());
                reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((line=reader.readLine())!=null){
                    str += line;
                }
   //             System.out.println("str : " + str);
                //解析html
                if(index == 1){
                	maptmp = analysis(str);
                }if(index == 2){
                	maptmp = analysis2(str);
                }else{
    
                }

            }else{
                System.out.println("获取不到网页的源码，服务器响应代码为："+responsecode);
            }
        }catch(Exception e){
            System.out.println("获取不到网页的源码,出现异常："+e);
        }
        return maptmp;
    }
    
    /**
     * ================================================================================================================
     * 解析html
     * 返回新url
     */
    public List<Map<String, String>> analysis(String str){

    	List<Map<String, String>> maptmp = new ArrayList<Map<String, String>>();
        //假设我们获取的HTML的字符内容如下
        String html = str;

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
        Elements elements1 = doc.getElementsByClass("edu-nav J_edu-nav");
   //     Elements doc1 = elements1.select("a.name");
//        System.out.println("elements1 : " + doc1);
        
      //培训类型
        Elements doc1 = elements1.select("li");
//        System.out.println("elements1 : " + doc1);
        for ( Element element : doc1 ){
        	//info_urls.add("http://www.dianping.com" + element.attr("href"));
        	Map<String, String> mapone = new HashMap<String, String>();
        	mapone.put("url","http://www.dianping.com" + element.select("a.name").attr("href"));
        	mapone.put("type",element.select("a.name").text());
        	mapone.put("topclass","1");
        	mapone.put("ptype","");
        	maptmp.add(mapone);
      //  	System.out.println("element1 : " + element.select("a.name").text());
        	for( Element item : element.select("a.item")){
        		Map<String, String> maptwo = new HashMap<String, String>();
        		maptwo.put("url","http://www.dianping.com" + item.attr("href"));
        		maptwo.put("type",item.text());
        		maptwo.put("topclass","0");
        		maptwo.put("ptype",element.select("a.name").text());
        		maptmp.add(maptwo);
        //		System.out.println("element2 : " + item.text());
        	}
        	
        }
        
        return maptmp;
    }
    
    /**
     * 解析第二层html
     *
     */
    public List<Map<String, String>> analysis2(String str){
    	List<Map<String, String>> maptwos = new ArrayList<Map<String, String>>();
        //假设我们获取的HTML的字符内容如下
        String html = str;

        //第一步，将字符内容解析成一个Document类
        Document doc = Jsoup.parse(html);
        //     Elements elements1 = doc.getElementsByClass("tit");
        Elements elements1 = doc.select("#shop-all-list");
        Elements doc1 = elements1.select("li");
        for ( Element element : doc1 ){
        	//url地址
        	Map<String, String> mapobj = new HashMap<String, String>();
        	Elements urladdress = element.select("a[data-hippo-type=\"shop\"]");
        	info_urls2.add(urladdress.attr("href"));
        	mapobj.put("url",urladdress.attr("href"));
        	//评论条数
        	Elements commentsnum = element.getElementsByClass("review-num");
        	if(commentsnum.isEmpty()){
        		System.out.println("commentsnum : null");
        		mapobj.put("commentsnum","0");
        		mapobj.put("效果","0.0");
        		mapobj.put("师资","0.0");
        		mapobj.put("环境","0.0");
        		
        	}else{
        		System.out.println("commentsnum : 非null ->" + commentsnum);
	        	mapobj.put("commentsnum",commentsnum.select("b").text());
	        	//stars数量
	        	Elements stars = element.select("div.comment>span");
	        	
	        	String regEx="[^0-9]"; 
	        	Pattern p = Pattern.compile(regEx);
	        	Matcher m = p.matcher(stars.attr("class"));
	        	mapobj.put("stars",m.replaceAll("").trim());
	        	//评分
	        	Elements comments = element.select("span.comment-list>span");
	        	if(comments.isEmpty()){
	        		mapobj.put("效果","0.0");
	        		mapobj.put("师资","0.0");
	        		mapobj.put("环境","0.0");
	        	}else{
	        		for(Element obj : comments){
		        		String tmp = obj.select("span").text().trim();
		        		mapobj.put(tmp.substring(0,2),tmp.substring(2,5));
		        	}
	        	}
	        	
        	}
        	maptwos.add(mapobj);
        }
        //第二步，根据我们需要得到的标签，选择提取相应标签的内容
        return maptwos;
    }  
}
