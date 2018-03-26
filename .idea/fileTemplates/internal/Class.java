#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import java.util.*;
import java.io.*;

@SuppressWarnings("Duplicates")
class ${NAME} {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/${NAME}.txt"));
    }
}
