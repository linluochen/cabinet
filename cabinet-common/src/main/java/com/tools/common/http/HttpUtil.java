package com.tools.common.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 请求工具类
 *
 * @author : liii
 * @version : 1.0.0
 * @date : 2015/7/21
 * @see : TODO
 */
public class HttpUtil {
    private static final int MAX_TIMEOUT = 7000;
    private static RequestConfig requestConfig;
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    static {
        // 设置连接池

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url, String sessionId) {
        return doGet(url, new HashMap<String, Object>(), sessionId);
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params, String sessionId) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            System.out.println(apiUrl);
            httpPost.setHeader("Cookie", "JSESSIONID=" + sessionId);
            HttpResponse response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("执行状态码 : " + statusCode);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, Object>());
    }


    /**
     * post请求
     *
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url, JSONObject json) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity(), "UTF-8");// 返回json格式：
                response = JSONObject.parseObject(result);//json对象转字符串
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            //httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            //httpPost.setHeader("Cookie", "JSESSIONID="+sessionId);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }


    public static JSONObject send(String requestUrl, String requestXML) {
        String msgXML = "";
        JSONObject response = null;
        HttpURLConnection httpConnection = null;
        try {
            URL url = new URL(requestUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setConnectTimeout(10000);
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            httpConnection.setAllowUserInteraction(true);
            httpConnection.setRequestProperty("Content-Type", "application/json");
            httpConnection.connect();

            // 2、发送数据
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(requestXML.getBytes("UTF-8"));
            log.info("平台发送的数据:" + requestXML);
            outputStream.flush();
            outputStream.close();

            // 3、返回数据
            InputStreamReader inputStreamReader = new InputStreamReader(httpConnection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String inputLine = "";
            StringBuffer inputLines = new StringBuffer();
            inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                inputLines.append(inputLine);
                inputLine = bufferedReader.readLine();
            }
            inputStreamReader.close();
            bufferedReader.close();

            // 4、关闭连接
            httpConnection.disconnect();
            log.info("平台接收到的数据:" + inputLines.toString());
            msgXML = inputLines.toString();
            response = JSONObject.parseObject(msgXML);
            return response;
        } catch (Exception e) {
            log.info(":" + e, e);
            if (e instanceof SocketTimeoutException) {
                log.error("" + e, e);
                response.put("err", "timeOut");
                return response;
            } else {
                log.error("" + e, e);
                response.put("err", "IOException");
                return response;
            }
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
            log.info(":对接第三方平台结束时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        }
    }


}