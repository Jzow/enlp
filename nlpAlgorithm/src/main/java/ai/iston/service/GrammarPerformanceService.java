package ai.iston.service;

import ai.iston.model.entity.Article;
import ai.iston.model.vo.GrammarInfoVo;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/26/19:50
 * @Description:
 */
public interface GrammarPerformanceService {

    GrammarInfoVo getGrammarInfo(List<Article> articleList);
}
