package com.storefront.manage.model.pojo.rbac;

import com.shop.base.model.BasePojo;
import java.util.Date;

public class Role extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.code
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.note
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private String note;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.create_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.modified_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.code
     *
     * @return the value of role.code
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.code
     *
     * @param code the value for role.code
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.note
     *
     * @return the value of role.note
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.note
     *
     * @param note the value for role.note
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.create_time
     *
     * @return the value of role.create_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.create_time
     *
     * @param createTime the value for role.create_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.modified_time
     *
     * @return the value of role.modified_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.modified_time
     *
     * @param modifiedTime the value for role.modified_time
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}