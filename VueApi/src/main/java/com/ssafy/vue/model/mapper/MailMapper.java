package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MailDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.SecureMemberDto;

@Mapper
public interface MailMapper {

	public int findId(String userid) throws SQLException;
	
}
