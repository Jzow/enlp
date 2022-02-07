package ai.iston.api;

import ai.iston.common.enums.FileTypeEnum;
import ai.iston.common.result.Result;
import ai.iston.common.result.ResultCode;
import ai.iston.dao.mdelmapper.TencentCorrectionModelMapper;
import ai.iston.model.dto.QueryPageTencentReportDTO;
import ai.iston.model.dto.TencentCorrectionDTO;
import ai.iston.model.vo.FileUpLoadVO;
import ai.iston.model.vo.TencentCorrectionVO;
import ai.iston.service.TencentCorrectionReportService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/10/16:00
 * @Description: 腾讯-全自动批改API
 */
@RequestMapping("tencent")
@RestController
@RequiredArgsConstructor
@Api(tags = "腾讯demo")
public class TencentCorrectionController {

    private final TencentCorrectionReportService tencentService;

    @PostMapping(value = "correction")
    @ApiOperation("批改接口")
    public Result<String> correction(@RequestBody TencentCorrectionDTO tencentCorrectionDTO){
        String resultFlag = tencentService.tencentOneTouchCorrection(tencentCorrectionDTO);
        if(resultFlag == null){
            return Result.failed("批改失败");
        }
        return Result.success(ResultCode.SUCCESS, "批改成功~");
    }

    @PostMapping(value = "queryReportList")
    @ApiOperation("分页查询成绩报告")
    public Result<Page<TencentCorrectionVO>> queryReportList(@RequestBody QueryPageTencentReportDTO queryPageTencentReportDTO){
        Page<TencentCorrectionVO> tencentCorrectionVoPage;
        tencentCorrectionVoPage = tencentService.queryCorrectionListInfoBySort(queryPageTencentReportDTO);
        if(tencentCorrectionVoPage == null){
            return Result.failed("没有查询到对应的数据");
        }
        return Result.success(tencentCorrectionVoPage);
    }

    @ApiOperation("文件上传")
    @PostMapping(value = "fileUpload")
    public Result<FileUpLoadVO> fileUpload(@RequestPart(value= "file") MultipartFile file, @RequestParam(value = "fileTypeEnum")FileTypeEnum fileTypeEnum) {
        FileUpLoadVO fileUpLoadVO = tencentService.fileUpload(file, fileTypeEnum);
        if(fileUpLoadVO == null){
            return Result.failed("文件上传失败，请检查命名格式！");
        }
        return Result.success(fileUpLoadVO);
    }
}
