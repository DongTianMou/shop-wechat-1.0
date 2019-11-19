
package com.dongtian.shopcommon.entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Timestamp create_time;
	/**
	 * 修改时间
	 */
	private Timestamp update_time;

}
