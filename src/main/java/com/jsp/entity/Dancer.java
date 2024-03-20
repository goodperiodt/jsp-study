package com.jsp.entity;

import java.util.List;
import java.util.Objects;

public class Dancer {
    // enum타입의 DanceLevel 객체 선언

    public enum DanceLevel {
        BEGINNER("초보", 50000),
        AMATEUR("아마추어", 100000),
        PROFESSIONAL("프로페셔널", 200000);
        private final String levelDescription; // 레벨 설명
        private final int payPerEvent; // 공연당 페이

        DanceLevel(String levelDescription, int payPerEvent) {
            this.levelDescription = levelDescription;
            this.payPerEvent = payPerEvent;
        }

        public String getLevelDescription() {
            return levelDescription;
        }

        public int getPayPerEvent() {
            return payPerEvent;
        }
    }

    // enum 타입의 장르 객체 선언
    public enum Genre {
        HIPHOP("힙합"),
        STREET("스트릿"),
        KPOP("케이팝"),

        ;


        private final String genreDescription; // 장르 설명

        Genre(String genreDescription) {
            this.genreDescription = genreDescription;
        }

        public String getGenreDescription() {
            return genreDescription;
        }
    }

    private String name; // 댄서 이름
    private String crewName; // 댄서 팀 이름
    private DanceLevel danceLevel; // 댄서 실력 등급
    private List<Genre> genres; // 댄선의 장르

    public Dancer() {}

    public Dancer(String name, String crewName, DanceLevel danceLevel, List<Genre> genres) {
        this.name = name;
        this.crewName = crewName;
        this.danceLevel = danceLevel;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public DanceLevel getDanceLevel() {
        return danceLevel;
    }

    public void setDanceLevel(DanceLevel danceLevel) {
        this.danceLevel = danceLevel;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dancer dancer = (Dancer) o;
        return Objects.equals(getName(), dancer.getName()) && Objects.equals(getCrewName(), dancer.getCrewName()) && getDanceLevel() == dancer.getDanceLevel() && Objects.equals(getGenres(), dancer.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCrewName(), getDanceLevel(), getGenres());
    }

    @Override
    public String toString() {
        return "Dancer{" +
                "name='" + name + '\'' +
                ", crewName='" + crewName + '\'' +
                ", danceLevel=" + danceLevel +
                ", genres=" + genres +
                '}';
    }
}
