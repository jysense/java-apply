package com.storefront.manage.model.pojo;

import com.shop.base.model.BasePojo;
import java.util.Date;

public class Store extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.is_business_mark
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Integer isBusinessMark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.first_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String firstIdstryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.first_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String firstIdstryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.direct_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String directIdstryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.direct_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String directIdstryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Integer isJingying;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.is_jiameng
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Integer isJiameng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.brand_id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Long brandId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.brand_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String brandName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.end_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String provinceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String provinceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String cityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String areaCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String areaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.position_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String positionName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.position_type
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Integer positionType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.street_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String streetName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private String detailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column store.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.id
     *
     * @return the value of store.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.id
     *
     * @param id the value for store.id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.name
     *
     * @return the value of store.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.name
     *
     * @param name the value for store.name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.info
     *
     * @return the value of store.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.info
     *
     * @param info the value for store.info
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.is_business_mark
     *
     * @return the value of store.is_business_mark
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Integer getIsBusinessMark() {
        return isBusinessMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.is_business_mark
     *
     * @param isBusinessMark the value for store.is_business_mark
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setIsBusinessMark(Integer isBusinessMark) {
        this.isBusinessMark = isBusinessMark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.first_idstry_code
     *
     * @return the value of store.first_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getFirstIdstryCode() {
        return firstIdstryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.first_idstry_code
     *
     * @param firstIdstryCode the value for store.first_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setFirstIdstryCode(String firstIdstryCode) {
        this.firstIdstryCode = firstIdstryCode == null ? null : firstIdstryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.first_idstry_name
     *
     * @return the value of store.first_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getFirstIdstryName() {
        return firstIdstryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.first_idstry_name
     *
     * @param firstIdstryName the value for store.first_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setFirstIdstryName(String firstIdstryName) {
        this.firstIdstryName = firstIdstryName == null ? null : firstIdstryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.direct_idstry_code
     *
     * @return the value of store.direct_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getDirectIdstryCode() {
        return directIdstryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.direct_idstry_code
     *
     * @param directIdstryCode the value for store.direct_idstry_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setDirectIdstryCode(String directIdstryCode) {
        this.directIdstryCode = directIdstryCode == null ? null : directIdstryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.direct_idstry_name
     *
     * @return the value of store.direct_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getDirectIdstryName() {
        return directIdstryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.direct_idstry_name
     *
     * @param directIdstryName the value for store.direct_idstry_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setDirectIdstryName(String directIdstryName) {
        this.directIdstryName = directIdstryName == null ? null : directIdstryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.is_jingying
     *
     * @return the value of store.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Integer getIsJingying() {
        return isJingying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.is_jingying
     *
     * @param isJingying the value for store.is_jingying
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setIsJingying(Integer isJingying) {
        this.isJingying = isJingying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.is_jiameng
     *
     * @return the value of store.is_jiameng
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Integer getIsJiameng() {
        return isJiameng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.is_jiameng
     *
     * @param isJiameng the value for store.is_jiameng
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setIsJiameng(Integer isJiameng) {
        this.isJiameng = isJiameng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.brand_id
     *
     * @return the value of store.brand_id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.brand_id
     *
     * @param brandId the value for store.brand_id
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.brand_name
     *
     * @return the value of store.brand_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.brand_name
     *
     * @param brandName the value for store.brand_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.start_time
     *
     * @return the value of store.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.start_time
     *
     * @param startTime the value for store.start_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.end_time
     *
     * @return the value of store.end_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.end_time
     *
     * @param endTime the value for store.end_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.province_code
     *
     * @return the value of store.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.province_code
     *
     * @param provinceCode the value for store.province_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.province_name
     *
     * @return the value of store.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.province_name
     *
     * @param provinceName the value for store.province_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.city_code
     *
     * @return the value of store.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.city_code
     *
     * @param cityCode the value for store.city_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.city_name
     *
     * @return the value of store.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.city_name
     *
     * @param cityName the value for store.city_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.area_code
     *
     * @return the value of store.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.area_code
     *
     * @param areaCode the value for store.area_code
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.area_name
     *
     * @return the value of store.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.area_name
     *
     * @param areaName the value for store.area_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.position_name
     *
     * @return the value of store.position_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.position_name
     *
     * @param positionName the value for store.position_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.position_type
     *
     * @return the value of store.position_type
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Integer getPositionType() {
        return positionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.position_type
     *
     * @param positionType the value for store.position_type
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.street_name
     *
     * @return the value of store.street_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.street_name
     *
     * @param streetName the value for store.street_name
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.detail_address
     *
     * @return the value of store.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.detail_address
     *
     * @param detailAddress the value for store.detail_address
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.create_time
     *
     * @return the value of store.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.create_time
     *
     * @param createTime the value for store.create_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column store.modified_time
     *
     * @return the value of store.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column store.modified_time
     *
     * @param modifiedTime the value for store.modified_time
     *
     * @mbggenerated Tue May 07 16:01:34 CST 2019
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}