package 宅男女神网站图片下载;

public class 宅男女神多线程下载 implements Runnable{
    private String Url;

    private String 图集名称;

    private String 图片名称;

    public 宅男女神多线程下载() {
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void set图集名称(String 图集名称) {
        this.图集名称 = 图集名称;
    }

    public void set图片名称(String 图片名称) {
        this.图片名称 = 图片名称;
    }

    @Override
    public void run() {
        静态方法.输入图片连接下载(Url,"F:\\"+图集名称+"\\"+图片名称);
    }

}
