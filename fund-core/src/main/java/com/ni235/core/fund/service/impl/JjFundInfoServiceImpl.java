package com.ni235.core.fund.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.ni235.common.constant.FundConstant;
import com.ni235.common.utils.HttpUtils;
import com.ni235.common.utils.PageUtils;
import com.ni235.common.utils.Query;
import com.ni235.core.fund.dao.JjFundInfoDao;
import com.ni235.core.fund.entity.JjFundInfoEntity;
import com.ni235.core.fund.service.JjFundInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class JjFundInfoServiceImpl extends ServiceImpl<JjFundInfoDao, JjFundInfoEntity> implements JjFundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<JjFundInfoEntity> page = this.page(
                new Query<JjFundInfoEntity>().getPage(params),
                new QueryWrapper<JjFundInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveFundInfo() throws IOException {
        saveFund(1,8000);
        System.out.println("添加完成。");
    }

    private boolean saveFund(int pi,int num) throws IOException {
        HttpEntity entity = HttpUtils.httpGet("http://fund.eastmoney.com/data/FundGuideapi.aspx?dt=4&sd=&ed=&sc=3y&st=desc&pi="+pi+"&pn="+num+"&zf=diy&sh=list&rnd=0.47558301404199343");
        if (entity != null) {
            String html = EntityUtils.toString(entity, "gb2312");
            String str = html.substring(14);
            JSONObject object = new JSONObject(str);
            Integer allPages = object.getInt("allPages");
            Integer pageIndex = object.getInt("pageIndex");
            JSONArray jsonArray = object.getJSONArray("datas");
            Set<String>  set = new HashSet<>();
            List<JjFundInfoEntity> infoEntities = jsonArray.stream().map(item -> {
                String[] info = item.toString().split(",");
                JjFundInfoEntity jjFundInfoEntity = new JjFundInfoEntity();
                jjFundInfoEntity.setSymbol(info[0]);
                jjFundInfoEntity.setName(info[1]);
                jjFundInfoEntity.setSname(info[2]);
                String type = info[3];
                getType(type,jjFundInfoEntity,set);
                jjFundInfoEntity.setThisYear(getBigDecimal(info[4]));
                jjFundInfoEntity.setWeek(getBigDecimal(info[5]));
                jjFundInfoEntity.setOneMonth(getBigDecimal(info[6]));
                jjFundInfoEntity.setThreeMonth(getBigDecimal(info[7]));
                jjFundInfoEntity.setSixMonth(getBigDecimal(info[8]));
                jjFundInfoEntity.setOneYear(getBigDecimal(info[9]));
                jjFundInfoEntity.setTwoYear(getBigDecimal(info[10]));
                jjFundInfoEntity.setThreeYear(getBigDecimal(info[11]));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    jjFundInfoEntity.setDate(sdf.parse(info[15]));
                } catch (ParseException e) {
                    System.out.println(jjFundInfoEntity.getSymbol() + "\t" + jjFundInfoEntity.getName());
                }
                jjFundInfoEntity.setPerNav(getBigDecimal(info[16]));
                jjFundInfoEntity.setDay(getBigDecimal(info[17]));
                return jjFundInfoEntity;
            }).collect(Collectors.toList());
            log.info("开始添加---------------------------"+infoEntities.size());
            for (String s : set) {
                System.out.println(s);
            }
            return this.saveBatch(infoEntities, infoEntities.size());
        }
        return false;
    }

    private void getType(String type,JjFundInfoEntity jjFundInfoEntity,Set<String> set){
        if ("股票型".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_GP.getCode());
        } else if ("混合型".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_HH.getCode());
        } else if ("债券型".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_ZQ.getCode());
        } else if ("指数型".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_ZS.getCode());
        } else if ("QDII-指数".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_QDII_ZS.getCode());
        } else if ("混合-FOF".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_HH_FOF.getCode());
        }else if ("股票-FOF".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_GP_FOF.getCode());
        }else if ("QDII".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_QDII.getCode());
        }else if ("定开债券".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_DK_ZQ.getCode());
        }else if ("股票指数".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_GP_ZS.getCode());
        }else if ("其他创新".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_CX.getCode());
        }else if ("联接基金".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_LJ.getCode());
        }else if ("债券指数".equals(type)) {
            jjFundInfoEntity.setType(FundConstant.FundEnum.FUND_TYPE_ZQ_ZS.getCode());
        }else {
            set.add(type);
        }
    }

    private BigDecimal getBigDecimal(String value){
        if(StringUtils.isEmpty(value)){
            value = "0";
        }
       return new BigDecimal(value);
    }

}