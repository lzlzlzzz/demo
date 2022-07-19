package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpUtil {

    private static final int READ_TIMEOUT = 60000;

    private static final int CONNECT_TIMEOUT = 60000;

    /**
     * http get request
     */
    public static String get(String urlAddr, Map<String, Object> paramsMap, int connectTimeout, int readTimeout) throws Exception {
        log.info("get request url: {}, params: {}", urlAddr, JSONObject.toJSONString(paramsMap));
        String line;
        String params = "";
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer result = new StringBuffer();
        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            if (paramsMap != null && !paramsMap.isEmpty()) {
                StringBuffer str = new StringBuffer();
                Set set = paramsMap.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (paramsMap.get(key) == null) {
                        continue;
                    }
                    str.append(key).append("=").append(paramsMap.get(key)).append("&");
                }
                if (str.length() > 0) {
                    params = "?" + str.substring(0, str.length() - 1);
                }
            }
            URL url = new URL(urlAddr + params);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            conn.connect();
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    /**
     * http post request
     */
    public static String post(String urlAddr, Map<String, Object> paramsMap, int connectTimeout, int readTimeout) throws Exception {
        log.info("post request url: {}, params: {}", urlAddr, JSONObject.toJSONString(paramsMap));
        String line;
        String params = "";
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        StringBuffer result = new StringBuffer();
        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            if (paramsMap != null && !paramsMap.isEmpty()) {
                StringBuffer str = new StringBuffer();
                Set set = paramsMap.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (paramsMap.get(key) == null) {
                        continue;
                    }
                    str.append(key).append("=").append(paramsMap.get(key)).append("&");
                }
                if (str.length() > 0) {
                    params = str.substring(0, str.length() - 1);
                }
            }
            URL url = new URL(urlAddr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，
            // 因此需要设为true, 默认情况下是false
            conn.setDoOutput(true);
            // 不使用缓存,默认情况下是true
            conn.setUseCaches(false);
            // 设定请求的方法为"POST",默认是GET
            conn.setRequestMethod("POST");
            if (!params.isEmpty()) {
                // 此处getOutputStream会隐含的进行connect()
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
                // 写入
                outputStreamWriter.write(params);
                // 刷新该流的缓冲
                outputStreamWriter.flush();
            }
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    /**
     * http post request
     */
    public static String post(String urlAddr, String paramsStr, int connectTimeout, int readTimeout) throws Exception {
        log.info("post request url: {}, params: {}", urlAddr, paramsStr);
        String line;
        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        StringBuffer result = new StringBuffer();
        try {
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            URL url = new URL(urlAddr);
            conn = (HttpURLConnection) url.openConnection();
            // 设置读取超时时间
            conn.setReadTimeout(readTimeout);
            // 设置连接超时时间
            conn.setConnectTimeout(connectTimeout);
            // 设置是否向HttpURLConnection输出，因为这个是post请求，参数要放在http正文内，
            // 因此需要设为true, 默认情况下是false
            conn.setDoOutput(true);
            // 不使用缓存,默认情况下是true
            conn.setUseCaches(false);
            // 设定请求的方法为"POST",默认是GET
            conn.setRequestMethod("POST");
            if (paramsStr != null && !paramsStr.isEmpty()) {
                // 此处getOutputStream会隐含的进行connect()
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
                // 写入
                outputStreamWriter.write(paramsStr);
                // 刷新该流的缓冲
                outputStreamWriter.flush();
            }
            inputStreamReader = new InputStreamReader(conn.getInputStream(), "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    /**
     * 根据IP获取地址
     */
    public static JSONObject getIpAddress(String ip) {
        if (ip == null || ip.trim().length() < 1) {
            return null;
        }
        String code = "0";
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        try {
            String res = HttpUtil.get(url, null, 0, 0);
            JSONObject json = JSONObject.parseObject(res);
            String backCode = json.getString("code");
            if (code.equals(backCode)) {
                return json.getJSONObject("data");
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}