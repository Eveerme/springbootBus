package com.chen.sbbus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.sbbus.entity.Feedback;

import java.util.List;

public interface FeedbackService extends IService<Feedback> {
    List<Feedback> getAllFeedbackList();

    List<Feedback> getFeedbackListByDriverId(Integer id);
    Feedback getFeedbackListById(Integer id);
    Integer insertFeedback(Feedback feedback);
    List<String> convertStringToList(String input);

    List<Feedback> getLists(List<Feedback> feedbackList);
}
