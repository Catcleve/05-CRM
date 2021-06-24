package com.Hwang.crm.workbench.service;

import com.Hwang.crm.workbench.bean.Activity;
import com.Hwang.crm.workbench.bean.Clue;
import com.Hwang.crm.workbench.bean.ClueActivityRelation;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClueService {


    PageInfo<Clue> list(PageInfo<Clue> pageInfo, Clue clue);


    void saveClue(Clue clue);

    void updateClueById(Clue clue);

    void deleteClue(List<String> ids);

    List<Activity> getActivity(Clue clue, PageInfo<Activity> pageInfo);

    void deleteRelation(ClueActivityRelation relation);

    void insertRelation(String clueId, String[] ids);
}
