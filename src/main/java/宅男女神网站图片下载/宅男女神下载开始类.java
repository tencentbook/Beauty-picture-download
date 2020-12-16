package 宅男女神网站图片下载;

import java.io.File;
import java.util.ArrayList;

public class 宅男女神下载开始类 {
    public static void main(String[] args) throws InterruptedException {
        long a1= System.currentTimeMillis();
        //首先输入首页，获取首页信息
        String 首页="https://www.nvshens.org/g/11282/";
        首页 =  静态方法.访问网页(首页);
        String 图集名称=首页.substring(首页.indexOf("htilte")+8,首页.indexOf("</h1>")) ;
        String 图集页数=首页.substring(首页.indexOf("张照片")-4,首页.indexOf("张照片"));
        int 页数=Integer.parseInt(静态方法.数字过滤(图集页数)) ;
        String 图片链接模板= 首页.substring(首页.indexOf("<img src=")+10,首页.indexOf("alt")-7);
        ArrayList<String> 图片链接集合=new  ArrayList<>();
        StringBuilder 链接模板=new StringBuilder(图片链接模板);
        //添加第一张图片
        图片链接集合.add(链接模板.append("0.jpg").toString());
        //添加剩余图片
        for (int i =1; i < 页数; i++) {
            链接模板=new StringBuilder(图片链接模板);
            链接模板.append(静态方法.补零到3位(i)).append(".jpg");
            图片链接集合.add(链接模板.toString());
        }
        //创建目录放到F盘，循环下载，可以从任意页开始，不过建议从首页开始
        Boolean 创建目录=new File("F:\\"+图集名称).mkdirs();

        //循环下载剩下的图片，实现了runable接口的类
        宅男女神多线程下载  宅男女神多线程下载=new 宅男女神多线程下载();
        Thread thread=null;
        String 下载链接=null;
        String 图片名称=null;

            for (int i = 0; i < 图片链接集合.size(); i++) {
                //避免多线程过快反应冲突
                Thread.sleep(50);
                下载链接=图片链接集合.get(i);
                宅男女神多线程下载.setUrl(下载链接);
                宅男女神多线程下载.set图集名称(图集名称);
                图片名称= 图集名称+静态方法.补零到3位(i)+".jpg";
                宅男女神多线程下载.set图片名称(图片名称);
                //初始化Thread实例
                thread=new Thread(宅男女神多线程下载);
                //开始运行
                thread.start();
                System.out.println(图片名称+"--开始下载");

                //避免高并发
                if (i%19==0&&i!=0){
                    System.out.println("---暂停5秒避免高IO并发---");
                    Thread.sleep(5000);
                }

            }

        //统计总共下载时间
        Runtime.getRuntime().addShutdownHook(new Thread(()->
       System.out.println(图集名称+"--下载结束,共耗时--"+(System.currentTimeMillis()-a1)/1000+"秒")) );
    }
}
