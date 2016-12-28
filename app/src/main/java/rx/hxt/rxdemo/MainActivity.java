package rx.hxt.rxdemo;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.hxt.rxdemo.module.cache6.CacheFragment;
import rx.Observable;
import rx.functions.Action1;
import rx.hxt.rxdemo.module.elementary1.ElementaryFragment;
import rx.hxt.rxdemo.module.map2.MapFragment;
import rx.hxt.rxdemo.module.token4.TokenFragment;
import rx.hxt.rxdemo.module.token_advanced5.TokenAdcancedFragment;
import rx.hxt.rxdemo.module.zip3.ZipFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);



        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ElementaryFragment();
                    case 1:
                        return new MapFragment();
                    case 2:
                        return new ZipFragment();
                    case 3:
                        return new TokenFragment();
                    case 4:
                        return new TokenAdcancedFragment();
                    case 5:
                        return new CacheFragment();
                    default:
                        return new ElementaryFragment();
                }
            }

            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_elementary);
                    case 1:
                        return getString(R.string.title_map);
                    case 2:
                        return getString(R.string.title_zip);
                    case 3:
                        return getString(R.string.title_token);
                    case 4:
                        return getString(R.string.title_token_advanced);
                    case 5:
                        return getString(R.string.title_cache);
                    default:
                        return getString(R.string.title_elementary);
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);

                //.subscribe(s->log>e());
        Observable.just("123","456")
                .subscribe(s-> Log.e("hxt",s));
    }
}
