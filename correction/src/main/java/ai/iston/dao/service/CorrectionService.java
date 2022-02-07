package ai.iston.dao.service;

import ai.iston.model.entity.Correction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 全自动批改表 服务类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
public interface CorrectionService extends IService<Correction> {

    boolean insertBatchCorrectionDataInfo(List<Correction> correctionList);

    Correction querySysCorrection(Correction correction);
}
