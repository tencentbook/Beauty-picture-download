package 套图吧网站图片下载;

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
    public static InputStream 输入图片连接下载(String imgUrl, String savePath) {
        InputStream inputStream = null;
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imgUrl).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            httpURLConnection.setRequestProperty("Referer","no-referrer");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
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
        return inputStream;
    }

    public static String 访问网页(String Url) {
        String content=null;
        try {
            Thread.sleep(100);
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


}
