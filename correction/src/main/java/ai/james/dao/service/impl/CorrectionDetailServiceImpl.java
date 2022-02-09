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
package ai.james.dao.service.impl;

import ai.james.dao.mapper.CorrectionDetailMapper;
import ai.james.dao.service.CorrectionDetailService;
import ai.james.model.entity.CorrectionDetail;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2022-01-06
 */
@Service
public class CorrectionDetailServiceImpl extends ServiceImpl<CorrectionDetailMapper, CorrectionDetail> implements CorrectionDetailService {

}
