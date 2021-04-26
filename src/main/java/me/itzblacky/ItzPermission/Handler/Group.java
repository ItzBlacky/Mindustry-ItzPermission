package me.itzblacky.ItzPermission.Handler;

import me.itzblacky.ItzCore.DependencyInterface.Utils.IGroup;

import java.util.ArrayList;
import java.util.List;

public class Group implements IGroup {
    private String name;
    private int id;
    private List<String> permissions;
    private IGroup subGroup;

    public Group(String name, int id) {
        this(name, id, new ArrayList<>(), null);
    }
    public Group(String name, int id, List<String> permissions, IGroup subGroup) {
        this.name = name;
        this.id = id;
        this.permissions = permissions;
        this.subGroup = subGroup;
    }
    public Group() {

    }

    @Override
    public boolean hasPermission(String permission) {
        return subGroup != null ? (subGroup.hasPermission(permission) && !permissions.contains("-" + permission)) : permissions.contains(permission);
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
    public void setSubGroup(IGroup group) {
        this.subGroup = group;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public IGroup getSubGroup() {
        return subGroup;
    }
}
