package part1;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    public enum Mode { NORMAL, REVERSE, SHUFFLE }

    private final List<Season> seasons;
    private final Mode mode;
    private int seasonIndex = 0;
    private EpisodeIterator current;

    public BingeIterator(List<Season> seasons, Mode mode) {
        this.seasons = seasons;
        this.mode = mode;
        this.current = createIteratorForSeason(seasons.get(0));
    }

    private EpisodeIterator createIteratorForSeason(Season season) {
        return switch (mode) {
            case REVERSE -> season.getReverseIterator();
            case SHUFFLE -> season.getShuffleIterator();
            default -> season.getIterator();
        };
    }

    @Override
    public boolean hasNext() {
        if (current.hasNext()) {
            return true;
        }
        while (++seasonIndex < seasons.size()) {
            current = createIteratorForSeason(seasons.get(seasonIndex));
            if (current.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return current.next();
    }
}