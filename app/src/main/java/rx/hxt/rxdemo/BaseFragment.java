package rx.hxt.rxdemo;

import android.app.AlertDialog;
import android.app.Fragment;
import android.util.Log;
import android.widget.Button;

import butterknife.OnClick;
import rx.Subscription;

/**
 * Created by Administrator on 2016/12/22.
 */

public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @OnClick(R.id.tipBt)
    void tip() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getTitleRes())
                .setView(getActivity().getLayoutInflater().inflate(getDialogRes(), null))
                .show();
        Log.e("hxt","点了我");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();

}
