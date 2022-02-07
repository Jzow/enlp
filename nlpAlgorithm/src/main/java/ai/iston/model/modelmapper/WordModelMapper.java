package ai.iston.model.modelmapper;

import ai.iston.model.bo.ParseWordBo;
import ai.iston.model.bo.VocabLevelBo;
import ai.iston.model.bo.WordBo;
import ai.iston.model.entity.VocabLevel;
import ai.iston.model.vo.ParseWordVo;
import ai.iston.model.vo.WordVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/21/21:12
 * @Description:
 */
@Mapper
public interface WordModelMapper {

    WordModelMapper INSTANCE = Mappers.getMapper(WordModelMapper.class);

    /**
     * bo -> vo
     * @param wordBo bo
     * @return vo
     */
    WordVo convertBoToVo(WordBo wordBo);

    /**
     * bo list -> vo list
     * @param wordBo bos
     * @return vos
     */
    List<WordVo> convertBosToVos(List<WordBo> wordBo);

    /**
     * parseWordBo -> parseWordVo
     * @param parseWordBo 转换bo
     * @return parseWordVo
     */
    ParseWordVo convertParseBoToParseVo(ParseWordBo parseWordBo);

    /**
     * parseWordBos -> parseWordVos
     * @param parseWordBos bos
     * @return vos
     */
    List<ParseWordVo> convertParseBosToParseVos(List<ParseWordBo> parseWordBos);

    /**
     * vocabLevel -> VocabLevelBo
     * @param vocabLevel entity
     * @return VocabLevel bo
     */
    VocabLevelBo convertVocabLevelToVocabLevelBo(VocabLevel vocabLevel);

    /**
     * vocabLevels -> VocabLevelBos
     * @param vocabLevels entity list
     * @return VocabLevel bo list
     */
    List<VocabLevelBo> convertVocabLevelTosVocabLevelBos(List<VocabLevel> vocabLevels);
}
