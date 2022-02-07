package ai.iston.dao.service.impl;

import ai.iston.dao.mapper.CorrectionModuleMapper;
import ai.iston.dao.service.CorrectionModuleService;
import ai.iston.model.entity.CorrectionModule;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
@Service
@RequiredArgsConstructor
public class CorrectionModuleServiceImpl extends ServiceImpl<CorrectionModuleMapper, CorrectionModule> implements CorrectionModuleService {

    @Override
    public boolean insertBatchCorrectionModuleDataInfo(List<CorrectionModule> correctionModuleList) {
        return saveBatch(correctionModuleList);
    }

    @Override
    public CorrectionModule queryCorrectionModuleByCorrectionId(String correctionId) {
        return lambdaQuery()
                .eq(StringUtils.hasText(correctionId), CorrectionModule::getCorrectionId, correctionId)
                .one();
    }
}
