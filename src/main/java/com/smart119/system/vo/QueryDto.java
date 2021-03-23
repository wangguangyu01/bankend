package com.smart119.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author: zhangshunhua
 * @date: 2021/3/6 11:55
 */
@Data
public class QueryDto<T> {

  /**
   * 每页条数
   */
  @ApiModelProperty(value = "每页条数", name = "limit", example = "0")
  private int limit = 10;

  /**
   * 偏移量
   */
  @ApiModelProperty(value = "偏移量", name = "offset", example = "0")
  private long offset = 0L;
  /**
   * 查询对象
   */
  @ApiModelProperty(value = "查询条件对象", name = "conditions")
  private T conditions;
  /**
   * 当前页码数
   */
  @ApiModelProperty(value = "当前页码数", name = "current", example = "1")
  private Long current;

  @ApiModelProperty(value = "字段排序列表", name = "orderBy")
  private List<String> orderBy;


  public Long getCurrent() {

    if (Objects.isNull(current)) {
      this.current = (offset / limit) + 1;
    }
    return current;
  }
}
