package 套图吧网站图片下载;

import java.io.File;

public class 开始类 {
    public static void main(String[] args) throws InterruptedException {
        //
        long a1= System.currentTimeMillis();
        //首先输入首页，获取首页信息
        String 首页="https://www.192te.com/gq/dm/cosplay370.html";
        String 首页信息= 静态方法.访问网页(首页);
        //建立获取图集名称，图集名称,图片总数
        String 图集名称和图片总数= 首页信息.substring(首页信息.indexOf("<title>")+7,首页信息.indexOf("</title>"));
        String 图集名称=图集名称和图片总数.substring(0,图集名称和图片总数.indexOf("P")+1);
        int 图片总数=Integer.parseInt(图集名称和图片总数.substring(图集名称和图片总数.indexOf("P")-3,图集名称和图片总数.indexOf("P")).trim());

        //创建目录放到F盘，循环下载，注意只能从首页开始
        Boolean 创建目录=new File("F:\\"+图集名称).mkdirs();
        //循环下载剩下的图片，实现了runable接口的类
        多线程优化下载 多线程下载=new 多线程优化下载();
        //首先下载首页图片
        多线程下载.setUrl(首页);
        多线程下载.set图集名称(图集名称);
        Thread thread=new Thread(多线程下载);
        thread.start();
        for (int i = 2; i <= 图片总数; i++) {
            Thread.sleep(100);
            String 从首页开始的地址=首页.substring(0,首页.length()-5)+"_"+i+".html";
            多线程下载.setUrl(从首页开始的地址);
            多线程下载.set图集名称(图集名称);
            //初始化Thread实例
            thread=new Thread(多线程下载);
            //开始运行
            thread.start();
        }
        //统计总共下载时间
        Runtime.getRuntime().addShutdownHook(new Thread(()->
                System.out.println(图集名称+"下载结束--共耗时--"+(System.currentTimeMillis()-a1)/1000+"秒")) );
    }

}
