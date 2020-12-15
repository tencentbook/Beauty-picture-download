package 宅男女神网站图片下载;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class 静态方法 {
    public static void 输入图片连接下载(String imgUrl, String savePath) {
        InputStream inputStream = null;
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imgUrl).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            httpURLConnection.setRequestProperty("Referer","https://www.nvshens.org/");
            httpURLConnection.setRequestProperty("Content-Type","image/jpeg");
            httpURLConnection.setConnectTimeout(100000);
            httpURLConnection.setReadTimeout(100000);
            inputStream = httpURLConnection.getInputStream();

            FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));
            byte[] buffer = new byte[1024000];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            inputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String 访问网页(String Url) {
        String content=null;
        try {

            HttpClient client = HttpClientBuilder.create().build(); //构建一个Client
            HttpGet httpget = new HttpGet(Url);  //构建一个get请求
            httpget.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                    1000 * 30); // 设置请求超时时间
            httpget.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
            httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpget.setHeader("Accept-Encoding", "gzip,deflate,sdch");	//需要加上这个头字段

            HttpResponse response = client.execute(httpget);//提交get请求
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
             content = EntityUtils.toString(result,"UTF-8");;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    //数字过滤
    public static String 数字过滤(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     * 数字转字符串前面自动补0的实现
     */
    public static String 补零到3位(int 数字) {
        // 0 代表前面补充0
        // 3 代表长度为3
        // d 代表参数为正数型
        String str = String.format("%03d", 数字);
        return str; // 001
    }
}
