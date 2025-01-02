package core.pickupbackend.match.fake;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.MatchStatus;
import core.pickupbackend.match.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMatchRepository implements MatchRepository {

    private final Map<Long, Match> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Match save(Match match) {
        if (match.getId() == null) {
            match.setId(sequence.getAndIncrement());
        }

        match.setCreatedAt(LocalDateTime.now());
        match.setUpdatedAt(LocalDateTime.now());
        store.put(match.getId(), match);
        return match;
    }

    @Override
    public Optional<Match> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Match> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Match> findByHostIdAndStatusOrderByCreatedAtDesc(Long hostId, MatchStatus status) {
        return store.values().stream()
                .filter(match -> Objects.equals(match.getHostId(), hostId) && match.getStatus() == status)
                .sorted(Comparator.comparing(Match::getCreatedAt).reversed())
                .toList();
    }

    @Override
    public void update(Match match) {
        if (!store.containsKey(match.getId())) {
            throw new NoSuchElementException("Match with ID " + match.getId() + " does not exist");
        }

        match.setUpdatedAt(LocalDateTime.now());
        store.put(match.getId(), match);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    public void clearStore() {
        store.clear();
        sequence.set(1L);
    }
}
