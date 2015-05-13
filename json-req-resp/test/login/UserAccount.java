package com.mycompany.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserAccount {
	private String accountId;
	private String address;
	private String anonymousAccountId;
	private int bankCardNum;
	private String cityCode;
	private String createTime;
	private String guoHuaCityStr;
	private String guoHuaProvinceStr;
	private boolean hasRiskAssessment;
	private String identityNo;
	private String innerId;
	private String ip;
	private String lastLoginTime;
	private String mobilePhone;
	private String openTime;
	private boolean opened;
	private String outAccountId;
	private String outAccountType;
	private String outId;
	private String provinceCode;
	private String riskAssessStyle;
	private String showAmount;
	private String status;
	private String syncFlag;
	private String trueName;
	private String updateTime;
	private String verifyTime;

	public UserAccount (JSONObject json){
		if(json != null){
				if(!json.isNull("accountId")) accountId = json.optString("accountId");
				if(!json.isNull("address")) address = json.optString("address");
				if(!json.isNull("anonymousAccountId")) anonymousAccountId = json.optString("anonymousAccountId");
				if(!json.isNull("bankCardNum")) bankCardNum = json.optInt("bankCardNum");
				if(!json.isNull("cityCode")) cityCode = json.optString("cityCode");
				if(!json.isNull("createTime")) createTime = json.optString("createTime");
				if(!json.isNull("guoHuaCityStr")) guoHuaCityStr = json.optString("guoHuaCityStr");
				if(!json.isNull("guoHuaProvinceStr")) guoHuaProvinceStr = json.optString("guoHuaProvinceStr");
				if(!json.isNull("hasRiskAssessment")) hasRiskAssessment = json.optBoolean("hasRiskAssessment");
				if(!json.isNull("identityNo")) identityNo = json.optString("identityNo");
				if(!json.isNull("innerId")) innerId = json.optString("innerId");
				if(!json.isNull("ip")) ip = json.optString("ip");
				if(!json.isNull("lastLoginTime")) lastLoginTime = json.optString("lastLoginTime");
				if(!json.isNull("mobilePhone")) mobilePhone = json.optString("mobilePhone");
				if(!json.isNull("openTime")) openTime = json.optString("openTime");
				if(!json.isNull("opened")) opened = json.optBoolean("opened");
				if(!json.isNull("outAccountId")) outAccountId = json.optString("outAccountId");
				if(!json.isNull("outAccountType")) outAccountType = json.optString("outAccountType");
				if(!json.isNull("outId")) outId = json.optString("outId");
				if(!json.isNull("provinceCode")) provinceCode = json.optString("provinceCode");
				if(!json.isNull("riskAssessStyle")) riskAssessStyle = json.optString("riskAssessStyle");
				if(!json.isNull("showAmount")) showAmount = json.optString("showAmount");
				if(!json.isNull("status")) status = json.optString("status");
				if(!json.isNull("syncFlag")) syncFlag = json.optString("syncFlag");
				if(!json.isNull("trueName")) trueName = json.optString("trueName");
				if(!json.isNull("updateTime")) updateTime = json.optString("updateTime");
				if(!json.isNull("verifyTime")) verifyTime = json.optString("verifyTime");
		}
	}

	public static ArrayList<UserAccount> createWithJsonArray(JSONArray array) {
		if(array != null){
			int len = array.length();
			ArrayList<UserAccount> list = new ArrayList<UserAccount>();
			for(int i = 0 ; i < len ; i++){
				JSONObject obj = array.optJSONObject(i);
				UserAccount oneItem = new UserAccount(obj);
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAnonymousAccountId() {
		return anonymousAccountId;
	}
	public void setAnonymousAccountId(String anonymousAccountId) {
		this.anonymousAccountId = anonymousAccountId;
	}
	public int getBankCardNum() {
		return bankCardNum;
	}
	public void setBankCardNum(int bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getGuoHuaCityStr() {
		return guoHuaCityStr;
	}
	public void setGuoHuaCityStr(String guoHuaCityStr) {
		this.guoHuaCityStr = guoHuaCityStr;
	}
	public String getGuoHuaProvinceStr() {
		return guoHuaProvinceStr;
	}
	public void setGuoHuaProvinceStr(String guoHuaProvinceStr) {
		this.guoHuaProvinceStr = guoHuaProvinceStr;
	}
	public boolean getHasRiskAssessment() {
		return hasRiskAssessment;
	}
	public void setHasRiskAssessment(boolean hasRiskAssessment) {
		this.hasRiskAssessment = hasRiskAssessment;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getInnerId() {
		return innerId;
	}
	public void setInnerId(String innerId) {
		this.innerId = innerId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public boolean getOpened() {
		return opened;
	}
	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	public String getOutAccountId() {
		return outAccountId;
	}
	public void setOutAccountId(String outAccountId) {
		this.outAccountId = outAccountId;
	}
	public String getOutAccountType() {
		return outAccountType;
	}
	public void setOutAccountType(String outAccountType) {
		this.outAccountType = outAccountType;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getRiskAssessStyle() {
		return riskAssessStyle;
	}
	public void setRiskAssessStyle(String riskAssessStyle) {
		this.riskAssessStyle = riskAssessStyle;
	}
	public String getShowAmount() {
		return showAmount;
	}
	public void setShowAmount(String showAmount) {
		this.showAmount = showAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

}