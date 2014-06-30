package com.mvc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.io.OutputStream;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;

import java.net.URL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class PocketApi {

	// public static final String ADD_URL =
	// "http://192.168.4.68:35357/v2.0/users";

	// public static final String ADD_URL =
	// "http://192.168.4.97:35357/v2.0/tokens";
	public String generalpaymentsadr(
			final String rpcusername,
			final String rpcpassword,
			String rpcpocketadr,
			String rpcport,
			String username
	) throws IOException {

		String ADD_URL = ""+rpcpocketadr+":"+rpcport+"/";
		
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(rpcusername, rpcpassword.toCharArray());
			}
		});

		HttpURLConnection connection = null;
		try {
			// 创建连接
			URL url = new URL(ADD_URL);
			connection = (HttpURLConnection) url.openConnection();

			// 设置http连接属性

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST"); // 可以根据需要 提交
																						// GET、POST、DELETE、INPUT等http提供的功能
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);

			// 设置http头 消息
			connection.setRequestProperty("Content-Type", "application/json"); // 设定
																																					// 请求格式
																																					// json，也可以设定xml格式的
			// connection.setRequestProperty("Content-Type", "text/xml"); //设定 请求格式
			// xml，
			connection.setRequestProperty("Accept", "application/json");// 设定响应的信息的格式为
																																	// json，也可以设定xml格式的
			// connection.setRequestProperty("X-Auth-Token","xx");
			// //特定http服务器需要的信息，根据服务器所需要求添加
			connection.connect();

			// 添加 请求内容

			JSONArray list = new JSONArray();
			list.add(username);

			// 构建嵌套的 json数据

			JSONObject obj = new JSONObject();
			obj.put("method", "getaccountaddress");
			obj.put("params", list);
			System.out.println(obj);
			OutputStream out = connection.getOutputStream();
			out.write(obj.toString().getBytes());
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println(sb);
			String sbs = sb.toString();
			JSONObject json = JSONObject.fromObject(sbs);
			String adr = (String) json.get("result");

			reader.close();
			// // 断开连接
			connection.disconnect();
			
			return adr;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public double getAccountInfo(
			final String rpcusername,
			final String rpcpassword,
			String rpcpocketadr,
			String rpcport,
			String username
	) throws IOException {
		
		String ADD_URL = ""+rpcpocketadr+":"+rpcport+"/";
		
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(rpcusername, rpcpassword.toCharArray());
			}
		});
		
		HttpURLConnection connection = null;
		try {
			// 创建连接
			URL url = new URL(ADD_URL);
			connection = (HttpURLConnection) url.openConnection();
			
			// 设置http连接属性
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST"); // 可以根据需要 提交
			// GET、POST、DELETE、INPUT等http提供的功能
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			
			// 设置http头 消息
			connection.setRequestProperty("Content-Type", "application/json"); // 设定
			// 请求格式
			// json，也可以设定xml格式的
			// connection.setRequestProperty("Content-Type", "text/xml"); //设定 请求格式
			// xml，
			connection.setRequestProperty("Accept", "application/json");// 设定响应的信息的格式为
			// json，也可以设定xml格式的
			// connection.setRequestProperty("X-Auth-Token","xx");
			// //特定http服务器需要的信息，根据服务器所需要求添加
			connection.connect();
			
			// 添加 请求内容
			
			JSONArray list = new JSONArray();
			list.add(username);
			
			// 构建嵌套的 json数据
			
			JSONObject obj = new JSONObject();
			obj.put("method", "getreceivedbyaccount");
			obj.put("params", list);
			System.out.println(obj);
			OutputStream out = connection.getOutputStream();
			out.write(obj.toString().getBytes());
			out.flush();
			out.close();
			
			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println(sb);
			String sbs = sb.toString();
			JSONObject json = JSONObject.fromObject(sbs);
			double amount = (Double) json.get("result");
			
			reader.close();
			// // 断开连接
			connection.disconnect();
			
			return amount;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	@Test
	public void testapi() throws IOException{
		String adr = this.generalpaymentsadr("fantianmi", "e60858587", "http://127.0.0.1", "8332", "test15");
		System.out.println(adr);
	}
	
	@Test
	public void getAccountInfo() throws IOException{
		double amount = this.getAccountInfo("fantianmi", "e60858587", "http://127.0.0.1", "8334", "test15");
		BigDecimal bamount = new BigDecimal(amount);
		System.out.println(bamount);
	}

}