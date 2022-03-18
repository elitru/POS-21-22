package at.eliastrummer.pattern.immutable.exercise;

import java.util.ArrayList;
import java.util.List;

public class ImmutableTester {
    public static void main(String[] args) {
        List<Member> memberList = new ArrayList<>(){{
            add(new Member("John", "Doe"));
        }};
        Club club = new Club(1, "Club 1", memberList);
        ImmutableClub immutableClub = new ImmutableClub(2, "Club 2", memberList);

        club.addMember(new Member("John", "Cena"));
        System.out.println(club.getMembers().size());
        System.out.println(immutableClub.getMembers().size());
        immutableClub.addMember(new Member("Unused", "member"));
        System.out.println(immutableClub.getMembers().size());
    }
}