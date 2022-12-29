package UwU_.GameSense;

import ru.salam4ik.bot.bot.BotStarter;
import UwU_.GameSense.altmanager.AltManager;
import UwU_.GameSense.altmanager.Session;
import UwU_.GameSense.bot.ProxyS;
import UwU_.GameSense.click.Screen;
import UwU_.GameSense.command.CommandManager;
import UwU_.GameSense.editor.Drag;
import UwU_.GameSense.editor.DragManager;
import UwU_.GameSense.helpers.ChangeLog;
import UwU_.GameSense.helpers.FriendManager;
import UwU_.GameSense.helpers.file.ClickGuiSave;
import UwU_.GameSense.helpers.render.TPS;
import UwU_.GameSense.module.Manager;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.impl.Render.FeatureList;
import UwU_.GameSense.notifications.NotificationManager;
import UwU_.GameSense.settings.config.ConfigManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.util.HashMap;

import static UwU_.GameSense.altmanager.AltManager.file;
import static UwU_.GameSense.altmanager.AltManager.sessions;
import static UwU_.GameSense.protect.Protection.getHwid;

public class Main {
    public static Manager m;
    public static Screen s;

    public static boolean protectedd = false;


    public static Robot imageRobot;
    public static ProxyS proxy = new ProxyS();
    public static HashMap<String, String> nickHash = new HashMap<>();
    public static String apiKey = "f601a7d599e39775a05da33304c958a8"; //"12704f09fe65b4b24bc3e80bf6ba682b";
    public static NotificationManager notify;
    public static ConfigManager c;
    public static AltManager alt;
    public static FriendManager f;
    public static double balance;
    public static CommandManager d = new CommandManager();

    public static String username;
    public static ChangeLog changeLog = new ChangeLog();
    public static int uid;
    public static String till;



    public void load() {

        new Discord().start();
        proxy.start();
        username = net.minecraft.client.main.Main.protection.nickname;
        uid = net.minecraft.client.main.Main.protection.uid;
        till = net.minecraft.client.main.Main.protection.till;
        String hwid = getHwid();
//        Document a = null;
//        try {
//            a = Jsoup.connect("http://89.107.10.34:7777?hwid=" + hwid).get();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        if (!a.text().equals(genKey(hwid))) {
//            System.exit(0);
//        }
        alt = new AltManager();
        try {
            imageRobot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        notify = new NotificationManager();
        m = new Manager();
        s = new Screen();
        c = new ConfigManager();
        DragManager.loadDragData();
        c.loadConfig("default");
        FeatureList.modules.addAll(Main.m.m);
        new TPS();
        f = new FriendManager();
        ClickGuiSave.load();
        BotStarter.init();
    }

    public void unload() {
        c.saveConfig("default");
        DragManager.saveDragData();
        try {
            if (file.exists()) file.delete();
            try (FileWriter fr = new FileWriter(file)) {
                for (Session s : sessions) {
                    fr.write(s.nick + "\n");
                }
            }

        } catch (Exception ex) {

        }
    }

    public static BufferedImage getScreenImage() {
        return imageRobot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public static Drag createDrag(Module module, String name, float x, float y) {
        DragManager.draggables.put(name, new Drag(module, name, x, y));
        return DragManager.draggables.get(name);
    }


    public static void keyEvent(int key) {
        for (Module m : m.m) {
            if (m.bind == key) {
                m.toggle();
            }
        }
    }
}
