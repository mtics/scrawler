package scawler.web.entity;

public class Weibo {

    private String uid;

    private String uname;

    private String content;

    private String published_time;  //发布时间

    private String platform;        //发布平台

    private int forwarding;         //转发数

    private int comments;           //评论数

    private int praises;            //点赞数

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublished_time() {
        return published_time;
    }

    public void setPublished_time(String published_time) {
        this.published_time = published_time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getForwarding() {
        return forwarding;
    }

    public void setForwarding(int forwarding) {
        this.forwarding = forwarding;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getPraises() {
        return praises;
    }

    public void setPraises(int praises) {
        this.praises = praises;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", content='" + content + '\'' +
                ", published_time='" + published_time + '\'' +
                ", platform='" + platform + '\'' +
                ", forwarding=" + forwarding +
                ", comments=" + comments +
                ", praises=" + praises +
                '}';
    }
}
