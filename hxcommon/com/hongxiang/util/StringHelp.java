package com.hongxiang.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelp {

    private static Pattern p = Pattern.compile("(\\{(\\d)\\})");
    public static String Format(String str,String... strings)
    {
        Matcher m = p.matcher(str);
        ArrayList<Integer> hole = new ArrayList<Integer>(); 
        while(m.find())
           if(m.groupCount() == 2)
               try {
                   hole.add(Integer.parseInt(m.group(2)));
            } catch (Exception e) {}
        Collections.sort(hole);
        if(hole.size() != strings.length) return "";
        
        int temp = -1;
        for (Integer item : hole) {
            if(temp == -1){
                if(item != 0) return "";
                temp = item; continue;
            }
            else {
               if(item - temp != 1) return "";
               temp = item;
            }
        }
        
        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0;i < hole.size(); i++) {
            String symbol = '{'+hole.get(i).toString()+'}';
            int index = -1;
            while((index = buffer.indexOf(symbol)) != -1 )
                buffer.replace(index, index+symbol.length(), strings[i]);
        }
        
        return buffer.toString();
    }
    
}
 