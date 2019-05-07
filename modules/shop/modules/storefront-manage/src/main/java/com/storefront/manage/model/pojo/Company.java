package com.storefront.manage.model.pojo;

import com.shop.base.model.BasePojo;
import java.util.Date;

public class Company extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.uscc
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String uscc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Integer isJingying;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.website
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String provinceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String provinceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String cityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String areaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String detailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column company.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.id
     *
     * @return the value of company.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.id
     *
     * @param id the value for company.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.name
     *
     * @return the value of company.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.name
     *
     * @param name the value for company.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.info
     *
     * @return the value of company.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.info
     *
     * @param info the value for company.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.uscc
     *
     * @return the value of company.uscc
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getUscc() {
        return uscc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.uscc
     *
     * @param uscc the value for company.uscc
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setUscc(String uscc) {
        this.uscc = uscc == null ? null : uscc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.is_jingying
     *
     * @return the value of company.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Integer getIsJingying() {
        return isJingying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.is_jingying
     *
     * @param isJingying the value for company.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setIsJingying(Integer isJingying) {
        this.isJingying = isJingying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.start_time
     *
     * @return the value of company.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.start_time
     *
     * @param startTime the value for company.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.website
     *
     * @return the value of company.website
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.website
     *
     * @param website the value for company.website
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.province_code
     *
     * @return the value of company.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.province_code
     *
     * @param provinceCode the value for company.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.province_name
     *
     * @return the value of company.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.province_name
     *
     * @param provinceName the value for company.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.city_code
     *
     * @return the value of company.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.city_code
     *
     * @param cityCode the value for company.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.city_name
     *
     * @return the value of company.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.city_name
     *
     * @param cityName the value for company.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.area_code
     *
     * @return the value of company.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.area_code
     *
     * @param areaCode the value for company.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.area_name
     *
     * @return the value of company.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.area_name
     *
     * @param areaName the value for company.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.detail_address
     *
     * @return the value of company.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.detail_address
     *
     * @param detailAddress the value for company.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.create_time
     *
     * @return the value of company.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.create_time
     *
     * @param createTime the value for company.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column company.modified_time
     *
     * @return the value of company.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column company.modified_time
     *
     * @param modifiedTime the value for company.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}