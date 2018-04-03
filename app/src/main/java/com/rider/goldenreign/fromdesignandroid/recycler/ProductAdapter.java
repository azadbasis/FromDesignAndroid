package com.rider.goldenreign.fromdesignandroid.recycler;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.R.*;

import com.rider.goldenreign.fromdesignandroid.R;
import com.rider.goldenreign.fromdesignandroid.databinding.ItemProductBinding;
import com.rider.goldenreign.fromdesignandroid.model.Product;

import java.util.List;

/**
 * Created by goldenreign on 4/3/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private final List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ItemProductBinding productBinding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ProductViewHolder(productBinding);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.setProduct(product);
            binding.imgProduct.setBackground(createProductBackground(product));
            binding.imgProduct.setImageResource(product.image);
        }

        @NonNull
        private GradientDrawable createProductBackground(Product product) {
            final GradientDrawable gradientDrawable = (GradientDrawable) ContextCompat.getDrawable(
                    itemView.getContext(), R.drawable.bg_product);

            gradientDrawable.setColor(ContextCompat.getColor(
                    itemView.getContext(), product.color));

            gradientDrawable.setSize(itemView.getWidth(), getDrawableHeight());
            gradientDrawable.mutate();
            return gradientDrawable;
        }

        private int getDrawableHeight() {
            final Context context = itemView.getContext();

            return getAdapterPosition() % 2 == 0
                    ? context.getResources().getDimensionPixelOffset(R.dimen.product_regular_height)
                    : context.getResources().getDimensionPixelOffset(R.dimen.product_large_height);
        }
    }
}
