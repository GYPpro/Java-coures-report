package sis4;

public class regularExp {

    public static boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] m = new boolean[lenS+1][lenP+1];
        m[0][0] = true;
        for(int i = 1; i <= lenS; i++){
            m[i][0] = false;
        }
        for(int j = 1; j <= lenP; j++){
            m[0][j] = false;
            if(j>=2 && p.charAt(j-1)=='*'){
                 m[0][j] = m[0][j-2];
            }
        }
        for(int i = 1; i <= lenS; i++){
            for(int j = 1; j <= lenP; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                    m[i][j] = m[i-1][j-1];
                }
                else if(p.charAt(j-1) == '*'){
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        m[i][j] = m[i-1][j] || m[i][j-2];
                    }
                    else{
                        m[i][j] = m[i][j-2];
                    }
                }
            }
        }
        return m[lenS][lenP];
        
    }
}
