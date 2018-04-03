package com.rider.goldenreign.fromdesignandroid;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;



import com.rider.goldenreign.fromdesignandroid.databinding.ActivityHomeBinding;
import com.rider.goldenreign.fromdesignandroid.model.Product;
import com.rider.goldenreign.fromdesignandroid.recycler.OnItemSelectedListener;
import com.rider.goldenreign.fromdesignandroid.recycler.ProductAdapter;
import com.rider.goldenreign.fromdesignandroid.recycler.ProductItemPaddingDecoration;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {
    private final List<Product> fakeProducts = Product.createFakeProducts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_home);

        initRecycler(binding.productsRecycler);
    }

    private void initRecycler(RecyclerView productsRecycler) {
        productsRecycler.setAdapter(new ProductAdapter(fakeProducts));
        productsRecycler.addItemDecoration(new ProductItemPaddingDecoration(this));
        productsRecycler.addOnItemTouchListener(new OnItemSelectedListener(this) {
            @Override
            public void onItemSelected(RecyclerView.ViewHolder holder, int position) {
                OrderDialogFragment.newInstance(fakeProducts.get(position)).show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}