package ai.iston.dao.mapper;

import ai.iston.model.entity.VocabLevel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
public interface VocabLevelMapper {

    /**
     * 获取词性等级
     * @param wordsBoMap 组装请求数据
     * @return 返回数据
     */
    List<VocabLevel> getVocabLevelInfo(Map<String, Object> wordsBoMap);

}
