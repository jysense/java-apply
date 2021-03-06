package com.shop.order.dao.mapper;

import com.shop.order.model.pojo.OrderStatuChange;
import com.shop.order.model.pojo.OrderStatuChangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface OrderStatuChangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int countByExample(OrderStatuChangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int deleteByExample(OrderStatuChangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    @Delete({
        "delete from order_statu_change",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    @Insert({
        "insert into order_statu_change (order_no, pre_statu, ",
        "cur_statu, operator_id, ",
        "operator_name, operate_desc, ",
        "create_time, modified_time)",
        "values (#{orderNo,jdbcType=VARCHAR}, #{preStatu,jdbcType=INTEGER}, ",
        "#{curStatu,jdbcType=INTEGER}, #{operatorId,jdbcType=BIGINT}, ",
        "#{operatorName,jdbcType=VARCHAR}, #{operateDesc,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(OrderStatuChange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int insertSelective(OrderStatuChange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    List<OrderStatuChange> selectByExample(OrderStatuChangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    @Select({
        "select",
        "id, order_no, pre_statu, cur_statu, operator_id, operator_name, operate_desc, ",
        "create_time, modified_time",
        "from order_statu_change",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    OrderStatuChange selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int updateByExampleSelective(@Param("record") OrderStatuChange record, @Param("example") OrderStatuChangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int updateByExample(@Param("record") OrderStatuChange record, @Param("example") OrderStatuChangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    int updateByPrimaryKeySelective(OrderStatuChange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_statu_change
     *
     * @mbggenerated Thu Feb 08 17:16:06 CST 2018
     */
    @Update({
        "update order_statu_change",
        "set order_no = #{orderNo,jdbcType=VARCHAR},",
          "pre_statu = #{preStatu,jdbcType=INTEGER},",
          "cur_statu = #{curStatu,jdbcType=INTEGER},",
          "operator_id = #{operatorId,jdbcType=BIGINT},",
          "operator_name = #{operatorName,jdbcType=VARCHAR},",
          "operate_desc = #{operateDesc,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OrderStatuChange record);
}