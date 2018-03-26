package com.dashboard;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class GrubHubLoginHelper {

	private Map<String, Token> tokenMap = new HashMap<String, Token>();
	static String datePattern = "yyyy-MM-dd";
	static TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");

	public List<GrubHubModel> fetchTransaction(String token, String startDate, String endDate) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(String.format(
				"https://api-gtm.grubhub.com/merchant/financials/v0/269205/transactions?endDate=%s&startDate=%s",
				endDate, startDate)).get().addHeader("accept", "application/json")
				.addHeader("accept-language", "en-US,en;q=0.9").addHeader("authorization", "Bearer " + token)
				.addHeader("cache-control", "no-cache").build();
		List<GrubHubModel> orders = new ArrayList<>();
		try {
			Response response = client.newCall(request).execute();

			if (response.isSuccessful()) {
				String resp_body = response.body().string();
				JSONObject myObject;
				try {
					myObject = new JSONObject(resp_body);
					JSONArray jArray = myObject.getJSONArray("269205");

					jArray.forEach((j) -> {
						JSONObject jsonObject = (JSONObject) j;
						/*
						 * System.out.println("Customer Id: " + jsonObject.get("customer_id") + "===" +
						 * "Order Number : " + jsonObject.get("order_number") + "===" + "Total : " +
						 * jsonObject.get("total") + "===" + "NetAmount : " +
						 * jsonObject.get("net_amount") + "====" + "display_net_advertising_fee : " +
						 * jsonObject.get("display_net_advertising_fee") + "====" +
						 * "display_net_processing_fee : " +
						 * jsonObject.get("display_net_processing_fee"));
						 */
						GrubHubModel grubHubModel = new GrubHubModel(jsonObject.get("customer_id").toString(),
								jsonObject.get("order_number").toString(),
								jsonObject.get("restaurant_total").toString().replace("$", "").replace("-", "0.00")
										.trim(),
								jsonObject.get("net_amount").toString().replace("$", "").replace("-", "0.00").trim(),
								"0.00", "0.00");
						orders.add(grubHubModel);
					});

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}

	public String getAccessToken(String email, String password) {

		if (tokenMap.containsKey(email)
				&& tokenMap.get(email).getAccessTokenExpiry() > Calendar.getInstance().getTime().getTime()) {
			System.out.println("Using existing token from cache!!");
			return tokenMap.get(email).getAccessToken();
		}

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,
				"{\"brand\":\"GRUBHUB\",\"client_id\":\"QhYEEP59t2QhjFCMvYhsvdN655YPsNCWzggzM8VoA3FNUhSQbSrixtNhLfCK8RVf\",\"email\":\""
						+ email + "\",\"password\":\"" + password + "\"}");
		Request request = new Request.Builder().url("https://api-gtm.grubhub.com/auth").post(body)
				.addHeader("host", "api-gtm.grubhub.com").addHeader("connection", "keep-alive")
				.addHeader("content-length", "158").addHeader("accept", "application/json")
				.addHeader("origin", "https://restaurant.grubhub.com")
				.addHeader("user-agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
				.addHeader("content-type", "application/json")
				.addHeader("referer", "https://restaurant.grubhub.com/login")
				.addHeader("accept-encoding", "gzip, deflate, br").addHeader("accept-language", "en-US,en;q=0.9")
				.addHeader("cache-control", "no-cache").build();

		Response response = null;
		String resp_body = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if (response.isSuccessful()) {
			try {
				resp_body = response.body().string();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			try {
				JSONObject myObject = new JSONObject(resp_body);
				JSONObject sessionJson = (JSONObject) myObject.get("session_handle");
				String access_token = (String) sessionJson.get("access_token");
				Long session_ExpireTime = (Long) sessionJson.get("token_expire_time"); // Store this to DB
				System.out.println("Got Access Token: " + access_token);
				tokenMap.put(email, new Token(email, access_token, session_ExpireTime));
				return access_token;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getFormattedDate(Date date, String pattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setTimeZone(tzInAmerica);
		String date1 = simpleDateFormat.format(date);
		System.out.println(date1);
		return date1;
	}

	public static void main(String[] s) {
		// GrubHubLoginHelper helper = new GrubHubLoginHelper();

		System.out.println(getCompany());
	}

	public static Company getCompany() {
		GrubHubLoginHelper grubHubLoginHelper = new GrubHubLoginHelper();
		String access_token = grubHubLoginHelper.getAccessToken("rutgerskbg@gmail.com", "grubhubsucks123");

		List<GrubHubModel> allOrders = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.setTimeZone(tzInAmerica);
		String today = getFormattedDate(cal.getTime(), datePattern);
		cal.add(Calendar.DATE, -1);
		String yesterday = getFormattedDate(cal.getTime(), datePattern);
		cal.add(Calendar.MONTH, -1);
		String dateMonthAgo = getFormattedDate(cal.getTime(), datePattern);

		// Get data of last 30 days first
		if (access_token != null) {
			// grubHubLoginHelper.fetchTransaction(access_token, dateMonthAgo, today);
			allOrders.addAll(grubHubLoginHelper.fetchTransaction(access_token, dateMonthAgo, today));
		}

		// Get data of last periodically days first
		// while(true) {
		access_token = grubHubLoginHelper.getAccessToken("rutgerskbg@gmail.com", "grubhubsucks123");
		if (access_token == null) {
			System.out.println("Unable to get access token, retry later!!");
		} else {
			List<GrubHubModel> orders = grubHubLoginHelper.fetchTransaction(access_token, yesterday, today);
			System.out.println("he" + orders.size());
			allOrders.addAll(grubHubLoginHelper.fetchTransaction(access_token, yesterday, today));
			// /}
			/*
			 * try { Thread.sleep(1000*1000); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
		}
		System.out.println(allOrders.size());
		double totalRevenue = allOrders.stream().mapToDouble(a -> {
			if (! a.getGrossTotal().equals("null"))
				return Double.parseDouble(a.getGrossTotal());
			else
				return 0.00;

		}).sum();
		double totalProfit = allOrders.stream().mapToDouble(a -> Double.parseDouble(a.getNetTotal())).sum();
		double totalCommision = totalRevenue - totalProfit;
		System.out.println("size:" + allOrders.size());
		return new Company("G1", "Grubhub", totalRevenue, totalCommision, totalProfit);
	}

}