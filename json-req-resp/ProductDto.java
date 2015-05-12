package com.mycompany.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDto {
	private int investLowLimit;
	private String logoUrl;
	private int productDays;
	private String productDesc;
	private String productId;
	private String productName;
	private String productType;
	private String productTypeId;
	private double profitRate;
	private String rateName;
	private String wanfenProfit;

	public ProductDto (JSONObject json){
		if(json != null){
				if(!json.isNull("investLowLimit")) investLowLimit = json.optInt("investLowLimit");
				if(!json.isNull("logoUrl")) logoUrl = json.optString("logoUrl");
				if(!json.isNull("productDays")) productDays = json.optInt("productDays");
				if(!json.isNull("productDesc")) productDesc = json.optString("productDesc");
				if(!json.isNull("productId")) productId = json.optString("productId");
				if(!json.isNull("productName")) productName = json.optString("productName");
				if(!json.isNull("productType")) productType = json.optString("productType");
				if(!json.isNull("productTypeId")) productTypeId = json.optString("productTypeId");
				if(!json.isNull("profitRate")) profitRate = json.optDouble("profitRate");
				if(!json.isNull("rateName")) rateName = json.optString("rateName");
				if(!json.isNull("wanfenProfit")) wanfenProfit = json.optString("wanfenProfit");
		}
	}

	public static ArrayList<ProductDto> createWithJsonArray(JSONArray array) {
		if(array != null){
			int len = array.length();
			ArrayList<ProductDto> list = new ArrayList<ProductDto>();
			for(int i = 0 ; i < len ; i++){
				JSONObject obj = array.optJSONObject(i);
				ProductDto oneItem = new ProductDto(obj);
				list.add(oneItem);
			}
			return list;
		}
		return null;
	}

	public int getInvestLowLimit() {
		return investLowLimit;
	}
	public void setInvestLowLimit(int investLowLimit) {
		this.investLowLimit = investLowLimit;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public int getProductDays() {
		return productDays;
	}
	public void setProductDays(int productDays) {
		this.productDays = productDays;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public double getProfitRate() {
		return profitRate;
	}
	public void setProfitRate(double profitRate) {
		this.profitRate = profitRate;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public String getWanfenProfit() {
		return wanfenProfit;
	}
	public void setWanfenProfit(String wanfenProfit) {
		this.wanfenProfit = wanfenProfit;
	}

}