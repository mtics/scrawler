package scawler.web.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import scawler.web.entity.CookieInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class WeiboLogin {

    private CookieInfo cookieInfo = new CookieInfo();

    /*
     * 初始登录信息, 返回false说明初始失败
     */
    /*
    public boolean Login(String username, String passwd){

        boolean flag = false;
        String su = null;

        try{
            su = new String(Base64.encodeBase64(URLEncoder.encode(username, "UTF-8").getBytes()));
            String url = "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    */

    public WeiboLogin(){
        //Cookie策略，不设置会拒绝Cookie Rejected, 设置策略保存Cookie信息
        cookieInfo.setCookieStore(new BasicCookieStore());

        CookieSpecProvider myCookie = new CookieSpecProvider() {
            public CookieSpec create(HttpContext httpContext) {
                return new DefaultCookieSpec();
            }
        };

        Registry<CookieSpecProvider> rg = RegistryBuilder.<CookieSpecProvider>create().
                register("myCookie", myCookie).build();

        cookieInfo.setClient(HttpClients.custom().setDefaultCookieSpecRegistry(rg).build());
        cookieInfo.setGet(new HttpGet());
        cookieInfo.setPost(new HttpPost());
    }

    public void Login() throws ClientProtocolException, IOException, URISyntaxException{

        String LoginUrl = "https://weibo.cn/?luicode=20000174";

        cookieInfo.getGet().setURI(new URI(LoginUrl));
        cookieInfo.getGet().addHeader("Content-Type", "text/html; charset=utf-8");
        cookieInfo.getGet().addHeader("Host", "weibo.cn");
        cookieInfo.getGet().addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        cookieInfo.getGet().addHeader("API-RemoteIP", "192.168.0.1");
        cookieInfo.getGet().addHeader("X-Forwarded-For","192.168.0.1");
        cookieInfo.getGet().addHeader("CLIENT-IP", "192.168.0.1");
        cookieInfo.getGet().addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        cookieInfo.getGet().addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        cookieInfo.getGet().addHeader("Accept-Encoding", "gzip, deflate, br");
        cookieInfo.getGet().addHeader("Referer", " https://passport.weibo.cn/signin/login?entry=mweibo&r=https%3A%2F%2Fweibo.cn%2F%3Fluicode%3D20000174&backTitle=%CE%A2%B2%A9&vt=");
        cookieInfo.getGet().addHeader("Connection", "keep-alive");
        cookieInfo.getGet().addHeader("Cookie", "SCF=AicAXu8Ojskyd9EA_yFZnluHp0M_xYLrffcGYvSAIaQhaPwWV-VKA3HnEpxP86s5ra00VLETFDLInQEsIZXf53A.; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9Wh3EI8IgOIMxy9Q3753gY1E5JpX5K-hUgL.FoqNSoBESKecehn2dJLoIp7LxKML1KBLBKnLxKqL1hnLBoMcS0qXeo-0So5R; _T_WM=21894e700cf4b4922b2d9ad38a50a791; MLOGIN=1; M_WEIBOCN_PARAMS=luicode%3D20000174; SUB=_2A252waD1DeRhGeBJ7VYT9S3KyzSIHXVSTcC9rDV6PUJbkdAKLVbbkW1NRilER0PQ1TCtXN79rlPoughCr46PGbVw; SUHB=0u5R0naprfboo7; SSOLoginState=1539690661");

        HttpResponse response = cookieInfo.getClient().execute(cookieInfo.getGet());
        HttpEntity entity = response.getEntity();
        String context = EntityUtils.toString(entity);
        System.out.println("获取的微博内容"+context);

    }
}
