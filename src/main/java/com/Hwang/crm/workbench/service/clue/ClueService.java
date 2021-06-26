package com.Hwang.crm.workbench.service.clue;

import com.Hwang.crm.workbench.bean.activity.Activity;
import com.Hwang.crm.workbench.bean.clue.Clue;
import com.Hwang.crm.workbench.bean.relation.ClueActivityRelation;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ClueService {


    PageInfo<Clue> list(PageInfo<Clue> pageInfo, Clue clue);


    void saveClue(Clue clue);

    void updateClueById(Clue clue);

    void deleteClue(List<String> ids);

    List<Activity> getActivity(Clue clue, PageInfo<Activity> pageInfo);

    void deleteRelation(ClueActivityRelation relation);

    void insertRelation(String clueId, String[] ids);

    void insertConversion(Tran tran , HttpSession session);

}
