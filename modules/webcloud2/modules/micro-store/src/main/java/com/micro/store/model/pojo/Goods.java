package com.micro.store.model.pojo;

import com.micro.store.model.BasePojo;
import java.util.Date;

public class Goods extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.id
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.name
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.price
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Long price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.stock_count
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Integer stockCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.lock_stock_cnt
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Integer lockStockCnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.create_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.modified_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.id
     *
     * @return the value of goods.id
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.id
     *
     * @param id the value for goods.id
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.name
     *
     * @return the value of goods.name
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.name
     *
     * @param name the value for goods.name
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.price
     *
     * @return the value of goods.price
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Long getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.price
     *
     * @param price the value for goods.price
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.stock_count
     *
     * @return the value of goods.stock_count
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.stock_count
     *
     * @param stockCount the value for goods.stock_count
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.lock_stock_cnt
     *
     * @return the value of goods.lock_stock_cnt
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Integer getLockStockCnt() {
        return lockStockCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.lock_stock_cnt
     *
     * @param lockStockCnt the value for goods.lock_stock_cnt
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setLockStockCnt(Integer lockStockCnt) {
        this.lockStockCnt = lockStockCnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.create_time
     *
     * @return the value of goods.create_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.create_time
     *
     * @param createTime the value for goods.create_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.modified_time
     *
     * @return the value of goods.modified_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.modified_time
     *
     * @param modifiedTime the value for goods.modified_time
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}