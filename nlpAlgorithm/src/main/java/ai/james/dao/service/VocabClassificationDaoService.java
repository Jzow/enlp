package ai.james.dao.service;

import ai.james.model.entity.VocabClassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:57
 * @Description:
 */
public interface VocabClassificationDaoService extends IService<VocabClassification> {

    /**
     * 获取词汇分类数据
     * @param type 词性
     * @param wordList 词汇数据集合
     * @return 返回分类的数据
     */
    List<VocabClassification> getVocalClassificationList(String type, List<String> wordList);
}
