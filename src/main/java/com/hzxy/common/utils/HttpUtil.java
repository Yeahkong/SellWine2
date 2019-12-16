package com.hzxy.common.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/** * @author  作者 E-mail:
 * @date 创建时间：2016-11-11 下午4:39:32 
 * @version 1.0 
 * @parameter  
 * @return  
 */

@SuppressWarnings("deprecation")
public class HttpUtil {

    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static String postData(String urlStr, String data) {
            return postData(urlStr, data, null);
    }

    /**
     * @param urls
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */

    public static String sendPost(String urls) {
    	  DefaultHttpClient httpclient = new DefaultHttpClient();
          HttpPost httppost = new HttpPost(urls);

          String conResult="";
          try {
              HttpResponse response = httpclient.execute(httppost);
              int statusCode = response.getStatusLine().getStatusCode();
              if (statusCode == 200) {
                  /*读返回数据*/
                  conResult = EntityUtils.toString(response
                          .getEntity());

              } else {
                  String err = response.getStatusLine().getStatusCode() + "";
                  conResult += "false:" + err;
              }
          } catch (ClientProtocolException e) {
              conResult += "false:"+e.getMessage();
              e.printStackTrace();
          } catch (IOException e) {
              conResult += "false:"+e.getMessage();
              e.printStackTrace();
          }
          return conResult;
    }

    public static String postData(String urlStr, String data, String contentType) {
            BufferedReader reader = null;
            try {
                    URL url = new URL(urlStr);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(CONNECT_TIMEOUT);
                    conn.setReadTimeout(CONNECT_TIMEOUT);
                    if (contentType != null)
                            conn.setRequestProperty("content-type", contentType);
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
                    if (data == null)
                            data = "";
                    writer.write(data);
                    writer.flush();
                    writer.close();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                            sb.append(line);
                            sb.append("\r\n");
                    }
                    return sb.toString();
            } catch (IOException e) {
                    // logger.error("Error connecting to " + urlStr + ": " +
                    // e.getMessage());
            } finally {
                    try {
                            if (reader != null)
                                    reader.close();
                    } catch (IOException e) {
                    }
            }
            return null;
    }
}
