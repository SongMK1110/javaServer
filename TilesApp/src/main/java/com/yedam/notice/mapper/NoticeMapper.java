package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeMapper {
	public List<NoticeVO> selectList();
	public NoticeVO searchOne(int nid); //한건조회
	public int insertNotice(NoticeVO notice); //글등록
	public int updateNotice(NoticeVO notice); //글수정
	public int deleteNotice(int nid); //글삭제
	public int increaseCnt(int nid);//조회수증가
	
	//댓글등록
	public int insertReply(ReplyVO reply);
	//댓글목록
	public List<ReplyVO> replyList(int nid);
	//댓글삭제
	public int deleteReply(int rid); //댓글번호
}
