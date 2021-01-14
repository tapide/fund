package com.ni235.core.fund.controller;

import java.io.IOException;
import java.util.Map;

import com.ni235.common.utils.PageUtils;
import com.ni235.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ni235.core.fund.entity.JjFundInfoEntity;
import com.ni235.core.fund.service.JjFundInfoService;



/**
 * 
 *
 * @author tapide
 * @email tapide@163.com
 * @date 2021-01-13 17:32:31
 */
@RestController
@RequestMapping("fund/core")
public class JjFundInfoController {
    @Autowired
    private JjFundInfoService jjFundInfoService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jjFundInfoService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{symbol}")
    public R info(@PathVariable("symbol") String symbol){
		JjFundInfoEntity jjFundInfo = jjFundInfoService.getById(symbol);
        return R.ok().put("jjFundInfo", jjFundInfo);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(){
        try {
            jjFundInfoService.saveFundInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }



}
