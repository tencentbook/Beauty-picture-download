package 宅男女神网站图片下载.套图整页下载;
import 宅男女神网站图片下载.静态方法;
import java.util.ArrayList;

public class 宅男女神整页下载类 {
    public static void main(String[] args) throws InterruptedException {
        long a1= System.currentTimeMillis();
        //首先输入首页，获取首页信息
        String 相册首页="https://www.nvshens.org/girl/19411/album/4.html";
        相册首页 =  静态方法.访问网页(相册首页);
        String 第一段=相册首页;
        String 后缀;

        ArrayList<String> 后缀集合=new  ArrayList<>();
        for (int i = 0; i < 30; i++) {
            第一段=第一段.substring(第一段.indexOf("igalleryli_link")+24);
            后缀=静态方法.数字过滤(第一段.substring(0,第一段.indexOf("img"))) ;
            后缀集合.add(后缀);
            if (第一段.indexOf("igalleryli_link")==-1){
                break;
            }
        }

        String 首页;
        for (int i = 0; i <后缀集合.size() ; i++) {
            首页="https://www.nvshens.org/g/"+后缀集合.get(i)+"/";
            System.out.println(首页);
           宅男女神下载方法.宅男女神一个相册下载(首页);

            //避免高并发
            if (i%3==0&&i!=0){
                System.out.println("---暂停5秒避免高IO并发---");
                Thread.sleep(5000);
            }
        }

        //统计总共下载时间
        Runtime.getRuntime().addShutdownHook(new Thread(()->
       System.out.println("--下载结束,共耗时--"+(System.currentTimeMillis()-a1)/1000+"秒")) );
    }
}
