package com.mycompany.requests;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginResponse extends BaseResponse {
	private UserAccount UserAccount;

	public LoginResponse (String jsonStr){
		super(jsonStr);
		if(!TextUtils.isEmpty(jsonStr)){
			try{
				JSONObject json = new JSONObject(jsonStr);

				JSONObject sub = json.optJSONObject("result");
				UserAccount = new UserAccount(sub);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public UserAccount getUserAccount() {
		return UserAccount;
	}
	public void setUserAccount(UserAccount UserAccount) {
		this.UserAccount = UserAccount;
	}

}
