package concurrency.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container {
    public static final List<String> list = new ArrayList<>();
    public static final List<String> syncList = Collections.synchronizedList(new ArrayList<String>());

    synchronized void addEntry(String s){
        list.add(s);
    }

    void addToSyncList(String s) {
        syncList.add(s);
    }

    public static int size() {
        return list.size();
    }

    public static int sizeSyncList() {
        return syncList.size();
    }
}
