package ai.iston.dao.service;

import ai.iston.model.dto.QueryPageTencentReportDTO;
import ai.iston.model.dto.TencentCorrectionDTO;
import ai.iston.model.entity.TencentCorrection;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface TencentCorrectionService extends IService<TencentCorrection> {

    /**
     * 插入数据到腾讯批改表
     * @param tencentCorrection 腾讯批改数据传输对象
     * @return 返回是否插入数据成功
     */
    int insertTencentCorrectionData(TencentCorrection tencentCorrection);

    /**
     * 批量插入数据到腾讯批改表
     * @param tencentCorrectionList 腾讯批改传输集合对象
     * @return 返回是否插入数据成功
     */
    boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionList);

    /**
     * 查询报告列表
     * @param queryPageTencentReportDTO 分页查询对象
     * @return 返回批改报告列表
     */
    Page<TencentCorrection> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO);
}
