

import App

/**
 * description:
 * create       2017/6/16 18:51
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 *
 */
class GroovyTestMain {
    def age
    def alias

    static void main(args) {
        App app = new App(name: "test")
        println app.name
    }

    def toStr = {
        "${age} ${alias}"
    }

    @Override
    String toString(){
        toStr.call()
    }


}

