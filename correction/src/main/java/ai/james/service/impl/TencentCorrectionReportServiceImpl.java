package ai.james.service.impl;

import ai.james.common.enums.FileTypeEnum;
import ai.james.common.properties.ComProperties;
import ai.james.dao.mdelmapper.TencentCorrectionModelMapper;
import ai.james.dao.service.TencentCorrectionDetailService;
import ai.james.dao.service.TencentCorrectionService;
import ai.james.model.bo.TencentScoreBO;
import ai.james.model.dto.QueryPageTencentReportDTO;
import ai.james.model.dto.TencentCorrectionDTO;
import ai.james.model.entity.TencentCorrection;
import ai.james.model.entity.TencentCorrectionDetail;
import ai.james.model.vo.FileUpLoadVO;
import ai.james.model.vo.TencentCorrectionVO;
import ai.james.service.TencentCorrectionReportService;
import cn.hutool.core.io.resource.InputStreamResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.http.HttpUtil;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: James Zow
 * @Date: 2022/01/11/19:37
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TencentCorrectionReportServiceImpl implements TencentCorrectionReportService {

    private final TencentCorrectionService tencentCorrectionService;

    private final TencentCorrectionDetailService tencentCorrectionDetailService;

    private final ComProperties comProperties;

    @Override
    public int insertTencentCorrectionData(TencentCorrection tencentCorrection) {
        return tencentCorrectionService.insertTencentCorrectionData(tencentCorrection);
    }

    @Override
    public boolean insertBatchTencentCorrectionData(List<TencentCorrection> tencentCorrectionDTOList) {
        return tencentCorrectionService.insertBatchTencentCorrectionData(tencentCorrectionDTOList);
    }

    @Override
    public List<TencentCorrectionDetail> queryTencentCorrectionDetailList(String tencentCorrectionId) {
        return tencentCorrectionDetailService.queryTencentCorrectionDetailList(tencentCorrectionId);
    }

    @Override
    public boolean insertBatchTencentCorrectionDetailData(List<TencentCorrectionDetail> tencentCorrectionDetails) {
        return tencentCorrectionDetailService.insertBatchTencentCorrectionDetailData(tencentCorrectionDetails);
    }

    @Override
    public Page<TencentCorrectionVO> queryCorrectionListInfoBySort(QueryPageTencentReportDTO queryPageTencentReportDTO) {
        Page<TencentCorrectionVO> tencentCorrectionVoPage = TencentCorrectionModelMapper.INSTANCE.convertPageVo(tencentCorrectionService.queryCorrectionListInfoBySort(queryPageTencentReportDTO));
        tencentCorrectionVoPage.getRecords().forEach(item -> {
            item.setScore(JSONObject.parseObject(String.valueOf(item.getModuleScore()), TencentScoreBO.class));
        });
        return tencentCorrectionVoPage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String tencentOneTouchCorrection(TencentCorrectionDTO tencentCorrectionDTO) {
        if(tencentCorrectionDTO == null){
            return null;
        }
        LocalDateTime correctionTime = LocalDateTime.now();
        List<TencentCorrectionDTO.Answer> answerList = tencentCorrectionDTO.getAnswerList();
        // 调用算法接口

        // 保存列表数据
        List<TencentCorrection> tencentCorrectionList = new ArrayList<>();
        for (TencentCorrectionDTO.Answer answer : answerList) {
            TencentScoreBO scoreBO = TencentScoreBO.builder()
                    .topicModule(tencentCorrectionDTO.getTopicModule())
                    .score(Arrays.asList(25,45))
                    .totalScore(100)
                    .build();

           TencentCorrection tencentCorrection = TencentCorrection.builder()
                    .topicModule(tencentCorrectionDTO.getTopicModule())
                    .moduleScore(JSONObject.toJSONString(scoreBO))
                    .correctionTime(correctionTime)
                    .build();
            tencentCorrectionList.add(tencentCorrection);

        }
        tencentCorrectionService.insertBatchTencentCorrectionData(tencentCorrectionList);
        return "批改成功";
    }

    public FileUpLoadVO upLoadToFileServer(MultipartFile file, ComProperties comProperties) {
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());
            Map<String, Object> params = new HashMap<>(16);
            params.put("file", isr);
            params.put("path", "tencent");
            params.put("output", "json");
            String resp = HttpUtil.post(comProperties.getUploadPath(), params);
            FileUpLoadVO fileUpLoadVo = JSON.parseObject(resp, FileUpLoadVO.class);
            fileUpLoadVo.setMultipartFile(file);
            return fileUpLoadVo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileUpLoadVO fileUpload(MultipartFile file, FileTypeEnum fileTypeEnum) {
        return upLoadToFileServer(file, comProperties);
    }
}
