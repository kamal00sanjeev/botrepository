package com.jira.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.jira.bot.model.BotResponse;

@SuppressWarnings("deprecation")
public class BotResponseUtil {

	private static final String jiraEndPoint = "http://localhost:8181/bot";

	// POST query
	public static String postUserResponse(String endParam) {
		BotResponse botResponse = null;
		try {

			@SuppressWarnings({ "resource" })
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(jiraEndPoint + "/" + endParam);

			StringEntity input = new StringEntity("{\"responseId\":null, \"queryText\":\"Hi\",\"responseText\":null}");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			Gson gson = new Gson();

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println("Output is##" + output);
				botResponse = gson.fromJson(output, BotResponse.class);
				System.out.println(botResponse.getResponseText());
			}
			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return botResponse.getResponseText();
	}

	// GET query
	@SuppressWarnings("resource")
	public static String getUserResponse(String endParam) {
		BotResponse botResponse = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(jiraEndPoint + "/" + endParam);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			Gson gson = new Gson();
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				botResponse = gson.fromJson(output, BotResponse.class);
				System.out.println(botResponse.getResponseText());
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return botResponse.getResponseText();
	}

}
