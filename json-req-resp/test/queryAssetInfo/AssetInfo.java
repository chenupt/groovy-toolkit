package com.mycompany.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AssetInfo {
	private String accountId;
	private double totalAsset;
	private double totalDayProfit;
	private double totalMonthProfit;
	private double totalProfit;
	private double totalWeekProfit;

	public AssetInfo (JSONObject json){
		if(json != null){
				if(!json.isNull("accountId")) accountId = json.optString("accountId");
				if(!json.isNull("totalAsset")) totalAsset = json.optDouble("totalAsset");
				if(!json.isNull("totalDayProfit")) totalDayProfit = json.optDouble("totalDayProfit");
				if(!json.isNull("totalMonthProfit")) totalMonthProfit = json.optDouble("totalMonthProfit");
				if(!json.isNull("totalProfit")) totalProfit = json.optDouble("totalProfit");
				if(!json.isNull("totalWeekProfit")) totalWeekProfit = json.optDouble("totalWeekProfit");
		}
	}

	public static ArrayList<AssetInfo> createWithJsonArray(JSONArray array) {
		if(array != null){
			int len = array.length();
			ArrayList<AssetInfo> list = new ArrayList<AssetInfo>();
			for(int i = 0 ; i < len ; i++){
				JSONObject obj = array.optJSONObject(i);
				AssetInfo oneItem = new AssetInfo(obj);
				list.add(oneItem);
			}
			return list;
		}
		return null;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public double getTotalDayProfit() {
		return totalDayProfit;
	}
	public void setTotalDayProfit(double totalDayProfit) {
		this.totalDayProfit = totalDayProfit;
	}
	public double getTotalMonthProfit() {
		return totalMonthProfit;
	}
	public void setTotalMonthProfit(double totalMonthProfit) {
		this.totalMonthProfit = totalMonthProfit;
	}
	public double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}
	public double getTotalWeekProfit() {
		return totalWeekProfit;
	}
	public void setTotalWeekProfit(double totalWeekProfit) {
		this.totalWeekProfit = totalWeekProfit;
	}

}