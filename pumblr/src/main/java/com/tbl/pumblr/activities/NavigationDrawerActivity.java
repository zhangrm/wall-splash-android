package com.tbl.pumblr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import com.tbl.pumblr.R;
import com.tbl.pumblr.fragments.PhotosFragment;
import com.tbl.pumblr.utils.SessionManager;
import com.tbl.pumblr.views.drawer.MaterialNavigationDrawer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 201503105229 on 2015/5/2.
 */
public class NavigationDrawerActivity extends MaterialNavigationDrawer implements View.OnClickListener{


    private View avatorImageViewLayout,menuLoginWelcomeLayout;

    private OnFilterChangedListener onFilterChangedListener;

    public void setOnFilterChangedListener(OnFilterChangedListener onFilterChangedListener) {
        this.onFilterChangedListener = onFilterChangedListener;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setOverflowShowingAlways();
        // create and set the header
        View headerView = LayoutInflater.from(this).inflate(R.layout.header, null);

        avatorImageViewLayout = headerView.findViewById(R.id.avatorImageViewLayout);
        menuLoginWelcomeLayout = headerView.findViewById(R.id.menuLoginWelcomeLayout);

        avatorImageViewLayout.setOnClickListener(this);
        menuLoginWelcomeLayout.setOnClickListener(this);



        this.setDrawerHeaderCustom(headerView);

        this.addDivisor();

        /*MaterialAccount account = new MaterialAccount(this.getResources(),"Name and Surname","subtitle or email",R.drawable.profile, R.drawable.header);
        this.addAccount(account);*/



        /*MaterialSection section = newSection("Section 1", R.drawable.menu_home_default, new Fragment());
        section.setNotifications(4);
        this.addSection(section);
        MaterialSection section2 = newSection("Section 2",R.drawable.menu_favor_default, new Fragment());
        section2.setNotifications(8);
        this.addSection(section2);
        MaterialSection section3 = newSection("Section 3",R.drawable.menu_about_default, new Fragment());
        section3.setNotifications(29);
        this.addSection(section3);*/
        View listView=  LayoutInflater.from(this).inflate(R.layout.menu_listview, null);
        this.setDrawerListCustom(listView);




        /*MaterialSection bottom = newSection("Setting", new Fragment());
        this.addBottomSection(bottom);*/


        View footerView = LayoutInflater.from(this).inflate(R.layout.menu_exit_footer, null);
        this.setDrawerFooterCustom(footerView);

        // for drawable resources
        //setDrawerHeaderImage(R.drawable.header);

        // and for bitmaps
        //this.setDrawerHeaderImage(myBitmap);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //menu.findItem(R.id.action_open_source).setIcon(R.drawable.toolbar_screen_selector);


        /*menu.addSubMenu(1, 1, 1, "Car");
        menu.addSubMenu(1, 1, 2, "House");
        menu.addSubMenu(1, 1, 3, "Beauty");
        menu.addSubMenu(1, 1, 4, "Handsome Boy");
        menu.addSubMenu(1, 1, 5,"Anime");*/
        /*menu.findItem(R.id.action_shuffle).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_shuffle).paddingDp(1).color(Color.WHITE).actionBarSize());*/

        return true;
    }



    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == avatorImageViewLayout.getId() || v.getId() == menuLoginWelcomeLayout.getId()){
            if(SessionManager.isUserLogin()){

            }else{
                startActivity(new Intent(this,IndexActivity.class));
            }
        }else if(true){

        }
    }


    public interface OnFilterChangedListener {
        public void onFilterChanged(int filter);
    }

}
