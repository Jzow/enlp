package ai.iston.dao.service.impl;

import ai.iston.dao.mapper.TencentCorrectionMapper;
import ai.iston.dao.mdelmapper.TencentCorrectionModelMapper;
import ai.iston.dao.service.TencentCorrectionService;
import ai.iston.model.dto.QueryPageTencentReportDTO;
import ai.iston.model.dto.TencentCorrectionDTO;
import ai.iston.model.entity.TencentCorrection;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  腾讯批改ORM服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Service
@RequiredArgsConstructor
public class TencentCorrectionServiceImpl extends ServiceImpl<TencentCorrectionMapper, TencentCorrection> implements TencentCorrectionService {

    private final TencentCorrectionMapper tencentCorrectionMapper;

    @Override
    public int insertTencentCorrectionData(TencentCorrection tencentCorrection) {
        return tencentCorrectionMapper.insert(tencentCorrection);
    }

    @Override
    public boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionList) {
        return saveBatch(tencentCorrectionList);
    }

    @Override
    public Page<TencentCorrection> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO) {
        return lambdaQuery()
                .orderBy(null != queryPageTencentReportDTO.getSerialNumber(), queryPageTencentReportDTO.getSerialNumber() == null, TencentCorrection::getSerialNumber)
                .orderBy(null != queryPageTencentReportDTO.getTopicModule(), queryPageTencentReportDTO.getTopicModule() == null, TencentCorrection::getTopicModule)
                .orderByDesc(TencentCorrection::getCorrectionTime)
                .page(queryPageTencentReportDTO.buildMybatisPage());
    }
}
