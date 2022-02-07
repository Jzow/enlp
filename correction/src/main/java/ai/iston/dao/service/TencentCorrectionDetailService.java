package ai.iston.dao.service;

import ai.iston.model.entity.TencentCorrectionDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-10
 */
public interface TencentCorrectionDetailService extends IService<TencentCorrectionDetail> {

    /**
     * 查询批改详情数据
     * @param tencentCorrectionId 根据批改id
     * @return 返回 批改详情数据
     */
    List<TencentCorrectionDetail> queryTencentCorrectionDetailList(String tencentCorrectionId);

    /**
     * 批量插入批改详情数据
     * @param tencentCorrectionDetails 批改详情传输对象
     * @return 是否插入成功 true 成功 false 失败
     */
    boolean insertBatchTencentCorrectionDetailData(List<TencentCorrectionDetail> tencentCorrectionDetails);
}
