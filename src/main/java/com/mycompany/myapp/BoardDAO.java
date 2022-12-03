package com.mycompany.myapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.myapp.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
//	Connection conn = null;
//	PreparedStatement stmt = null;
//	ResultSet rs = null;

	private final String BOARD_INSERT = "insert into BOARD1 (title, writer, content) values (?,?,?)";
	private final String BOARD_UPDATE = "update BOARD1 set title=?, writer=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD1  where seq=?";
	private final String BOARD_GET = "select * from BOARD1  where seq=?";
	private final String BOARD_LIST = "select * from BOARD1 order by seq desc";


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertBoard(BoardVO vo) {
		String sql = "insert BOARD1 (title, content, writer) values (?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{vo.getTitle(),vo.getContent(),vo.getWriter()});
	}


	// 글 삭제
	public int deleteBoard(int seq) {
		String sql = "delete from BOARD1 where seq = ?";
		return jdbcTemplate.update(sql,new Object[]{seq});
	}
	public int updateBoard(BoardVO vo) {
		String BOARD_UPDATE = "update BOARD1 set title=?, content=?, writer=? where seq=?";
		return jdbcTemplate.update(BOARD_UPDATE,new Object[]{vo.getTitle(),vo.getContent(),vo.getWriter(),vo.getSeq()});
	}
	class BoardRowMapper implements RowMapper<BoardVO>{
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			BoardVO vo = new BoardVO();
			vo.setSeq(rs.getInt("seq"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWriter(rs.getString("writer"));
			vo.setRegdate(rs.getDate("regdate"));
			return vo;
		}
	}
	
	public BoardVO getBoard(int seq) {
		String sql="select * from BOARD1 where seq="+seq;
		return jdbcTemplate.queryForObject(sql,new BoardRowMapper());
	}
	
	public List<BoardVO> getBoardList(){
		String sql="select * from BOARD1 order by regdate desc";
		return jdbcTemplate.query(sql,new BoardRowMapper());

	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}
}
