package com.justl.dao;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.justl.domain.auto.BlackRoom;
import com.justl.domain.auto.User;
import com.justl.domain.response.ResponseData;
import com.justl.utils.DbClient;
import com.justl.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ly 2018-11-12
 */
@Repository
public class UserDao {

    private static Logger logger = LoggerFactory.getLogger(LoginDao.class);

    final DbClient dbClient=new DbClient();
    /**
     * 普通用户列表查询
     * @return List<AVUser>
     */
    public List<User> queryUser(String TbName,Map<String,Object> map){
        List<AVObject> avObjects = dbClient.selectCQL(TbName, map);
        List<User> list=new ArrayList();
        for (int i = 0; i < avObjects.size(); i++) {
            AVObject avObject = avObjects.get(i);
            User user=new User();
            user.setMobilePhoneNumber((String)avObject.get("mobilePhoneNumber"));
            user.setName((String)avObject.get("Name"));
            user.setSex((String)avObject.get("Sex"));
            user.setProperty((String)avObject.get("Property"));
            user.setHeight((String)avObject.get("Height"));
            user.setWeight((String)avObject.get("Weight"));
            user.setAge((String)avObject.get("Age"));
            user.setConstellation((String)avObject.get("Constellation"));
            user.setListId((String)avObject.get("ListId"));
            user.setGiveThumbsupNum(avObject.getInt("Givethumbsupnum"));
            user.setThumbsupNum(avObject.getInt("Thumbsupnum"));
            user.setGiftNum(avObject.getInt("Giftnum")+"");
            list.add(user);
         }
        return list;
    }
    /**
     * 小黑屋查询
     */
    public  List queryGoodUser(String TbName,Map<String, Object> map){
        DbClient dbClient = new DbClient();
        List avObjects =dbClient.queryGoodUser(TbName,map);
        List<User> list=new ArrayList();
        for (int i = 0; i < avObjects.size(); i++) {
            AVObject avObject = (AVObject) avObjects.get(i);
            User user=new User();
            user.setMobilePhoneNumber((String)avObject.get("mobilePhoneNumber"));
            user.setName((String)avObject.get("Name"));
            user.setSex((String)avObject.get("Sex"));
            user.setProperty((String)avObject.get("Property"));
            user.setHeight((String)avObject.get("Height"));
            user.setWeight((String)avObject.get("Weight"));
            user.setAge((String)avObject.get("Age"));
            user.setConstellation((String)avObject.get("Constellation"));
            user.setListId((String)avObject.get("ListId"));
            user.setGiveThumbsupNum(avObject.getInt("Givethumbsupnum"));
            user.setThumbsupNum(avObject.getInt("Thumbsupnum"));
            user.setGiftNum(avObject.getInt("Giftnum")+"");
            list.add(user);
        }
        return  list;
    }

    /**
     * 小黑屋查询
     * @param TbName
     * @param map
     * @return
     */
    public  List selectCQL(String TbName, Map<String, Object> map){
        List<AVObject> avObjects = dbClient.selectCQL(TbName, map);
        List list=new ArrayList();
        for (int i = 0; i < avObjects.size(); i++) {
            BlackRoom blackRoom=new BlackRoom();
            AVObject avObject = avObjects.get(i);
            blackRoom.setMobilePhoneNumber((String )avObject.get("mobilePhoneNumber"));
            blackRoom.setName((String )avObject.get("Name"));
            blackRoom.setSex((String )avObject.get("Sex"));
            blackRoom.setReportNum(avObject.getInt("ReportNum")+"");
            blackRoom.setReportType((String )avObject.get("ReportType"));
            blackRoom.setReportObjectid((String )avObject.get("ReportObjectid"));
            blackRoom.setAppeal(avObject.getBoolean("Appeal"));
            list.add(blackRoom);
        }
        return list;
    }
}
