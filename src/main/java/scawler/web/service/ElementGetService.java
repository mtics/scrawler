package scawler.web.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scawler.web.entity.Weibo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElementGetService {

    private ElementHandlerService ehs = new ElementHandlerService();

    public Document getDocument(String URL){

        try{
            return Jsoup.connect(URL).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Weibo> getWeibo() throws IOException {
        ElementGetService egs = new ElementGetService();

        File input = new File("/home/incentancy/workspace/Java/scrawler/src/main/resources/weibo-home.html");
        //Document doc = Jsoup.parse(input, "UTF-8", "");

        Document doc = egs.getDocument("https://weibo.com/u/6764253608/home?wvr=5");

        //获取目标HTML代码
        Elements allContents = doc.select("[class=WB_feed WB_feed_v3 WB_feed_v4]");
        //获取所有VIP内容框
        Elements vipContents = allContents.select("[class=WB_cardwrap WB_feed_type S_bg2 WB_feed_vipcover WB_feed_like]");
        //获取所有非VIP内容框
        Elements notVipContents = allContents.select("[class=WB_cardwrap WB_feed_type S_bg2 WB_feed_like]");

        List<Weibo> weiboList = new ArrayList<Weibo>();

        for(int i = 0; i < vipContents.size(); i ++){
            weiboList.add(ehs.ElementHandler(vipContents.get(i)));
        }

        for(int i = 0; i < notVipContents.size(); i ++){
            weiboList.add(ehs.ElementHandler(notVipContents.get(i)));
        }

        return weiboList;
    }
}
