package me.itzblacky.ItzPermission.Handler;

import me.itzblacky.ItzCore.DependencyInterface.Utils.IGroup;
import me.itzblacky.ItzCore.DependencyInterface.Utils.IUser;

import java.util.ArrayList;
import java.util.List;

public class Holder {
    private Group defaultGroup;

    private List<User> users;
    private List<Group> groups;

    public Holder() {
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
        groups.add(new Group("Group1", 1));
        System.out.println(groups.get(0).getName());
        groups.add(new Group("Group2", 2));
        groups.add(new Group("Group3", 3));
        groups.add(new Group("Group4", 4));
        users.add(new User("uuid1", groups.get(0)));
        users.add(new User("uuid2", groups.get(1)));
        users.add(new User("uuid3", groups.get(2)));
        users.add(new User("uuid4", groups.get(3)));
    }

    public List<IUser> getUsers() {
        return new ArrayList<>(users);
    }

    public List<IGroup> getGroups() {
        return new ArrayList<>(groups);
    }

    public void addUser(IUser user) {
        users.add((User) user);
    }

    public void addGroup(IGroup group) {
        groups.add((Group) group);
    }

    public IGroup getDefaultGroup() {
        return defaultGroup;
    }
    public void setDefaultGroup(IGroup group) {
        this.defaultGroup = (Group) group;
    }
}
