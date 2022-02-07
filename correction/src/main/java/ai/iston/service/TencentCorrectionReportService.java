package ai.iston.service;

import ai.iston.common.enums.FileTypeEnum;
import ai.iston.model.dto.QueryPageTencentReportDTO;
import ai.iston.model.dto.TencentCorrectionDTO;
import ai.iston.model.entity.TencentCorrection;
import ai.iston.model.entity.TencentCorrectionDetail;
import ai.iston.model.vo.FileUpLoadVO;
import ai.iston.model.vo.TencentCorrectionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/19:37
 * @Description:
 */
public interface TencentCorrectionReportService {

    /**
     * 插入数据到腾讯批改表
     * @param tencentCorrection 腾讯批改数据传输对象
     * @return
     */
    int insertTencentCorrectionData(TencentCorrection tencentCorrection);

    /**
     * 批量插入数据到腾讯批改表
     * @param tencentCorrectionDTOList
     * @return
     */
    boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionDTOList);

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

    /**
     * 查询报告列表
     * @param queryPageTencentReportDTO 分页查询对象
     * @return 返回批改报告列表
     */
    Page<TencentCorrectionVO> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO);

    /**
     * 一键批改
     * @param tencentCorrectionDTO 批改传入数据对象
     * @return 返回待定 2022-1-11
     */
    String tencentOneTouchCorrection(TencentCorrectionDTO tencentCorrectionDTO);

    /**
     * 文件上传
     * @param file 单个文件
     * @param fileTypeEnum 文件类型枚举
     * @return 返回上传成功的地址
     */
    FileUpLoadVO fileUpload(MultipartFile file, FileTypeEnum fileTypeEnum);
}
