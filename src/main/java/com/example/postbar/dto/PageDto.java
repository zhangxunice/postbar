package com.example.postbar.dto;

import com.baomidou.mybatisplus.core.toolkit.support.BiIntFunction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxu
 * @title
 * @date 2019/8/21 19:55
 */
@Data
public class PageDto {
    private List<QuestionDto> questionDtoList;
    private boolean showleft;           //上一页
    private boolean shownext;          //下一页
    private boolean showFirstpage;        //第一页
    private boolean showEndpage;       //最后一页
    private Integer currentpage;       //当前页
    private List<Integer> longpages = new ArrayList<>();      //页条
    private long totalpages;          //总页数

    public void setpages(long pages, long total, Integer page, Integer pagesize) {
        //pages:总页数    total:总数据数    page:当前页   pagesize:每页条数
        // System.out.println(page);
        currentpage = page;
        totalpages = pages;
        longpages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                longpages.add(0, page - i);
            }
            if (page + i <= pages) {
                longpages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showleft = false;
        } else {
            showleft = true;
        }
        //是否展示下一页
        if (page == pages) {
            shownext = false;
        } else {
            shownext = true;
        }
        //是否展示第一页
        if (longpages.contains(1)) {
            showFirstpage = false;
        } else {
            showFirstpage = true;
        }
        //是否展示最后一页
        if (longpages.contains((int) pages)) {
            showEndpage = false;
        } else {
            showEndpage = true;
        }
    }
}
