package com.stone.it.rcms.base.dao;


import com.stone.it.rcms.base.vo.I18nVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Desc
 */
public interface II18nDao {

  PageResult<I18nVO> findPageResult(I18nVO i18nVO,
     PageVO pageVO);

  List<I18nVO> findListByLanguage(@Param("lang")String lang);

  I18nVO findI18nById(@Param("i18nId")String i18nId);

  int createI18n(I18nVO i18nVO);

  int updateI18n(I18nVO i18nVO);

  int deleteI18n(@Param("i18nId") String i18nId);

}
