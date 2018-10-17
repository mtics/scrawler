package scawler.web;

import scawler.web.entity.Weibo;
import scawler.web.service.ElementGetService;
import scawler.web.service.ElementHandlerService;
import scawler.web.service.WeiboLogin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 该类用于加载Main主方法
 */
public class Test {

    public static void main(String[] args){

        ElementGetService egs = new ElementGetService();
        //WeiboLogin weiboLogin = new WeiboLogin();
        List<Weibo> weiboList = null;

        try {
            //weiboLogin.Login();
            weiboList = egs.getWeibo();
            for(Weibo weibo: weiboList){
                System.out.println(weibo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
