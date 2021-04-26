package me.itzblacky.ItzPermission.Handler;

import me.itzblacky.ItzCore.DependencyInterface.Utils.IGroup;
import me.itzblacky.ItzCore.DependencyInterface.Utils.IUser;

import java.util.ArrayList;
import java.util.List;

public class Holder {


    private List<User> users;
    private List<Group> groups;

    private Group defaultGroup;


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
