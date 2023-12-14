package sis3;

import java.util.ArrayList;
import java.util.HashMap;

public class TrieTree {
    public ArrayList<HashMap<Character, Integer>> t;
    public int root = 0;

    public TrieTree()
    {
        t = new ArrayList<HashMap<Character, Integer>>();
        t.add(new HashMap<Character, Integer>());
    }

    public void addedge(String _s)
    {
        int pvidx = root;
        _s += '-';
        for (int i = 0; i < _s.length(); i++) {
            if (t.get(pvidx).containsKey(_s.charAt(i))) {
                pvidx = t.get(pvidx).get(_s.charAt(i));
            } else {
                t.get(pvidx).put(_s.charAt(i), t.size());
                t.add(new HashMap<Character, Integer>());
                pvidx = t.get(pvidx).get(_s.charAt(i));
            }
        }
    }

    public boolean ifcmp(String s) 
    {
        int pvidx = root;
        for (int i = 0; i < s.length(); i++) {
            if (t.get(pvidx).containsKey(s.charAt(i)))
                pvidx = t.get(pvidx).get(s.charAt(i));
            else
                return false;
        }
        return t.get(pvidx).containsKey('-');
    }
}
