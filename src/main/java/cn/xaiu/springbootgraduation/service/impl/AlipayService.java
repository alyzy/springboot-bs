package cn.xaiu.springbootgraduation.service.impl;

import cn.xaiu.springbootgraduation.dao.IAlipayDao;
import cn.xaiu.springbootgraduation.dao.IAlldao;
import cn.xaiu.springbootgraduation.domain.AlipayProductPoJo;
import cn.xaiu.springbootgraduation.domain.AlipayPublicPoJo;
import cn.xaiu.springbootgraduation.domain.User;
import cn.xaiu.springbootgraduation.service.IAlipayService;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
@Transactional
@SuppressWarnings("ALL")
public class AlipayService implements IAlipayService{

    @Autowired
    private IAlipayDao iAlipayDao;
    @Autowired
    private IAlldao iAlldao;

    public void addOnePay(AlipayProductPoJo alipayProductPoJo, String routeId,User user) {
        iAlipayDao.insert(alipayProductPoJo);
        iAlldao.insertPR(alipayProductPoJo.getOutTradeNo(),routeId);
        iAlldao.insertUP(user.getUsername(),alipayProductPoJo.getOutTradeNo());

    }

    @Override
    public void updateOne(String outTradeNo, String trade_no, Date payDate) {

        try {

            Long otn = Long.valueOf(outTradeNo);
            Long tn = Long.valueOf(trade_no);
            iAlipayDao.updateOne(otn,tn,payDate,"A");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<AlipayProductPoJo> listAll(String username) {

        return  iAlipayDao.getByNameIsUser(username);
    }

    @Override
    public AlipayProductPoJo getOneByName(String name) {
        return iAlipayDao.getByName(name);
    }
}
