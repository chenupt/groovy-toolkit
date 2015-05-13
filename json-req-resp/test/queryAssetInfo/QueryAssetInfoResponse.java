package com.mycompany.requests;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryAssetInfoResponse extends BaseResponse {
	private AssetInfo AssetInfo;

	public QueryAssetInfoResponse (String jsonStr){
		super(jsonStr);
		if(!TextUtils.isEmpty(jsonStr)){
			try{
				JSONObject json = new JSONObject(jsonStr);

				JSONObject sub = json.optJSONObject("result");
				AssetInfo = new AssetInfo(sub);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public AssetInfo getAssetInfo() {
		return AssetInfo;
	}
	public void setAssetInfo(AssetInfo AssetInfo) {
		this.AssetInfo = AssetInfo;
	}

}
