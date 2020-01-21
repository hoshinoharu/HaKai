package com.rehoshi.bh.booter;

import java.net.MalformedURLException;
import java.util.Stack;

public class Moniter implements Runnable {

    private BhDriver bhDriver;
    private boolean first = true;
    private boolean bootSuccess = false;
    private Stack<Booter> booterStack = new Stack<>();
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
                bootSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bootSuccess) {
            while (true) {
                this.bhDriver.startFrame();
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

                System.out.println("当前Booter： " + curBooter.getClass());
                //拦截识别方法 做一些提前处理
                int status = curBooter.interceptRecognize();

                if (status == Booter.RecognizeStatus.NOT_INTERCEPT) {
                    if (first) {
                        //识别成功则表示初始化完成
                        first = !curBooter.recognizeSense();
                        curBooter.onRecognizeSenseFinish();

                        if (curBooter.recognizeSenseTimeout()) {
                            //识别场景错误回到上一个场景
                            status = Booter.RecognizeStatus.TO_PRE_SENSE;
                        } else {
                            status = Booter.RecognizeStatus.NO_ACTION;
                        }
                    } else {
                        //识别每一帧
                        status = curBooter.recognizeFrame();
                    }
                }

                Booter nextBooter = this.curBooter.getNextBooter();
                if (status == Booter.RecognizeStatus.TO_PRE_SENSE || this.curBooter.isFinish()) {
                    finishCurBooter();
                }
                switch (status) {
                    case Booter.RecognizeStatus.STAY_CUR_SENSE:
                        break;
                    case Booter.RecognizeStatus.TO_NEXT_SENSE:
                        if (nextBooter != null) {
                            this.curBooter = nextBooter;
                        }
                        break;
                }
                this.bhDriver.endFrame();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //重新启动
            this.run();
        }
    }

    private void finishCurBooter() {
        //弹出当前booter
        Booter pop = this.booterStack.pop();
        if(!this.booterStack.empty()){
            this.curBooter = this.booterStack.peek();
        }else {
            this.curBooter = pop ;
        }
    }


    public void start() {
        new Thread(this).start();
    }
}
