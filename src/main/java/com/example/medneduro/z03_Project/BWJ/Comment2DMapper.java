package com.example.medneduro.z03_Project.BWJ;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Comment2DMapper {
    /**
     * ✅ 1) 신규 저장
     * - COMMENT_ID는 시퀀스로 생성
     * - content는 CLOB이라도 String으로 넣으면 오라클이 처리 가능
     */
    @Insert("""
        INSERT INTO DIAGNOSIS_COMMENT_2D (
            COMMENT_ID, AXIS, SLICE_INDEX, X_COORD, Y_COORD, CONTENT,
            MED_MRI_ID, STAFF_ID, PATIENT_ID, CREATED_AT
        ) VALUES (
            DIAGNOSIS_COMMENT_2D_SEQ.NEXTVAL,
            #{axis}, #{sliceIndex}, #{xCoord}, #{yCoord}, #{content},
            #{medMriId}, #{staffId}, #{patientId}, SYSTIMESTAMP
        )
    """)
    int insert(Comment2DDto dto);

    /**
     * ✅ 2) 방금 생성된 PK 조회(오라클에서 가장 단순한 방법 중 하나)
     * - 같은 세션에서 CURRVAL을 가져옴
     */
    @Select("SELECT DIAGNOSIS_COMMENT_2D_SEQ.CURRVAL FROM DUAL")
    Long selectLastInsertId();

    /**
     * ✅ 3) 수정 (commentId로 content/좌표 등을 갱신)
     * - 필요하면 axis/sliceIndex도 수정 가능
     */
    @Update("""
        UPDATE DIAGNOSIS_COMMENT_2D
           SET CONTENT = #{content},
               X_COORD = #{xCoord},
               Y_COORD = #{yCoord}
         WHERE COMMENT_ID = #{commentId}
    """)
    int update(Comment2DDto dto);

    /**
     * ✅ 4) 조회 (현재 MRI + axis + sliceIndex에 해당하는 코멘트들)
     * - 화면에 마커를 다시 그릴 때 사용
     */
    @Select("""
        SELECT
            COMMENT_ID   AS commentId,
            MED_MRI_ID   AS medMriId,
            STAFF_ID     AS staffId,
            PATIENT_ID   AS patientId,
            AXIS         AS axis,
            SLICE_INDEX  AS sliceIndex,
            X_COORD      AS xCoord,
            Y_COORD      AS yCoord,
            CONTENT      AS content,
            CREATED_AT   AS createdAt
        FROM DIAGNOSIS_COMMENT_2D
        WHERE MED_MRI_ID = #{medMriId}
          AND AXIS = #{axis}
          AND SLICE_INDEX = #{sliceIndex}
        ORDER BY COMMENT_ID ASC
    """)
    List<Comment2DDto> findBySlice(
            @Param("medMriId") Long medMriId,
            @Param("axis") String axis,
            @Param("sliceIndex") Integer sliceIndex
    );

    /**
     * ✅ 5) 삭제
     */
    @Delete("DELETE FROM DIAGNOSIS_COMMENT_2D WHERE COMMENT_ID = #{commentId}")
    int delete(@Param("commentId") Long commentId);
}