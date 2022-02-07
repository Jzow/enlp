package ai.iston.dao.mdelmapper;

import ai.iston.model.entity.TencentCorrection;
import ai.iston.model.vo.TencentCorrectionVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: James Zow
 * @Date: 2022/01/10/17:16
 * @Description:
 */
@Mapper
public interface TencentCorrectionModelMapper {

    TencentCorrectionModelMapper INSTANCE = Mappers.getMapper(TencentCorrectionModelMapper.class);

    /**
     * entity -> vo
     * @param tencentCorrection entity
     * @return vo
     */
    TencentCorrectionVO convertEntityToVo(TencentCorrection tencentCorrection);

    /**
     * Page<TencentCorrection> -> Page<TencentCorrectionVO>
     * @param tencentCorrectionPage entity page 对象
     * @return 返回批改报告分页集合数据
     */
    Page<TencentCorrectionVO> convertPageVo(Page<TencentCorrection> tencentCorrectionPage);
}
