package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.dao.IItemDao;
import com.stone.it.micro.rcms.framebase.service.IItemService;
import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framebase.vo.ItemVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PageResult;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * LOOKUP配置
 *
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Named
public class ItemService implements IItemService {

  @Inject
  private IItemDao itemDao;

  @Override
  public PageResult<ClassifyVO> findClassifyPageResult(ClassifyVO classifyVO, PageVO pageVO) {
    return itemDao.findPageResult(classifyVO,pageVO);
  }

  @Override
  public ClassifyVO findClassify(String classifyCode) {
    return itemDao.findClassify(classifyCode);
  }

  @Override
  public int createClassify(ClassifyVO classifyVO) {
    return itemDao.createClassify(classifyVO);
  }

  @Override
  public int updateClassify(String classifyCode, ClassifyVO classifyVO) {
    classifyVO.setClassifyCode(classifyCode);
    return itemDao.updateClassify(classifyVO);
  }

  @Override
  public int deleteClassify(String classifyCode) {
    return itemDao.deleteClassify(classifyCode);
  }

  @Override
  public List<ItemVO> findClassifyItemByCode(String classifyCode) {
    return itemDao.findClassifyItemByCode(classifyCode);
  }

  @Override
  public List<ItemVO> findClassifyItemByCodeLang(String classifyCode, String lang) {
    return itemDao.findClassifyItemByCodeLang(classifyCode,lang);
  }

  @Override
  public int createClassifyItem(ItemVO itemVO) {
    itemVO.setItemId(UUIDUtil.getUuid());
    return itemDao.createClassifyItem(itemVO);
  }

  @Override
  public int updateClassifyItem(String itemId, ItemVO itemVO) {
    itemVO.setItemId(itemId);
    return itemDao.updateClassifyItem(itemVO);
  }

  @Override
  public int deleteClassifyItem(String itemId) {
    return itemDao.deleteClassifyItem(itemId);
  }
}
