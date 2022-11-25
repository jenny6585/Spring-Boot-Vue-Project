package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.SecureMemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	public int joinMember(MemberDto memberDto) throws SQLException;
	public SecureMemberDto getSecure(String id) throws SQLException;
	public void secureMember(SecureMemberDto secureMemberDto) throws SQLException;
	public MemberDto findMember(String userid) throws SQLException;
	public int deleteMember(int userid) throws SQLException;
}
