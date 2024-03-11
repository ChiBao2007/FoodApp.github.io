package bao.huynh.food_app_arnc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import bao.huynh.food_app_arnc.Adapter.FOODADAPTER;
import bao.huynh.food_app_arnc.Fragment.FragmentAccount;
import bao.huynh.food_app_arnc.Fragment.FragmentCard;
import bao.huynh.food_app_arnc.Fragment.FragmentFavorite;
import bao.huynh.food_app_arnc.Fragment.FragmentHome;
import bao.huynh.food_app_arnc.Fragment.FragmentMap;
import bao.huynh.food_app_arnc.Fragment.FragmentPromotion;
import bao.huynh.food_app_arnc.Fragment.FragmentSupport;
import bao.huynh.food_app_arnc.Fragment.Fragment_Message;
import bao.huynh.food_app_arnc.Fragment.Fragment_Notification;

@ExperimentalBadgeUtils public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    SearchView searchView;

    FragmentHome fragmentHome = new FragmentHome();
    FragmentCard fragmentCard = new FragmentCard();
    FragmentFavorite fragmentFavorite = new FragmentFavorite();
    Fragment_Notification fragment_notification = new Fragment_Notification();
    Fragment_Message fragmentMessage = new Fragment_Message();
    FragmentSupport fragmentSupport = new FragmentSupport();

    FragmentAccount fragmentAccount = new FragmentAccount();
    FragmentPromotion fragmentPromotion = new FragmentPromotion();
    FragmentMap fragmentMap = new FragmentMap();
    Fragment currentfragment;

    BadgeDrawable badgeNoti,badgeMess, badgeFavorite;
    FOODADAPTER foodadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anhxa();
        bottomNavigationView.setBackground(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mnuHome:
                        currentfragment = fragmentHome;
                        break;
                    case R.id.mnuFavorite:
                        currentfragment = fragmentFavorite;
                        badgeFavorite.setVisible(true);
                        break;
                    case R.id.mnuNotification:
                        currentfragment = fragment_notification;
                        badgeNoti.setVisible(false);
                        break;
                    case R.id.mnuMessage:
                        currentfragment = fragmentMessage;
                        badgeMess.setVisible(false);
                        break;
                }
                LoadFragment(currentfragment);
                return true;
            }
        });
        LoadFragment(fragmentHome);
        LoadCart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            handleIntentExtras(extras);
        }

//        searchView.onActionViewExpanded();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                foodadapter.getFilter().filter(newText);
//                return false;
//            }
//        });
    }

    private void LoadCart() {



        badgeNoti = bottomNavigationView.getOrCreateBadge(R.id.mnuNotification);
        badgeNoti.setNumber(0);
        badgeNoti.setBackgroundColor(Color.RED);

        badgeMess = bottomNavigationView.getOrCreateBadge(R.id.mnuMessage);
        badgeMess.setNumber(0);
        badgeMess.setBackgroundColor(Color.RED);

        badgeFavorite = bottomNavigationView.getOrCreateBadge(R.id.mnuFavorite);
        badgeFavorite.setNumber(0);
        badgeFavorite.setBackgroundColor(Color.RED);


    }


    private void handleIntentExtras(Bundle extras) {

//


        FragmentCard fragmentCard = new FragmentCard();

        fragmentCard.setArguments(extras);
        LoadFragment(fragmentCard);

        getIntent().removeExtra("quantity");
        getIntent().removeExtra("food");
    }
    private void anhxa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        bottomNavigationView = findViewById(R.id.bot_nav_view);
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchmain);

        // Kiểm tra xem các view có null không
        if (drawerLayout == null || navigationView == null || bottomNavigationView == null || toolbar == null || searchView == null) {
            // Xử lý khi có view là null
            return;
        }

        searchView.onActionViewExpanded();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);

//        MenuItem cartMenuItem = menu.findItem(R.id.mnu_cart);

//        cartMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(@NonNull MenuItem item) {
//                handleCartIconClick();
//                return true;
//            }
//        });

//        updateCartBadge(cartMenuItem.getActionView(), 5);

//        badgeCart = BadgeDrawable.create(this);
//        badgeCart.setBackgroundColor(Color.RED);
//        badgeCart.setNumber(0);

        return super.onCreateOptionsMenu(menu);
        
    }

//    private void updateCartBadge(View view, int i) {
//        if (view != null) {
//            RelativeLayout badgeLayout = view.findViewById(R.id.badge_layout);
//            if (badgeLayout != null) {
//                TextView badgeTextView = badgeLayout.findViewById(R.id.badge);
//                if (badgeTextView != null) {
//                    if (i > 0) {
//                        badgeTextView.setText(String.valueOf(i));
//                        badgeTextView.setVisibility(View.VISIBLE);
//                    } else {
//                        badgeTextView.setVisibility(View.GONE);
//                    }
//                }
//            }
//        }
//    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mnu_home1:
                currentfragment = fragmentHome;
                break;
            case R.id.mnu_info:
                currentfragment = fragmentAccount;
                Toast.makeText(this, "Infomation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnu_Promotion:
                currentfragment = fragmentPromotion;
                Toast.makeText(this, "Promotion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuSupport:
                currentfragment = fragmentSupport;
                Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuMap:
                currentfragment = fragmentMap;
                Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show();
                break;
        }
        LoadFragment(currentfragment);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnu_cart:
                handleCartIconClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleCartIconClick() {
        FragmentCard fragmentCard = new FragmentCard();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framemain, fragmentCard)
                .addToBackStack(null)
                .commit();

        Toast.makeText(this, "Giỏ hàng của bạn", Toast.LENGTH_SHORT).show();
    }

    void LoadFragment(Fragment f) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framemain, f);
        transaction.commit();
    }
}
