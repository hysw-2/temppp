package yu.spring.chap5.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {
    @NotBlank(message="회원 이름을 입력하세요")
    String name;

    @NotBlank(message="비밀번호를 입력하세요")
    String password;

    @NotBlank(message="이메일을 입력하세요")
    String email;
    LocalDate regdate;
}
