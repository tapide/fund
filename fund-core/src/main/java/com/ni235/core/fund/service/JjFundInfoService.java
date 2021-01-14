package com.ni235.core.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ni235.common.utils.PageUtils;
import com.ni235.core.fund.entity.JjFundInfoEntity;

import java.io.IOException;
import java.util.Map;

/**
 * 
 *
 * @author tapide
 * @email tapide@163.com
 * @date 2021-01-13 17:32:31
 */
public interface JjFundInfoService extends IService<JjFundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveFundInfo() throws IOException;

}

