package ai.james.dao.service;

import ai.james.model.entity.CorrectionModule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
public interface CorrectionModuleService extends IService<CorrectionModule> {

    boolean insertBatchCorrectionModuleDataInfo(List<CorrectionModule> correctionModuleList);

    CorrectionModule queryCorrectionModuleByCorrectionId(String correctionId);
}
