package at.eliastrummer.pattern.immutable.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ImmutableClub {
    private final int secret;
    private final String name;
    private final List<Member> members;

    public ImmutableClub(int secret, String name, List<Member> members) {
        this.secret = secret;
        this.name = name;
        this.members = members
                .stream()
                .map(m -> new Member(m.getFirstname(), m.getLastname()))
                .collect(Collectors.toUnmodifiableList());
    }

    public ImmutableClub addMember(Member member) {
        return new ImmutableClub(secret, name, new ArrayList<>(){{
            addAll(members);
            add(member);
        }});
    }

    public int getSecret() {
        return secret;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members
                .stream()
                .map(m -> new Member(m.getFirstname(), m.getLastname()))
                .collect(Collectors.toList());
    }
}
