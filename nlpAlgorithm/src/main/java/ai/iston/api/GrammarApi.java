package ai.iston.api;

import ai.iston.model.entity.Article;
import ai.iston.model.vo.GrammarInfoVo;
import ai.iston.model.vo.WordInfoVo;
import ai.iston.service.GrammarPerformanceService;
import ai.iston.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/30/15:43
 * @Description:
 */
@RestController
@Api(tags = "NLP语法分析")
@RequestMapping("grammarApi")
@RequiredArgsConstructor
public class GrammarApi {

    private final GrammarPerformanceService grammarPerformanceService;

    @PostMapping(value = "getGrammarInfo")
    @ApiOperation(value = "获取语法信息")
    public Result<GrammarInfoVo> getGrammarInfo(@RequestBody List<Article> articles) {
        GrammarInfoVo result = grammarPerformanceService.getGrammarInfo(articles);
        if(result == null){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }
}
