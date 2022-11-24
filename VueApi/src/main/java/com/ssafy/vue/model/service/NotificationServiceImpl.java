package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.NotificationDto;
import com.ssafy.vue.model.NotificationParameterDto;
import com.ssafy.vue.model.mapper.NotificationMapper;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeNotification(NotificationDto notificationDto) throws Exception {
		if(notificationDto.getSubject() == null || notificationDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(NotificationMapper.class).writeNotification(notificationDto) == 1;
	}

	@Override
	public List<NotificationDto> listNotification(NotificationParameterDto NotificationParameterDto) throws Exception {
		int start = NotificationParameterDto.getPg() == 0 ? 0 : (NotificationParameterDto.getPg() - 1) * NotificationParameterDto.getSpp();
		NotificationParameterDto.setStart(start);
		return sqlSession.getMapper(NotificationMapper.class).listNotification(NotificationParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(NotificationParameterDto notificationParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(notificationParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(NotificationMapper.class).getTotalCount(notificationParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / notificationParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = notificationParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < notificationParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public NotificationDto getNotification(int notificationno) throws Exception {
		return sqlSession.getMapper(NotificationMapper.class).getNotification(notificationno);
	}
	
	@Override
	public void updateHit(int notificationno) throws Exception {
		sqlSession.getMapper(NotificationMapper.class).updateHit(notificationno);
	}

	@Override
	@Transactional
	public boolean modifyNotification(NotificationDto notificationDto) throws Exception {
		return sqlSession.getMapper(NotificationMapper.class).modifyNotification(notificationDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteNotification(int notificationno) throws Exception {
		sqlSession.getMapper(NotificationMapper.class).deleteMemo(notificationno);
		return sqlSession.getMapper(NotificationMapper.class).deleteNotification(notificationno) == 1;
	}

	@Override
    public int getTotalCount(NotificationParameterDto notificationParameterDto) throws Exception{
        return sqlSession.getMapper(NotificationMapper.class).getTotalCount(notificationParameterDto);
    }
}