package com.justl.serviceImp;

import com.justl.dao.AppDao;
import com.justl.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImp implements AppService {
    @Autowired
    private AppDao appDao;
    @Override
    public List queryBtns() {
        List list = appDao.queryBtns();
        return list;
    }

    @Override
    public List queryModels() {
        List list = appDao.queryModels();
        return list;
    }

    @Override
    public List queryIncome() {
        List list = appDao.queryIncome();
        return list;
    }
}
