package scawler.web.service;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scawler.web.entity.Weibo;

import java.util.regex.Pattern;


public class ElementHandlerService {

    DateService ds = new DateService();

    /*
     * 处理网页元素，从中获取所需信息
     * 并将信息添加入一个Weibo实体中
     */
    public Weibo ElementHandler(Element element){

        Weibo weibo = new Weibo();

        //进入每一个内容框
        Elements contentElements = element.select("[class=WB_feed_detail clearfix]");

        //选择每一个框中的信息
        Elements infoELements = contentElements.select("[class=WB_info]");

        //获取昵称
        Elements aElements = infoELements.select("a");

        String nickName = aElements.get(0).attr("nick-name");
        String usercard = aElements.get(0).attr("usercard");
        int beginIndex = usercard.indexOf('=');
        int endIndex = usercard.indexOf('&');
        String uid = usercard.substring(beginIndex+1, endIndex);

        //获取发布时间
        Elements labelElements = contentElements.select("[class=WB_from S_txt2]");

        //注意这个时候还可以从里面获得当前时间
        //String publishedTime = labelElements.get(0).getElementsByTag("a").get(0).text();
        String seconds = labelElements.get(0).getElementsByTag("a").get(0).attr("date");
        String publishedTime = ds.Second2Date(seconds);
        String platform = labelElements.get(0).getElementsByTag("a").get(1).text();
        String content = contentElements.select("[class=WB_text W_f14]").get(0).getElementsByTag("div").get(0).text();

        //获取图片地址
        /*
        Elements liElements = contentElements.select("[class=WB_media_a WB_media_a_mn WB_media_a_m4 clearfix]");
        Elements imgElements = liElements.select("[class=WB_pic li_1 S_bg1 S_line2 bigcursor]");
        String[] picUrl = null;
        int i = 0;
        for(Element imgElement:imgElements) {
            picUrl[i] = imgElement.attr("src");
            i++;
        }
        */

        //底部四个栏目
        Elements buttomElements = element.select("[class=WB_row_line WB_row_r4 clearfix S_line2]");
        Elements buttomLiElements = buttomElements.get(0).select("li");
        Elements emElements = null;
        //转发
        emElements = buttomLiElements.get(1).select("em");
        String forwardingStr = emElements.get(1).text();
        int forwarding = Str2Num(forwardingStr);
        //评论
        emElements = buttomLiElements.get(2).select("em");
        String commentStr = emElements.get(1).text();
        int comments = Str2Num(commentStr);
        //点赞
        emElements = buttomLiElements.get(3).select("em");
        String praiseStr = emElements.get(1).text();
        int praise = Str2Num(praiseStr);

        weibo.setUid(uid);
        weibo.setUname(nickName);
        weibo.setContent(content);
        weibo.setPublished_time(publishedTime);
        weibo.setPlatform(platform);
        weibo.setForwarding(forwarding);
        weibo.setComments(comments);
        weibo.setPraises(praise);

        return weibo;
    }

    /*
     * 将字符串转化为整数
     * 若碰到汉字，则为0
     */
    public int Str2Num(String str){
        boolean isNum = Pattern.matches("//d+", str);
        if (isNum)
            return Integer.parseInt(str);
        return 0;
    }
}
