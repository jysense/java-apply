package com.micro.store.dao.mapper;

import com.micro.store.model.pojo.Goods;
import com.micro.store.model.pojo.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface GoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int countByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int deleteByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    @Delete({
        "delete from goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    @Insert({
        "insert into goods (name, price, ",
        "stock_count, lock_stock_cnt, ",
        "create_time, modified_time)",
        "values (#{name,jdbcType=VARCHAR}, #{price,jdbcType=BIGINT}, ",
        "#{stockCount,jdbcType=INTEGER}, #{lockStockCnt,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifiedTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int insertSelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    List<Goods> selectByExample(GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    @Select({
        "select",
        "id, name, price, stock_count, lock_stock_cnt, create_time, modified_time",
        "from goods",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Goods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbggenerated Thu May 30 16:09:12 CST 2019
     */
    @Update({
        "update goods",
        "set name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=BIGINT},",
          "stock_count = #{stockCount,jdbcType=INTEGER},",
          "lock_stock_cnt = #{lockStockCnt,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modified_time = #{modifiedTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Goods record);
}