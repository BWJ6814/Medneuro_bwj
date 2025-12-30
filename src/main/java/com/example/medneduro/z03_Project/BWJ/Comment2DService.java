package com.example.medneduro.z03_Project.BWJ;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Comment2DService {

    /**
     * ✔ final 필드
     * - 생성자에서 반드시 주입되어야 함
     * - 객체 생성 이후 변경 불가 (불변성)
     */
    private final Comment2DMapper mapper;

    /**
     * ✔ 생성자 주입
     * - @RequiredArgsConstructor 없이 직접 작성
     * - Spring이 자동으로 Bean 주입
     */
    public Comment2DService(Comment2DMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * ✅ 저장 (신규 / 수정)
     * - commentId가 없으면 insert
     * - commentId가 있으면 update
     */
    public Long save(Comment2DDto dto) {
        System.out.println(dto.toString());

        // ====== 최소 검증 로직 (현업에서도 이 정도는 거의 항상 함) ======
        if (dto.getMedMriId() == null) {
            throw new IllegalArgumentException("medMriId is required");
        }
        if (dto.getStaffId() == null || dto.getStaffId().isBlank()) {
            throw new IllegalArgumentException("staffId is required");
        }
        if (dto.getPatientId() == null || dto.getPatientId().isBlank()) {
            throw new IllegalArgumentException("patientId is required");
        }
        if (dto.getAxis() == null || dto.getAxis().isBlank()) {
            throw new IllegalArgumentException("axis is required");
        }
        if (dto.getSliceIndex() == null) {
            throw new IllegalArgumentException("sliceIndex is required");
        }
        if (dto.getXCoord() == null || dto.getYCoord() == null) {
            throw new IllegalArgumentException("x/y coord is required");
        }

        // 내용이 null이면 빈 문자열로 처리 (DB 안정성)
        if (dto.getContent() == null) {
            dto.setContent("");
        }

        // ====== 신규 등록 ======
        if (dto.getCommentId() == null) {
            mapper.insert(dto);

            // MyBatis에서 방금 insert된 PK 조회
            return mapper.selectLastInsertId();
        }

        // ====== 수정 ======
        mapper.update(dto);
        return dto.getCommentId();
    }

    /**
     * ✅ 특정 슬라이스의 코멘트 목록 조회
     */
    public List<Comment2DDto> list(Long medMriId, String axis, Integer sliceIndex) {
        return mapper.findBySlice(medMriId, axis, sliceIndex);
    }

    /**
     * ✅ 삭제
     */
    public void delete(Long commentId) {
        mapper.delete(commentId);
    }

    public List<Comment2DDto> listByMri(Long medMriId) {
        if (medMriId == null) {
            throw new IllegalArgumentException("medMriId is required");
        }
        return mapper.findByMriId(medMriId);
    }

}