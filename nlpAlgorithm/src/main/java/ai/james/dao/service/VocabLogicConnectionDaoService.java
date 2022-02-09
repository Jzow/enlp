/*
 * MIT License
 *
 * Copyright (c) 2021 James Zow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software
 */
package ai.james.dao.service;

import ai.james.model.entity.VocabLogicConnection;
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
