package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.util.OpenCrypt;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.SecureMemberDto;
import com.ssafy.vue.model.service.JwtServiceImpl;
import com.ssafy.vue.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			SecureMemberDto sec = memberService.getSecure(memberDto.getUserid());
			if(sec==null) {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}else {
				String pw = OpenCrypt.getSHA256(memberDto.getUserpwd(), sec.getSalt());
				memberDto.setUserpwd(pw);
				MemberDto loginUser = memberService.login(memberDto);
				
				if (loginUser != null) {
					String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
					String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
					memberService.saveRefreshToken(memberDto.getUserid(), refreshToken);
					logger.debug("로그인 accessToken 정보 : {}", accessToken);
					logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
					resultMap.put("access-token", accessToken);
					resultMap.put("refresh-token", refreshToken);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} else {
					resultMap.put("message", FAIL);
					status = HttpStatus.ACCEPTED;
				}
			}
			
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@ApiOperation(value = "회원 수정", notes = "수정할 회원 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyMember(@RequestBody @ApiParam(value = "수정할 회원.", required = true) MemberDto memberDto) throws Exception {
		logger.info("modifyMember - 호출 {}", memberDto);
		
		if (memberService.modifyMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", memberDto.getUserid());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원 추가", notes = "추가할 회원 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/join")
	public ResponseEntity<String> joinMember(@RequestBody @ApiParam(value = "게시글 정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("joinMember - 호출 {}",memberDto);
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(memberDto.getUserid());
		try {
			byte[] key = OpenCrypt.generateKey("AES", 128);
			SecureMemberDto secureMemberDto = new SecureMemberDto(memberDto.getUserid(), UUID.randomUUID().toString(), OpenCrypt.byteArrayToHex(key));
			System.out.println(secureMemberDto);
			memberService.secureMember(secureMemberDto);
			memberDto.setUserpwd(OpenCrypt.getSHA256(memberDto.getUserpwd(), secureMemberDto.getSalt()));
			memberService.joinMember(memberDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "아이디 중복 검사", notes = "아이디 중복 검사한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/find")
	public ResponseEntity<String> findMember(@RequestBody @ApiParam(value = "아이디 정보.", required = true) String userid) throws Exception {
		logger.info("findMember - 호출 {}",userid);
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("id : " + userid);
		if(memberService.findMember(userid)!=null) {
			//이미 존재하는 아이디
			resultMap.put("message", "이미 존재하는 아이디입니다.");
			System.out.println("있다"+memberService.findMember(userid));
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}else {
			System.out.println(memberService.findMember(userid));
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		
		
	}
	
	@ApiOperation(value = "회원 삭제", notes = "userid. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @DeleteMapping("/{userid}")
    public ResponseEntity<String> deleteMember(@PathVariable("articleno") @ApiParam(value = "userid의 글번호.", required = true) int userid) throws Exception {
        logger.info("deleteArticle - 호출");
        if (memberService.deleteMember(userid)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
		

}
