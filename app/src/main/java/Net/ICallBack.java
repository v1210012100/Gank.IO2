package Net;


public interface ICallBack<T> {

    public void onSuccess(String flag, String key, T t);

    public void onFailure(String flag, String key, String why);

}
