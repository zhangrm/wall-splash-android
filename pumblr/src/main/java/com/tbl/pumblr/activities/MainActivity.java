package com.tbl.pumblr.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.tbl.pumblr.R;
import com.tbl.pumblr.models.ImageList;
import com.tbl.pumblr.network.UnsplashApi;


public class MainActivity extends ActionBarActivity {
    public enum Category {
        ALL(1000),
        FEATURED(1001),
        LOVED(1002),
        BUILDINGS(1),
        FOOD(2),
        NATURE(4),
        PEOPLE(8),
        TECHNOLOGY(16),
        OBJECTS(32),

        Car(1),
        Luxury(2),
        Beauty(3),
        HandsomeBoy(4),
        Comic(5)

        ;

        public final int id;

        private Category(int id) {
            this.id = id;
        }
    }

    public Drawer.Result result;
    public AccountHeader.Result headerResult;

    private OnFilterChangedListener onFilterChangedListener;

    public void setOnFilterChangedListener(OnFilterChangedListener onFilterChangedListener) {
        this.onFilterChangedListener = onFilterChangedListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        // Create the AccountHeader
        headerResult = new AccountHeader()
            .withActivity(this)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile)),
                new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
            )
            .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                @Override
                public void onProfileChanged(View view, IProfile iProfile) {

                }
            })
            .build();

        result = new Drawer()
            .withActivity(this)
            .withToolbar(toolbar)
            .withHeader(R.layout.header)
            .withAccountHeader(headerResult)
            .withFooter(R.layout.footer)
            .addDrawerItems(
                    /*new PrimaryDrawerItem().withName(R.string.category_all).withIdentifier(Category.ALL.id).withIcon(GoogleMaterial.Icon.gmd_landscape),
                    new PrimaryDrawerItem().withName(R.string.category_featured).withIdentifier(Category.FEATURED.id).withIcon(GoogleMaterial.Icon.gmd_grade),
                    new SectionDrawerItem().withName(R.string.category_section_categories),
                    new PrimaryDrawerItem().withName(R.string.category_buildings).withIdentifier(Category.BUILDINGS.id).withIcon(GoogleMaterial.Icon.gmd_location_city),
                    new PrimaryDrawerItem().withName(R.string.category_food).withIdentifier(Category.FOOD.id).withIcon(GoogleMaterial.Icon.gmd_local_bar),
                    new PrimaryDrawerItem().withName(R.string.category_nature).withIdentifier(Category.NATURE.id).withIcon(GoogleMaterial.Icon.gmd_local_florist),
                    new PrimaryDrawerItem().withName(R.string.category_objects).withIdentifier(Category.OBJECTS.id).withIcon(GoogleMaterial.Icon.gmd_style),
                    new PrimaryDrawerItem().withName(R.string.category_people).withIdentifier(Category.PEOPLE.id).withIcon(GoogleMaterial.Icon.gmd_person),
                    new PrimaryDrawerItem().withName(R.string.category_technology).withIdentifier(Category.TECHNOLOGY.id).withIcon(GoogleMaterial.Icon.gmd_local_see)*/

                    new PrimaryDrawerItem().withName(R.string.category_car).withIdentifier(Category.Car.id).withIcon(GoogleMaterial.Icon.gmd_landscape),
                    new PrimaryDrawerItem().withName(R.string.category_luxury).withIdentifier(Category.Luxury.id).withIcon(GoogleMaterial.Icon.gmd_grade),
                    new PrimaryDrawerItem().withName(R.string.category_beauty).withIdentifier(Category.Beauty.id).withIcon(GoogleMaterial.Icon.gmd_location_city),
                    new PrimaryDrawerItem().withName(R.string.category_handsome_boy).withIdentifier(Category.HandsomeBoy.id).withIcon(GoogleMaterial.Icon.gmd_local_florist),
                    new PrimaryDrawerItem().withName(R.string.category_comic).withIdentifier(Category.Comic.id).withIcon(GoogleMaterial.Icon.gmd_person)
            )
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem drawerItem) {
                    if (drawerItem != null) {
                        if (drawerItem instanceof Nameable) {
                            toolbar.setTitle(((Nameable) drawerItem).getNameRes());
                        }
                        if (onFilterChangedListener != null) {
                            onFilterChangedListener.onFilterChanged(drawerItem.getIdentifier());
                        }
                    }
                }
            })
            .build();

        //disable scrollbar :D it's ugly
        result.getListView().setVerticalScrollBarEnabled(false);

        //Show the back arrow
        /*result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        //Show the hamburger icon
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);*/
    }

    /**
     * @param images
     */
    public void setCategoryCount(ImageList images) {
        if (result.getDrawerItems() != null && /*result.getDrawerItems().size() == 9 &&*/ images != null && images.getData() != null) {
            /*result.updateBadge(images.getData().size() + "", 0);
            result.updateBadge(UnsplashApi.countFeatured(images.getData()) + "", 1);

            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.BUILDINGS.id) + "", 3);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.FOOD.id) + "", 4);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.NATURE.id) + "", 5);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.OBJECTS.id) + "", 6);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.PEOPLE.id) + "", 7);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.TECHNOLOGY.id) + "", 8);*/

            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.Car.id) + "", 0);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.Luxury.id) + "", 1);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.Beauty.id) + "", 2);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.HandsomeBoy.id) + "", 3);
            result.updateBadge(UnsplashApi.countCategory(images.getData(), Category.Comic.id) + "", 4);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_open_source).setIcon(new IconicsDrawable(this, FontAwesome.Icon.faw_desktop).color(Color.WHITE).actionBarSize());
        menu.findItem(R.id.action_shuffle).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_shuffle).paddingDp(1).color(Color.WHITE).actionBarSize());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_open_source) {
            new Libs.Builder()
                    .withFields(R.string.class.getFields())
                    .withActivityTitle(getString(R.string.action_open_source))
                    .withActivityTheme(R.style.MaterialDrawerTheme_ActionBar)
                    .withLibraries("rxJava", "rxAndroid")
                    .start(this);
            /*startActivity(new Intent(MainActivity.this,AboutActivity.class));*/


            return true;
        }
        return false; //super.onOptionsItemSelected(item);
    }

    public interface OnFilterChangedListener {
        public void onFilterChanged(int filter);
    }
}
