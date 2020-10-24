package com.akaf.batmanapplication.utils;

import java.util.ArrayList;
import java.util.List;

public class SplitterTools {

    private static SplitterTools splitter_tools_instance = null;

    // static method to create instance of Singleton class
    public static SplitterTools getInstance()
    {
        if (splitter_tools_instance == null)
            splitter_tools_instance = new SplitterTools();

        return splitter_tools_instance;
    }

    public List<String> splitterStringList(String messageText) {
        List<String> splitterList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messageText.length(); i++)
            if (!(messageText.charAt(i) == ',')) {
                sb.append(messageText.charAt(i));
            } else {
                if (!sb.toString().isEmpty())
                    splitterList.add(0, sb.toString().trim());
                sb.delete(0, sb.length());
            }
        if (!sb.toString().isEmpty())
            splitterList.add(0, sb.toString().trim());
        return splitterList;
    }
}
