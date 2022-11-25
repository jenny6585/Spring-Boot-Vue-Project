package com.ssafy.vue.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.SecureMemberDto;
import com.ssafy.vue.model.TodoDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemberMapper;
import com.ssafy.vue.model.mapper.TodoMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

	@Override
	@Transactional
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto) == 1;
	}

	@Override
	public boolean joinMember(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUsername() == null || memberDto.getUserpwd() == null
				|| memberDto.getEmail() == null || memberDto.getNumber() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).joinMember(memberDto) == 1;
	}

	@Override
	public SecureMemberDto getSecure(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getSecure(userid);
	}

	@Override
	public void secureMember(SecureMemberDto secureMemberDto) throws Exception {
		sqlSession.getMapper(MemberMapper.class).secureMember(secureMemberDto);
	}

	@Override
	public MemberDto findMember(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).findMember(userid);
	}

	@Override
	public boolean deleteMember(int userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(userid) == 1;
	}

}
