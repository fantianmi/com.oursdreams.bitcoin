package com.mvc.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;

import org.junit.Test;

@Entity
public class LDSendMsg {
	/*
	 * webservice服务器定义
	 */
	// 我们所有的Demo，都是在GBK环境下测试的。
	// 如果您的系统是utf-8,调用注册方法可能不成功。
	// java.io.IOException: Server returned HTTP response code: 400 for URL:
	// http://sdk2.zucp.net:8060/webservice.asmx。
	// 如果出现上述400错误，请参考第105行。
	// 如果您的系统是utf-8，收到的短信可能是乱码，请参考第298行
	// 可以根据您的需要自行解析下面的地址
	// http://sdk2.zucp.net:8060/webservice.asmx?wsdl
	private String serviceURL = "http://86api.com/sms/SmsHttpPort.aspx?";
	private HttpURLConnection urlconn;

	private String sn = "";// 序列号
	private String password = "";// 密码

	/*
	 * 构造函数
	 */
	public LDSendMsg() {
	}

	public LDSendMsg(String sn, String password)
			throws UnsupportedEncodingException {
		this.sn = "guobiwang";
		this.password = "qwas1234";
	}

	/*
	 * 方法名称：getMD5 功 能：字符串MD5加密 参 数：待转换字符串 返 回 值：加密之后字符串
	 */
	public String mdSmsSend_u(String mobile, String content, String ext,
			String stime, String rrid) throws IOException {
		String params = "Action=SendMessage&UserId=" + sn + "&UserPwd=" + password
				+ "&SendPhone=" + mobile + "&SendMessage=" + content + "&SendPort=80";
		URL url;
		BufferedReader br = null;
		String response = "", brLine = "";
		try {
			url = new URL(serviceURL);
			urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setConnectTimeout(30000);
			urlconn.setReadTimeout(30000);
			urlconn.setRequestMethod("POST"); // request method, default GET
			urlconn.setUseCaches(false); // Post can not user cache
			urlconn.setDoOutput(true); // set output from urlconn
			urlconn.setDoInput(true); // set input from urlconn

			OutputStream out = urlconn.getOutputStream();
			out.write(params.getBytes("utf-8"));
			out.flush();
			out.close();
			br = new BufferedReader(new InputStreamReader(urlconn.getInputStream(),
					"utf-8"));
			while ((brLine = br.readLine()) != null)
				response = (new StringBuilder(String.valueOf(response))).append(brLine)
						.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("input stream close fail");
			}
			urlconn.disconnect();
		}

		return response;
	}
	@Test
	public void sendMsgTest() throws IOException{
		LDSendMsg lsm = new LDSendMsg("test1","123test");
		String result = lsm.mdSmsSend_u("18688164055", "test ld龙鼎科技", "", "", "");
		System.out.println(result);
	}
}
