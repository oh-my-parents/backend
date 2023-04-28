package omp.omp.domain.userquestion.domain;

import lombok.Getter;

/**
 *  질문지에서 구분하기 위함
 *  FATHER : 아버지용
 *  MOTHER : 어머니용
 */
@Getter
public enum ParentType {

    FATHER("아버지"),
    MOTHER("어머니");

    private final String parent;

    ParentType(String parent) {
        this.parent = parent;
    }
}
