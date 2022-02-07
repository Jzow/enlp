package ai.iston.dao.service.impl;

import ai.iston.dao.mapper.VocabClassificationMapper;
import ai.iston.dao.mapper.VocabLevelMapper;
import ai.iston.dao.service.VocabClassificationDaoService;
import ai.iston.model.entity.VocabClassification;
import ai.iston.model.entity.VocabLevel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/13:58
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class VocabClassificationDaoServiceImpl extends ServiceImpl<VocabClassificationMapper, VocabClassification> implements VocabClassificationDaoService {

    private final VocabClassificationMapper vocabClassificationMapper;

    @Override
    public List<VocabClassification> getVocalClassificationList(String type, List<String> wordList) {
        return lambdaQuery()
                .eq(StringUtils.hasText(type), VocabClassification::getType, type)
                .in(!wordList.isEmpty(), VocabClassification::getWord, wordList)
                .list();

    }
}
