package com.common.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * Post发送字符流
     * @param urlStr
     * @param req
     * @return result
     * @throws IOException
     * @author LiHao
     */
    public static String postMethod(String urlStr,String req,Integer timeOut) {
        OutputStreamWriter out = null;
        HttpURLConnection con = null;
        try{
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("content-type", "text/html");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(timeOut*1000);//连接主机超时（时间单位毫秒）
            con.setReadTimeout(timeOut*1000);//从主机读取数据超时

            out = new OutputStreamWriter(con.getOutputStream(),"utf-8");
            out.write(req.toString());
        }catch(IOException e){
            logger.info("错误信息:"+e.toString());
        }finally{
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }

            }
        }
        String respDesc = "";
        BufferedReader br = null;
        InputStreamReader isr = null;
        InputStream is = null;
        try{
            logger.info("请求接口的返回码："+con.getResponseCode());
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = con.getInputStream();
                isr = new InputStreamReader(is,"utf-8");
                br =new BufferedReader(isr);
                String valueString = null;
                while ((valueString=br.readLine())!=null){
                    respDesc+=valueString;
                }

            }
        }catch(IOException e){
            logger.info("错误信息:"+e.toString());
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }
            }
			/*if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					logger.info("错误信息:"+e.toString());
				}
			}*/
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }
            }
        }

        return respDesc;
    }
    /**
     * 重载
     * Post发送字符流
     * @param urlStr
     * @param req
     * @return result
     * @throws IOException
     * @author LiHao
     */
    public static String postMethod(String urlStr,String req,String type,Integer timeOut) {
        OutputStreamWriter out = null;
        HttpURLConnection con = null;
        try{
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("content-type", type);
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(timeOut*1000);//连接主机超时（时间单位毫秒）
            con.setReadTimeout(timeOut*1000);//从主机读取数据超时

            out = new OutputStreamWriter(con.getOutputStream(),"utf-8");
            out.write(req.toString());
        }catch(IOException e){
            logger.info("错误信息:"+e.toString());
        }finally{
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }

            }
        }
        String respDesc = "";
        BufferedReader br = null;
        InputStreamReader isr = null;
        InputStream is = null;
        try{
            logger.info("请求接口的返回码："+con.getResponseCode());
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = con.getInputStream();
                isr = new InputStreamReader(is,"utf-8");
                br =new BufferedReader(isr);
                String valueString = null;
                while ((valueString=br.readLine())!=null){
                    respDesc+=valueString;
                }

            }
        }catch(IOException e){
            logger.info("错误信息:"+e.toString());
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }
            }
			/*if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					logger.info("错误信息:"+e.toString());
				}
			}*/
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    logger.info("错误信息:"+e.toString());
                }
            }
        }

        return respDesc;
    }

    /**
     * POST发送表单请求
     * @param url
     * @param parms
     * @return
     * @throws IOException
     */
    public static String httpPost(String url, Map<String, String> parms)
            throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .setSocketTimeout(30000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : parms.entrySet()){
            params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
        CloseableHttpResponse response = null;
        int a = -10;
        String result = "error";
        try {
            response = httpClient.execute(httpPost);
            a = response.getStatusLine().getStatusCode();
            logger.info("请求接口的返回码："+a);
            if(a == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"utf-8");
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            httpClient.close();
            if(response != null){
                response.close();
            }
        }
        return result;
    }


    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * HTTPGet访问方法
     * @param url
     * @param param
     * @author LiShuhan
     * @return
     */
    public static String httpGet(String url,String param){

        String result = "error";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000).build();
        HttpGet httpget= new HttpGet(url+"?"+param);
        httpget.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        int a = -10;
        try {
            response = httpClient.execute(httpget);
            a = response.getStatusLine().getStatusCode();
            logger.info("请求返回码是:"+a);
            if(a == 200){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"utf-8");
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                httpClient.close();
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 创建能调用Https的HttpClient,实测有效
     * @return CloseableHttpClient
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static CloseableHttpClient createHttpsClient() throws Exception   {
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { x509mgr }, new java.security.SecureRandom());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
    /**
     * 向指定URL发送POST方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String getUrlResultPost(String url, String param) {

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            URL localURL = new URL(url);
            URLConnection connection = localURL.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String
                    .valueOf(param.length()));
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(30000);

            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();
            if (httpURLConnection.getResponseCode() != 200) {

            } else {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("访问出错" + url + "?" + param);
        } catch (Exception e) {
            System.out.println("访问出错:" + e.getMessage());
            System.out.println("访问出错" + url + "?" + param);
        } finally {
            try {
                if (outputStreamWriter != null)
                    outputStreamWriter.close();
                if (outputStream != null)
                    outputStream.close();
                if (reader != null)
                    reader.close();
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(resultBuffer);
        return resultBuffer.toString();
    }
    /**
     * 创建默认的HttpClient
     * @return
     */
    public static CloseableHttpClient createHttpClient(){
        return HttpClients.createDefault();
    }
}
