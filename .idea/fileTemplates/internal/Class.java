#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")
import java.util.*;
import java.io.*;
//${NAME}.txt
class ${NAME} {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
    }
}
