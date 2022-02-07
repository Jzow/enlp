package ai.iston.dao.service.impl;

import ai.iston.dao.mapper.VocabLogicConnectionMapper;
import ai.iston.dao.service.VocabLogicConnectionDaoService;
import ai.iston.model.entity.VocabLogicConnection;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/21:35
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class VocabLogicConnectionDaoServiceImpl extends ServiceImpl<VocabLogicConnectionMapper, VocabLogicConnection> implements VocabLogicConnectionDaoService {

    @Override
    public List<VocabLogicConnection> getVocalLogicConnectionList() {
        return list();
    }
}
