package main;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Scanner;

public class Lab2Start {
    protected static Weld weld;
    protected static WeldContainer container;

    public static void main(String args[]) throws Exception {
        weld = new Weld();
        container = weld.initialize();

        try {
            Scanner scan = new Scanner(System.in);
            String str = "Внимание! Улыбнитесь!";
            CryptographerBean cryptographerBean = container.instance().select(CryptographerBean.class).get();
            String codeStr = cryptographerBean.stringCode(str);

            System.out.println(codeStr);
        } finally {
            weld.shutdown();
        }
    }
}
