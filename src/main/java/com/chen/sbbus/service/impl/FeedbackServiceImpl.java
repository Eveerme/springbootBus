package com.chen.sbbus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.sbbus.entity.Feedback;
import com.chen.sbbus.entity.PrivateNotice;
import com.chen.sbbus.mapper.FeedbackMapper;
import com.chen.sbbus.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<Feedback> getAllFeedbackList() {
        List<Feedback> feedbackList = feedbackMapper.getAllFeedbackList();
        //获取List<Feedback> imgLists
        return getLists(feedbackList);
    }

    @Override
    public List<Feedback> getFeedbackListByDriverId(Integer id) {
        List<Feedback> feedbackList = feedbackMapper.getFeedbackListByDriverId(id);
        //获取List<Feedback> imgLists
        return getLists(feedbackList);
    }

    @Override
    public Feedback getFeedbackListById(Integer id) {
        Feedback feedback = feedbackMapper.getFeedbackListById(id);
        feedback.setImgLists(convertStringToList(feedback.getImgList()));
        return feedback;
    }

    @Override
    public Integer insertFeedback(Feedback feedback) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        feedback.setTime(formatter.format(date));
        return feedbackMapper.insertFeedback(feedback);
    }

    @Override
    public List<String> convertStringToList(String input) {
        String[] splitStrings = input.split(",");
        List<String> result = new ArrayList<>();
        for (String str : splitStrings) {
            result.add(str.trim());
        }
        return result;
    }

    @Override
    public List<Feedback> getLists(List<Feedback> feedbackList) {
        for (int i = 0; i<feedbackList.size(); i++){
            if (feedbackList.get(i).getImgList()==null){
                continue;
            }
            Feedback feedback = feedbackList.get(i);
            feedbackList.get(i).setImgLists(convertStringToList(feedback.getImgList()));
        }
        return feedbackList;
    }

    @Override
    public IPage<Feedback> getFeedbackByPage(int currentPage, int pageSize, String driverId) {
        IPage<Feedback> page = new Page<>(currentPage,pageSize);
        if (driverId.equals("")){
            return feedbackMapper.selectPage(page,null);
        }
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        Integer dId = Integer.valueOf(driverId);
        queryWrapper.like("driver_id",dId);         //根据司机ID模糊查询
        queryWrapper.orderByAsc("time");         //根据time升序排序
        return feedbackMapper.selectPage(page,queryWrapper);
    }

    @Override
    public Integer updateFeedbackIsSolve(Feedback feedback) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        feedback.setSolveTime(formatter.format(date));
        return feedbackMapper.updateFeedbackIsSolve(feedback);
    }

}
