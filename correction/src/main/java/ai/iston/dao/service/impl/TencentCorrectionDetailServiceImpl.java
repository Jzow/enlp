package ai.iston.dao.service.impl;

import ai.iston.dao.mapper.TencentCorrectionDetailMapper;
import ai.iston.dao.service.TencentCorrectionDetailService;
import ai.iston.model.entity.TencentCorrectionDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
@Service
public class TencentCorrectionDetailServiceImpl extends ServiceImpl<TencentCorrectionDetailMapper, TencentCorrectionDetail> implements TencentCorrectionDetailService {

    @Override
    public List<TencentCorrectionDetail> queryTencentCorrectionDetailList(String tencentCorrectionId){
        return lambdaQuery()
                .eq(StringUtils.hasText(tencentCorrectionId), TencentCorrectionDetail::getTencentCorrectionId, tencentCorrectionId)
                .list();
    }

    @Override
    public boolean insertBatchTencentCorrectionDetailData(List<TencentCorrectionDetail> tencentCorrectionDetails) {
        return saveBatch(tencentCorrectionDetails);
    }
}
