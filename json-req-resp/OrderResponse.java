package com.mycompany.requests;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderResponse extends BaseResponse {
	private String appRetcodeEnum;
	private ProdDetail prodDetail;
	private String retcode;
	private String retdesc;

	public OrderResponse (String jsonStr){
		super(jsonStr);
		if(!TextUtils.isEmpty(jsonStr)){
			try{
				JSONObject json = new JSONObject(jsonStr);
				if(!json.isNull("appRetcodeEnum")) appRetcodeEnum = json.optString("appRetcodeEnum");

				JSONObject sub = json.optJSONObject("prodDetail");
				prodDetail = new ProdDetail(sub);

				if(!json.isNull("retcode")) retcode = json.optString("retcode");
				if(!json.isNull("retdesc")) retdesc = json.optString("retdesc");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public String getAppRetcodeEnum() {
		return appRetcodeEnum;
	}
	public void setAppRetcodeEnum(String appRetcodeEnum) {
		this.appRetcodeEnum = appRetcodeEnum;
	}
	public ProdDetail getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(ProdDetail prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetdesc() {
		return retdesc;
	}
	public void setRetdesc(String retdesc) {
		this.retdesc = retdesc;
	}

}
