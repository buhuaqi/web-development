package com.justl.dao;

import com.avos.avoscloud.AVObject;
import com.justl.utils.DbClient;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * app及其相关查询
 */
@Repository
public class AppDao {

    private static DbClient dbClient=new DbClient();

    public List queryBtns(){
        List<AVObject> list = dbClient.selectCQL("BtnStatistics", new HashMap<>());
        return  list;
    }

    public List queryModels(){
        List<AVObject> list = dbClient.selectCQL("ModelStatistics", new HashMap<>());
        return  list;
    }

    public List queryIncome(){
        List<AVObject> list = dbClient.selectCQL("Income", new HashMap<>());
        return  list;
    }
}
