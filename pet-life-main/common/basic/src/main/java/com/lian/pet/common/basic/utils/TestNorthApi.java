package com.lian.pet.common.basic.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class TestNorthApi {
    private static final String ApicHost = "10.10.10.1:1443";
    private static final String appId = "roma.link.test";
    private static final String appKey = "ic+HsXPRCA****";

    private static final String deviceId="D6111KOQk";


    //�������·�ʱ�õ��ģ��滻���Լ���serviceName��commandName
    private static final String serviceName="service";
    private static final String commandName="command";


    /**
     * ����ssl���ӣ����ÿͻ�����������֤��
     * @return CloseableHttpClient
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static CloseableHttpClient createSSLClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2"}, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
    
    /**
     * ��������ʱ��header
     * @param httpUriRequest
     */
    private static void setSSLHeader(HttpUriRequest httpUriRequest) {
    	long time = System.currentTimeMillis();
    	String authorization = DigestUtils.sha256Hex(appId + appKey + time);
        httpUriRequest.addHeader("Content-Type", "application/json");
        httpUriRequest.addHeader("Authorization", authorization);
        httpUriRequest.addHeader("timestamp", String.valueOf(time));
    }
    /**
     * ������Ӧ���
     * @param httpUriRequest
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     */
    private static HttpEntity getSSLResponse(HttpUriRequest httpUriRequest) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        CloseableHttpClient httpClient = createSSLClient();
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("��Ӧ״̬Ϊ:" + response.getStatusLine());
        if (responseEntity != null) {
            System.out.println("��Ӧ���ݳ���Ϊ:" + responseEntity.getContentLength());
            System.out.println("��Ӧ����Ϊ:" + EntityUtils.toString(responseEntity));
        }
        return responseEntity;
    }

    /**
     * �����·�
     *
     * @param deviceId
     * @param serviceName
     * @param commandName
     * @param cmdComtent
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
    public static void deviceCommands(String serviceName, String commandName, JSONObject cmdComtent) throws JSONException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String url = "https://" + ApicHost + "/iot/1.0/deviceCommands?appId=" + appId;
        HttpPost httpPost = new HttpPost(url);  //����post����

        JSONObject cmdContent = new JSONObject();
        cmdContent.put("serviceId", serviceName);
        cmdContent.put("method", commandName);
        cmdContent.put("paras", cmdComtent);
        JSONObject cmdBody = new JSONObject();
        cmdBody.put("command", cmdContent);
        cmdBody.put("deviceId", deviceId);

        setSSLHeader(httpPost);	//���������header
        StringEntity stringEntity = new StringEntity(cmdBody.toString(), "utf-8");
        httpPost.setEntity(stringEntity);//���������body
        getSSLResponse(httpPost);//��ȡ��Ӧ���

    }


    public static void main(String[] args) throws Exception {
        JSONObject commandContent = new JSONObject();
        commandContent.put("status", "on");
        deviceCommands(serviceName, commandName, commandContent);

    }
}