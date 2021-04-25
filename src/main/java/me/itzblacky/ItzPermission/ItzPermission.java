package me.itzblacky.ItzPermission;

import me.itzblacky.ItzCore.DependencyInterface.Permission;
import me.itzblacky.ItzCore.DependencyInterface.Utils.IGroup;
import me.itzblacky.ItzCore.DependencyInterface.Utils.IUser;
import me.itzblacky.ItzPermission.Handler.Holder;
import me.itzblacky.ItzPermission.Handler.User;
import mindustry.Vars;
import mindustry.gen.Player;
import mindustry.mod.Plugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static me.itzblacky.ItzPermission.Utils.FileUtils.loadYaml;
import static mindustry.io.JsonIO.copy;

public class ItzPermission extends Plugin implements Permission  {

    private Holder holder;

    public ItzPermission() {
        loadHolder();
    }

    public void loadHolder() {
        File file = new File(Vars.modDirectory.absolutePath() + "/ItzPermission/Permissions.yml");
        if(!file.exists()) {
            copy(this.getClass().getResourceAsStream("/Permissions.yml"), file.getAbsolutePath());
        }
        holder = loadYaml(file, Holder.class);
    }
    @Override
    public IUser getUser(Player player) {
        return getUser(player.uuid());
    }

    @Override
    public IUser getUser(String uuid) {
        IUser user = holder.getUsers().stream().filter(x -> x.getUUID().equals(uuid)).findFirst().orElse(null);
        if(user == null) {
            user = new User(uuid, holder.getDefaultGroup());
            addUser(user);
        }
        return user;
    }

    @Override
    public IGroup getGroup(String name) {
        return name.equals("default") ? holder.getDefaultGroup() : holder.getGroups().stream()
                .filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void addUser(IUser user) {
        holder.addUser(user);
    }

    @Override
    public void addGroup(IGroup group) {
        holder.addGroup(group);
    }

    @Override
    public void reload() {
        loadHolder();
    }

    @Override
    public void save() {
        Yaml yaml = new Yaml(new Constructor(Holder.class));
        yaml.setBeanAccess(BeanAccess.FIELD);
        File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getParentFile().getParentFile().getParentFile();
        try {
            FileWriter writer = new FileWriter(jarDir.getPath() + "/output.yml");
            writer.write(yaml.dumpAs(holder, Tag.MAP, null));
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
