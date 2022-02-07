package ai.iston.dao.service;

import ai.iston.model.entity.VocabLogicConnection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: James Zow
 * @Date: 2022/01/25/21:32
 * @Description:
 */
public interface VocabLogicConnectionDaoService extends IService<VocabLogicConnection> {

    /**
     * 获取所有逻辑连接词
     * @return
     */
    List<VocabLogicConnection> getVocalLogicConnectionList();
}
