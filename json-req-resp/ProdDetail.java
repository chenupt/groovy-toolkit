package com.mycompany.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProdDetail {
	private boolean buyable;
	private ArrayList<DetailItemsItem> detailItems;
	private ProductDto productDto;

	public ProdDetail (JSONObject json){
		if(json != null){
				if(!json.isNull("buyable")) buyable = json.optBoolean("buyable");

				JSONArray array = json.optJSONArray("detailItems");
				detailItems = DetailItemsItem.createWithJsonArray(array);
				

				JSONObject sub = json.optJSONObject("productDto");
				productDto = new ProductDto(sub);

		}
	}

	public static ArrayList<ProdDetail> createWithJsonArray(JSONArray array) {
		if(array != null){
			int len = array.length();
			ArrayList<ProdDetail> list = new ArrayList<ProdDetail>();
			for(int i = 0 ; i < len ; i++){
				JSONObject obj = array.optJSONObject(i);
				ProdDetail oneItem = new ProdDetail(obj);
				list.add(oneItem);
			}
			return list;
		}
		return null;
	}

	public boolean getBuyable() {
		return buyable;
	}
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	public ArrayList<DetailItemsItem> getDetailItems() {
		return detailItems;
	}
	public void setDetailItems(ArrayList<DetailItemsItem> detailItems) {
		this.detailItems = detailItems;
	}
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

}