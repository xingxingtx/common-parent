package com.wei.utils.encryptdecrypt.entity;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月13日
 */
public class CertEntity {
    private String companyName;
    private int effectiveYears;
    private String orgName;
    private String countyCode;
    private String commonName;
    private String email;

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

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
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
}
