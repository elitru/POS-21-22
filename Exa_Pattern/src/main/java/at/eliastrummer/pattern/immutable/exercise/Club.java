package at.eliastrummer.pattern.immutable.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Club {
    private int secret;
    private String name;
    private List<Member> members;

    public Club(int secret, String name, List<Member> members) {
        this.secret = secret;
        this.name = name;
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
