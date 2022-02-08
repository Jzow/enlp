package ai.james.dao.service.impl;


import ai.james.dao.mapper.CorrectionMapper;
import ai.james.dao.service.CorrectionService;
import ai.james.model.entity.Correction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 全自动批改表 服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
@Service
@RequiredArgsConstructor
public class CorrectionServiceImpl extends ServiceImpl<CorrectionMapper, Correction> implements CorrectionService {

    private final CorrectionMapper correctionMapper;

    @Override
    public boolean insertBatchCorrectionDataInfo(List<Correction> correctionList) {
        return saveBatch(correctionList);
    }

    @Override
    public Correction querySysCorrection(Correction correction) {
        return lambdaQuery()
                .eq(StringUtils.hasText(correction.getClassId()), Correction::getClassId, correction.getClassId())
                .eq(StringUtils.hasText(correction.getHomeworkId()), Correction::getHomeworkId, correction.getHomeworkId())
                .eq(StringUtils.hasText(correction.getId()), Correction::getId, correction.getId())
                .eq(StringUtils.hasText(correction.getUserId()), Correction::getUserId, correction.getUserId())
                .one();
    }
}
