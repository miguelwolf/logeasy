package br.com.miguelwolf.logeasy.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import br.com.miguelwolf.logeasy.R;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private TextView mTextMessage;
    private GoogleMap mMap;

    private InicioFragment inicioFragment;
    private PesquisarFragment pesquisarFragment;
    private TarefasFragment tarefasFragment;
    private EmpresaFragment empresaFragment;
    private PerfilFragment perfilFragment;
//    private OpcoesFragment opcoesFragment;

    private BottomNavigationView navigation;

    private FrameLayout mMainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);

//        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        inicioFragment = new InicioFragment();
        pesquisarFragment = new PesquisarFragment();
        tarefasFragment = new TarefasFragment();
        empresaFragment = new EmpresaFragment();
        perfilFragment = new PerfilFragment();


        setFragment(inicioFragment);

//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(inicioFragment);
                    return true;

                case R.id.navigation_search:
                    setFragment(pesquisarFragment);
                    return true;

                case R.id.navigation_task:
                    setFragment(tarefasFragment);
                    return true;

                case R.id.navigation_agregados:
                    setFragment(empresaFragment);
                    return true;

                case R.id.navigation_perfil:
                    setFragment(perfilFragment);
                    return true;
            }
            return false;
        }

    };

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
