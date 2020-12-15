package 套图吧网站图片下载;

public class 多线程优化下载 implements Runnable{
    private String Url;

    private String 图集名称;

    public void setUrl(String url) {
        Url = url;
    }

    public void set图集名称(String 图集名称) {
        this.图集名称 = 图集名称;
    }

    @Override
    public void run() {
        String 首页=Url;
        String 首页信息= 静态方法.访问网页(首页);
        //获取当前页的图片链接和名称
        String 图片链接=首页信息.substring(首页信息.indexOf("lazysrc=")+8,首页信息.indexOf("alt=")).trim();
        String 图片名称起=首页信息.substring(首页信息.indexOf("alt=")+5);
        String 图片名称=图片名称起.substring(0,图片名称起.indexOf("/>")-2);
        静态方法.输入图片连接下载(图片链接,"F:\\"+图集名称+"\\"+图片名称+".jpg");
        System.out.println(图片名称+"--下载成功");
    }
}
