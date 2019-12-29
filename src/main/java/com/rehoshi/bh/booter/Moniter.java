package com.rehoshi.bh.booter;

import java.net.MalformedURLException;
import java.util.Stack;

public class Moniter implements Runnable {

    private BhDriver bhDriver;
    private boolean first = true;
    private Stack<Booter> booterStack = new Stack<Booter>();
    private Booter curBooter;

    public Moniter(Booter curBooter) {
        this.curBooter = curBooter;
    }

    public void run() {
        synchronized (this) {
            if (this.bhDriver != null) {
                throw new RuntimeException("Only can run once");
            }
            try {
                this.bhDriver = new BhDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            if (booterStack.isEmpty()) {
                first = true;
                curBooter.bindDriver(this.bhDriver);
                booterStack.push(curBooter);
            } else {
                Booter peek = booterStack.peek();
                if (peek != curBooter) {
                    first = true;
                    curBooter.bindDriver(this.bhDriver);
                    booterStack.push(curBooter);
                }
            }

            if (first) {
                //识别成功则表示初始化完成
                first = !curBooter.recognizeSense();
            } else {
                //识别每一帧
                int status = curBooter.recognizeFrame();
                switch (status) {
                    case Booter.RecognizeStatus.STAY_CUR_SENSE:
                        break;
                    case Booter.RecognizeStatus.TO_NEXT_SENSE:
                        this.curBooter = this.curBooter.getNextBooter() ;
                        break;
                    case Booter.RecognizeStatus.TO_PRE_SENSE:
                        this.curBooter = this.booterStack.pop() ;
                        break;
                }

            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void start() {
        new Thread(this).start();
    }
}
