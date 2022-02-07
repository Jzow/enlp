package ai.iston.api;

import ai.iston.model.entity.Article;
import ai.iston.model.modelmapper.WordModelMapper;
import ai.iston.model.vo.ParseWordVo;
import ai.iston.model.vo.SentenceWordVo;
import ai.iston.model.vo.WordInfoVo;
import ai.iston.model.vo.WordVo;
import ai.iston.service.WordProcessingService;
import ai.iston.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/19/0:01
 * @Description:
 */
@RestController
@Api(tags = "NLP词汇分析")
@RequestMapping("wordApi")
@RequiredArgsConstructor
public class WordApi {

    private final WordProcessingService wordProcessingService;

    @PostMapping(value = "getWordByPartOfSpeech")
    @ApiOperation(value = "获取指定词性的单词")
    public Result<List<String>> getWordByPartOfSpeech(String article, String part) {
        List<String> result = wordProcessingService.getWordByPartOfSpeech(article, part);
        if(result.isEmpty()){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }

    @PostMapping(value = "getWordByWordParsing")
    @ApiOperation(value = "获取文章所有单词&还原词的词性")
    public Result<List<WordVo>> getWordByWordParsing(String article) {
        List<WordVo> result = WordModelMapper.INSTANCE.convertBosToVos(wordProcessingService.getWordByWordParsing(article));
        if(result.isEmpty()){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }

    @PostMapping(value = "getSentenceAndWordInfo")
    @ApiOperation(value = "获取文章每一句的单词&还原词的词性")
    public Result<List<SentenceWordVo>> getSentenceAndWordInfo(String article) {
        List<SentenceWordVo> result = wordProcessingService.getSentenceAndWordInfo(article);
        if(result.isEmpty()){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }

    @PostMapping(value = "getNVADJADVWord")
    @ApiOperation(value = "获取文章所有的(N,V,ADJ,ADV)词性单词")
    public Result<List<ParseWordVo>> getNVADJADVWord(String article) {
        List<ParseWordVo> result = wordProcessingService.getNVADJADVWord(article);
        if(result.isEmpty()){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }

    @PostMapping(value = "getWordInfo")
    @ApiOperation(value = "获取词汇信息")
    public Result<WordInfoVo> getWordInfo(@RequestBody List<Article> articles) {
        WordInfoVo result = wordProcessingService.getWordInfo(articles);
        if(result == null){
            return Result.failed("没有数据");
        }
        return Result.success(result);
    }
}
