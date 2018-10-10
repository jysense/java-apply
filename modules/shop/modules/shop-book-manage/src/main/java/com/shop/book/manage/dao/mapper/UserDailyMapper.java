package com.shop.book.manage.dao.mapper;

import com.shop.book.manage.model.pojo.UserDaily;
import com.shop.book.manage.model.pojo.UserDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserDailyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int countByExample(UserDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int deleteByExample(UserDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    @Delete({
        "delete from user_daily",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    @Insert({
        "insert into user_daily (user_id, mobile, ",
        "score, day_date, create_time, ",
        "modified_time)",
        "values (#{userId,jdbcType=BIGINT}, #{mobile,jdbcType=VARCHAR}, ",
        "#{score,jdbcType=INTEGER}, #{dayDate,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifiedTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int insertSelective(UserDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    List<UserDaily> selectByExample(UserDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    @Select({
        "select",
        "id, user_id, mobile, score, day_date, create_time, modified_time",
        "from user_daily",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    UserDaily selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int updateByExampleSelective(@Param("record") UserDaily record, @Param("example") UserDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int updateByExample(@Param("record") UserDaily record, @Param("example") UserDailyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    int updateByPrimaryKeySelective(UserDaily record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_daily
     *
     * @mbggenerated Sun Dec 17 20:46:09 CST 2017
     */
    @Update({
        "update user_daily",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER},",
          "day_date = #{dayDate,jdbcType=DATE},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserDaily record);
}