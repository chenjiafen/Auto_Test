package cn.lizhi.http;

import org.apache.http.message.BasicHeader;

/**
 * @Author chenjiafneg
 * @Date 2020/8/5 16:55
 * @Version 1.0
 */
public class Header {
    private BasicHeader header = null;

    public Header(String name, String value) {
        this.header = new BasicHeader(name, value);
    }

    public BasicHeader header() {
        return header;
    }

    public void header(BasicHeader header) {
        this.header = header;
    }
}
