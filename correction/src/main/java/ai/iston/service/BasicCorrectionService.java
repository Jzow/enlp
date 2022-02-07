package ai.iston.service;


import ai.iston.model.dto.CorrectionDTO;
import ai.iston.model.vo.WordLevelVO;
import ai.iston.model.vo.WordsTestVO;

import java.util.List;

/**
 * @author James Zow
 * @version 1.json.0
 * @description: TODO
 * @date 2021/11/23 11:02
 */
public interface BasicCorrectionService {

    /**
     * 获取词汇
     * @param correctionDTO
     * @return 返回组装比对好的词汇数据
     */
    List<WordLevelVO> wordCorrection(CorrectionDTO correctionDTO);

    /**
     * 获取词汇测试
     * @param correctionDTO
     * @return 返回组装比对好的词汇数据
     */
    WordsTestVO wordCorrectionTest(CorrectionDTO correctionDTO);
}
