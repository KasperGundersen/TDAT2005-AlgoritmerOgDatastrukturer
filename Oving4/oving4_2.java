import java.io.*;
import java.util.ArrayList;
import java.util.*;

class oving4_2 {

    public static char[] readFile(String filename) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        char[] list = null;

        try {
            fis = new FileInputStream("test.txt");
            bis = new BufferedInputStream(fis);
            
            int n = bis.available();
            list = new char[n];

            for (int i = 0; i < n; i++) {
                list[i] = (char)bis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean checkSourceCode(String filename) {
        char[] file = readFile(filename); 
        ArrayList<Character> stack = new ArrayList<Character>();
        boolean inString = false;
        for (int i = 0; i < file.length; i++) {
            if ((file[i] == '\'' || file[i] == '\"' ) && !inString) {
                inString = true;
            } else if ((file[i] == '\'' || file[i] == '\"') && inString) {
                inString = false;
            }
            
            if (!inString) {
                if (file[i] == '{' || file[i] == '[' || file[i] == '(') {
                    stack.add(file[i]);
                } else if (file[i] == '}') {
                    if (stack.get(stack.size() - 1) == '{') {
                        stack.remove(stack.size()-1);
                    } else {
                        return false;
                    }
                } else if (file[i] == ']') {
                    if (stack.get(stack.size() - 1) == '[') {
                        stack.remove(stack.size()-1);
                    } else {
                        return false;
                    }
                } else if (file[i] == ')') {
                    if (stack.get(stack.size() - 1) == '(') {
                        stack.remove(stack.size()-1);
                    } else {
                        return false;
                    }
                }
            }
        }
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkSourceCode("test.txt"));
    }
}