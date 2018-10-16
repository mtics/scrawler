package scawler.web;

import scawler.web.service.WeiboLogin;

import java.io.IOException;
import java.net.URISyntaxException;

public class Test {

    public static void main(String[] args){
        try {
            new WeiboLogin().Login();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
