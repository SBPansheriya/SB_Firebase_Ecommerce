package com.example.sb_firebase_ecommerce.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.sb_firebase_ecommerce.R;
import com.example.sb_firebase_ecommerce.databinding.ActivityAddProductFragmentBinding;
import com.example.sb_firebase_ecommerce.databinding.FragmentViewProductBinding;

public class ViewProduct_Fragment extends Fragment {

    FragmentViewProductBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentViewProductBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
//        View view = inflater.inflate(R.layout.fragment_view_product_, container, false);

        return view;
    }
}