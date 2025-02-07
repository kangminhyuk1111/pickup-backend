package core.pickupbackend.match.repository;

import core.pickupbackend.match.fake.FakeMatchRepository;
import core.pickupbackend.match.application.out.MatchRepository;

class JdbcMatchRepositoryTest {

    private final MatchRepository matchRepository = new FakeMatchRepository();

}