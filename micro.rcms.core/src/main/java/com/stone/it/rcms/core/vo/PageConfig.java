package com.stone.it.rcms.core.vo;

import java.io.Serializable;

/**
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public class PageConfig implements Serializable {

  public static final PageConfig DEFAULT = new PageConfig();

  private static final long serialVersionUID = 8600747365376219368L;
  /**
   * 默认分页大小
   */
  private final  Integer defaultPage = 15;
  /**
   * 最大分页大小
   */
  private final Integer maxPageSize = 3500;

  public Integer getDefaultPage() {
    return defaultPage;
  }

  public Integer getMaxPageSize() {
    return maxPageSize;
  }
}
