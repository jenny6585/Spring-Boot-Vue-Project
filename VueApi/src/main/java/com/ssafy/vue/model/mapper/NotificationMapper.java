package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.NotificationDto;
import com.ssafy.vue.model.NotificationParameterDto;

@Mapper
public interface NotificationMapper {
	
	public int writeNotification(NotificationDto NotificationDto) throws SQLException;
	public List<NotificationDto> listNotification(NotificationParameterDto NotificationParameterDto) throws SQLException;
	public int getTotalCount(NotificationParameterDto NotificationParameterDto) throws SQLException;
	public NotificationDto getNotification(int Notificationno) throws SQLException;
	public void updateHit(int Notificationno) throws SQLException;
	public int modifyNotification(NotificationDto NotificationDto) throws SQLException;
	public void deleteMemo(int Notificationno) throws SQLException;
	public int deleteNotification(int Notificationno) throws SQLException;
	
}