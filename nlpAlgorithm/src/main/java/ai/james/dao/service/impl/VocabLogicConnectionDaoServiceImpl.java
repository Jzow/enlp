package ai.james.dao.service.impl;

import ai.james.dao.mapper.VocabLogicConnectionMapper;
import ai.james.dao.service.VocabLogicConnectionDaoService;
import ai.james.model.entity.VocabLogicConnection;
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
