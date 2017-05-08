package com.jlb.jinliangbao.fragment;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jlb.jinliangbao.QrcodeActivity;
import com.jlb.jinliangbao.R;

/**
 * Created by Administrator on 2017/4/25.
 */
public class AccountFragment extends Fragment implements View.OnClickListener{

    AlertDialog alertDialog;

    private AccountFragment() {
        super();
    }

    public static AccountFragment newInstance(){
        Bundle bundle = new Bundle();
        AccountFragment accountFragment = new AccountFragment();
        accountFragment.setArguments(bundle);
        return accountFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment,container,false);
        Button shimingBtn = (Button)view.findViewById(R.id.shiming_btn);

        ImageButton qrcodeBtn = (ImageButton)view.findViewById(R.id.qrcode_btn);
        qrcodeBtn.setOnClickListener(this);
        shimingBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shiming_btn:{
                if(alertDialog == null){
                    //自定义alert
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    //builder.setView(R.layout.alertdialog_shiming);

                    View view = getActivity().getLayoutInflater().from(getActivity()).inflate(R.layout.alertdialog_shiming,null);

                    Button yes = (Button)view.findViewById(R.id.yes);
                    Button no = (Button)view.findViewById(R.id.no);



                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"提交完成",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    builder.setView(view);
                    alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    alertDialog.show();
                }
                break;
            }
            case R.id.qrcode_btn:{
                Intent intent = new Intent(getActivity(), QrcodeActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            default:
        }
    }
}
