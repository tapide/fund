package com.ni235.core.fund.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * 
 * @author tapide
 * @email tapide@163.com
 * @date 2021-01-13 17:32:31
 */
@Data
@ToString
@TableName("jj_fund_info")
public class JjFundInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 基金代码
	 */
	private String symbol;
	/**
	 * 基金名字
	 */
	private String name;
	/**
	 * 字母缩写
	 */
	private String sname;
	/**
	 * 基金类型：1.股票型。2.混合型。3.债券型。4.指数型。5.QDII-指数。6.混合-FOF
	 */
	private Integer type;

	/**
	 * 单位净值
	 */
	private BigDecimal perNav;
	/**
	 * 日增长率
	 */
	private BigDecimal day;
	/**
	 * 近一周（%）
	 */
	private BigDecimal week;
	/**
	 * 进一个月（%）
	 */
	private BigDecimal oneMonth;
	/**
	 * 近三个月（%）
	 */
	private BigDecimal threeMonth;
	/**
	 * 近六个月（%）
	 */
	private BigDecimal sixMonth;
	/**
	 * 近一年(%)
	 */
	private BigDecimal oneYear;
	/**
	 * 今一年(%)
	 */
	private BigDecimal thisYear;
	/**
	 * 今一年(%)
	 */
	private BigDecimal twoYear;
	/**
	 * 进三年(%)
	 */
	private BigDecimal threeYear;
	/**
	 * 
	 */
	private Date date;

}
