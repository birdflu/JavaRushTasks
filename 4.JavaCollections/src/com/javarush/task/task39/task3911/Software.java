package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        Map<Integer, String> newHistoryMap = new LinkedHashMap<>();
        if (!versionHistoryMap.containsKey(rollbackVersion)) {
            return false;
        } else {
            Set<Integer> keySet = new HashSet<>();
            keySet.addAll(versionHistoryMap.keySet());

            for (Integer key : keySet) {
                if (key > rollbackVersion) {
                    versionHistoryMap.remove(key);
                }
            }
            currentVersion = rollbackVersion;
            return true;
        }
    }
}
