package com.example.demo.src.board;


import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.board.model.PatchBoardReq;
import com.example.demo.src.board.model.PatchStatusReq;
import com.example.demo.src.board.model.PostBoardReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 게시글 등록
    public int createBoard(PostBoardReq postBoardReq) {
        String createUserQuery = "insert into Board (userId, title, content) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{postBoardReq.getUserId(), postBoardReq.getTitle(), postBoardReq.getContent()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    // 게시글 수정
    public int modifyBoard(PatchBoardReq patchBoardReq) {
        String modifyBoardQuery = "update Board set title = ?, content = ? where boardIdx = ? ";
        Object[] modifyBoardParams = new Object[]{patchBoardReq.getTitle(), patchBoardReq.getContent(), patchBoardReq.getBoardIdx()};

        return this.jdbcTemplate.update(modifyBoardQuery, modifyBoardParams);
    }

    // 게시글 삭제
    public int deleteBoard(PatchStatusReq patchStatusReq) {
        String modifyBoardQuery = "update Board set status = ? where boardIdx = ? ";
        Object[] modifyBoardParams = new Object[]{patchStatusReq.isStatus(), patchStatusReq.getBoardIdx()};

        return this.jdbcTemplate.update(modifyBoardQuery, modifyBoardParams);
    }

    // Board 테이블에 존재하는 전체 게시글 조회
    public List<GetBoardRes> getBoards() {
        String getBoardsQuery = "select * from Board where status=1";
        return this.jdbcTemplate.query(getBoardsQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("userId"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("status"))
        );
    }

    // 해당 title을 갖는 게시글 조회
    public List<GetBoardRes> getBoardsByTitle(String title) {
        String getUsersByTitleQuery = "select * from Board where title like ? and status=1";
        String getUsersByTitleParams = "%"+title+"%";
        return this.jdbcTemplate.query(getUsersByTitleQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("userId"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("status")),
                getUsersByTitleParams);
    }

    // 해당 boardIdx를 갖는 게시글 조회
    public GetBoardRes getBoard(int boardIdx) {
        String getBoardQuery = "select * from Board where boardIdx = ? and status=1";
        int getBoardParams = boardIdx;
        return this.jdbcTemplate.queryForObject(getBoardQuery,
                (rs, rowNum) -> new GetBoardRes(
                        rs.getInt("boardIdx"),
                        rs.getString("userId"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getBoolean("status")),
                getBoardParams);
    }
}
