package com.shopstat.model.vo.stat;

import com.util.base.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 维度类
 */
public class Dimen implements Serializable {

    //维度名
    private String dimenName;

    //列名
    private String columnName;

    //维度类型
    private Class clazz;

    //维护选项值列表
    private List<String> options= new LinkedList<String>();

    //选项值中代表全部的选项值
    private String totalOption;

    public Dimen()
    {

    }

    public Dimen(String dimenName,Class clazz, List<String> options,String totalOption)
    {
        this.dimenName=dimenName;
        this.columnName= StringUtil.field2Col(dimenName);
        this.clazz=clazz;
        this.options=options;
        this.totalOption=totalOption;
    }

    public String getDimenName() {
        return dimenName;
    }

    public void setDimenName(String dimenName) {
        this.dimenName = dimenName;
        this.columnName= StringUtil.field2Col(dimenName);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getTotalOption() {
        return totalOption;
    }

    public void setTotalOption(String totalOption) {
        this.totalOption = totalOption;
    }

    public void addOption(String option)
    {
        if(options==null)
        {
            options= new ArrayList<String>();
        }
        options.add(option);
    }

    public String getGroupFlagment(int total)
    {
        //0表示全部
        if(total==0)
        {
            return "";
        }
        return columnName;
    }

    public String getQueryFlagMent(int total)
    {
        if(total==0)
        {
            if(Number.class.isAssignableFrom(clazz))
            {

                return totalOption+" as "+columnName;
            }
            return "'"+totalOption+"' as " +columnName;
        }
        return columnName;
    }

    public String getCndFlagMent()
    {
        if(Number.class.isAssignableFrom(clazz))
        {
            return "("+columnName+"<>"+totalOption+" or "+columnName+"  is null )";
        }
        return  columnName+"!='" +totalOption+"'";
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
