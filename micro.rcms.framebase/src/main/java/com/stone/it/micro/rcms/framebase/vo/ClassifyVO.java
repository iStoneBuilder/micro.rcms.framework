package com.stone.it.micro.rcms.framebase.vo;

import com.stone.it.micro.rcms.framecore.vo.BaseVO;
import java.util.List;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Data
public class ClassifyVO extends BaseVO {

  private String classifyCode;
  private String classifyName;
  private String description;

  private List<ItemVO> items;

}