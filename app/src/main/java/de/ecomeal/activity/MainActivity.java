package de.ecomeal.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.foundation_icons_typeface_library.FoundationIcons;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import de.ecomeal.fragment.WebFragment;
import de.ecomeal.interfaces.ChangeFragmentListener;
import de.ecomeal.fragment.ProductsFragment;
import de.ecomeal.R;

public class MainActivity extends BaseToolbarActivity implements ChangeFragmentListener {

    public static final String childViewFragment = "ProductFragment";
    private Toolbar toolbar;

    //Fragments
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
//        initViews();

        changeFragment(getFragmentToStart(childViewFragment), false);
    }


    private void initDrawer() {
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeightDp(98)
                .withHeaderBackground(R.drawable.eco_logo_header)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                }).build();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSliderBackgroundColor(ContextCompat.getColor(this, R.color.red_ecomeal))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Товары").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal)).withIcon(FoundationIcons.Icon.fou_marker),
                        new PrimaryDrawerItem().withName("Где купить?").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal)).withIcon(FoundationIcons.Icon.fou_marker),
                        new PrimaryDrawerItem().withName("Партнерам").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal)).withIcon(FoundationIcons.Icon.fou_torsos_all),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Сертификаты").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal)).withIcon(FoundationIcons.Icon.fou_page_doc),
                        new SecondaryDrawerItem().withName("Контакты").withTextColor(ContextCompat.getColor(this, R.color.white_ecomeal)).withIcon(FoundationIcons.Icon.fou_telephone)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                changeFragment(ProductsFragment.newInstance(),false);
                                break;
                            case 2:
                                changeFragment(WebFragment.newInstance("http://ecomeal.de/where-to-buy.html"),false);
                                break;
                        }

                        return false;
                    }
                })
                .build();
    }


    private Fragment getFragmentToStart(String nameFragment) {
        Fragment fragment = null;
        switch (nameFragment) {
            case childViewFragment:
                fragment = ProductsFragment.newInstance();
                break;
        }
        return fragment;
    }

    public void changeFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null && (currentFragment == null || !fragment.getClass().equals(currentFragment.getClass()))) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void shouldReplace(Fragment fragment, boolean addToBackStack) {
        changeFragment(fragment, addToBackStack);
    }
}
