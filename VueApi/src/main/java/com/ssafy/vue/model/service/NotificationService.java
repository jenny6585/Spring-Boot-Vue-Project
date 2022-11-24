package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.NotificationDto;
import com.ssafy.vue.model.NotificationParameterDto;

public interface NotificationService {
	public boolean writeNotification(NotificationDto notificationDto) throws Exception;
	public List<NotificationDto> listNotification(NotificationParameterDto notificationParameterDto) throws Exception;
	public PageNavigation makePageNavigation(NotificationParameterDto notificationParameterDto) throws Exception;
	
	public NotificationDto getNotification(int Notificationno) throws Exception;
	public void updateHit(int Notificationno) throws Exception;
	public boolean modifyNotification(NotificationDto notificationDto) throws Exception;
	public boolean deleteNotification(int Notificationno) throws Exception;
	public int getTotalCount(NotificationParameterDto notificationParameterDto)throws Exception;
}
