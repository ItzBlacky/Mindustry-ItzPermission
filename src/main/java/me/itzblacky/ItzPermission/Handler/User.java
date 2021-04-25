package me.itzblacky.ItzPermission.Handler;

import me.itzblacky.ItzCore.DependencyInterface.Utils.IGroup;
import me.itzblacky.ItzCore.DependencyInterface.Utils.IUser;

import java.util.ArrayList;
import java.util.List;

public class User implements IUser {

    private String uuid;
    private List<String> permissions;
    private IGroup group;

    public User(String uuid, IGroup group) {
        this(uuid, group, new ArrayList<>());
    }

    public User(String uuid, IGroup group, List<String> permissions) {
        this.uuid = uuid;
        this.group = group;
        this.permissions = permissions;
    }

    @Override
    public void setGroup(IGroup group) {
        this.group = group;
    }

    @Override
    public void setPermission(String permission) {
        permissions.remove(permission.startsWith("-") ? permission : "-" + permission);
        permissions.add(permission);
    }

    @Override
    public void removePermission(String permission) {
        permissions.remove(permission);
    }

    @Override
    public boolean hasPermission(String permission) {
        return group.hasPermission(permission) && !permission.contains("-" + permission);
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public IGroup getGroup() {
        return group;
    }
}
