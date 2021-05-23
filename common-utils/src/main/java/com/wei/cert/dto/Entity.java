package com.wei.cert.dto;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class Entity {
    String companyName;
    int effectiveYears;
    String orgName;
    String countryCode;
    String commonName;
    String email;
    String businessCategory;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEffectiveYears() {
        return effectiveYears;
    }

    public void setEffectiveYears(int effectiveYears) {
        this.effectiveYears = effectiveYears;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }
}
