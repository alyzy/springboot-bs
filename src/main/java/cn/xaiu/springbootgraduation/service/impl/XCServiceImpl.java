package cn.xaiu.springbootgraduation.service.impl;

import cn.xaiu.springbootgraduation.dao.IXCDao;
import cn.xaiu.springbootgraduation.domain.XC;
import cn.xaiu.springbootgraduation.service.IXCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class XCServiceImpl implements IXCService {
    @Autowired
    private IXCDao ixcDao;
    @Override
    public void addXC(XC xc) {
         ixcDao.insert(xc);
    }

    @Override
    public List<XC> listAll() {
        return ixcDao.selectAll();
    }

    @Override
    public XC getOneByName(String name) {
        return null;
    }

    @Override
    public void addOne(XC xc) {
        ixcDao.insert(xc);
    }
}
