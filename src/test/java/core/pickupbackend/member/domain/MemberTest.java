package core.pickupbackend.member.domain;

import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberTest {

    @Test
    void 생성자로_멤버_생성_테스트() {
        final Member member = new Member(
                "example@email.com",
                "encodedPassword",
                "농구왕",
                180,
                75,
                Position.PG,
                Level.BEGINNER
        );

        assertThat(member).isNotNull();
    }

    @Test
    void 생성자로_멤버_생성_테스트시_잘못된_값_입력() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Member(
                    "",
                    "encodedPassword",
                    "농구왕",
                    180,
                    75,
                    Position.PG,
                    Level.BEGINNER
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            final Member member = new Member(
                    "example@email.com",
                    "",
                    "농구왕",
                    180,
                    75,
                    Position.PG,
                    Level.BEGINNER
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Member(
                    "example@email.com",
                    "encodedPassword",
                    "",
                    180,
                    75,
                    Position.PG,
                    Level.BEGINNER
            );
        });
    }

}