package rx.hxt.rxdemo.model;

/**
 * Created by Administrator on 2016/12/22.
 */

public class FakeToken {
    public String token;
    public boolean expired;

    public FakeToken() {
    }

    public FakeToken(boolean expired) {
        this.expired = expired;
    }
}
