package com.umc.boardcrud.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardIdx;

    private String userId;

    private String title;

    private String content;

    public Board toEntity(){
        return Board.builder()
                .boardIdx(boardIdx)
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
