package com.example.hongdo.knock.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.activitys.CalendarActivity;
import com.example.hongdo.knock.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Win 8.1 Version 2 on 13/10/2017.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.tv_forgot)
    TextView tvForgot;
    Unbinder unbinder;

    FragmentManager fm;
    LostPassFragment lostPassFragment;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.ibtn_clear_email)
    ImageButton ibtnClearEmail;
    @BindView(R.id.edit_passWord)
    EditText editPassWord;
    @BindView(R.id.tv_notifi_alert)
    TextView tvNotifiAlert;
    @BindView(R.id.ibtn_clear_pass)
    ImageButton ibtnClearPass;
    @BindView(R.id.btn_sign_in)
    Button btnSignIn;

    private String email, pass;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fm = getActivity().getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        addEvent();
        return view;
    }

    private void addEvent() {
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvNotifiAlert.setText("");
                if (editEmail.getText().toString().length() == 0 || editEmail.getText().toString() == null) {
                    ibtnClearEmail.setVisibility(View.GONE);
                } else {
                    ibtnClearEmail.setVisibility(View.VISIBLE);
                }
                if (checkFill() == true)
                    btnSignIn.setEnabled(true);
                else
                    btnSignIn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                tvNotifiAlert.setText("");
                if (editPassWord.getText().toString().length() == 0 || editPassWord.getText().toString() == null) {
                    ibtnClearPass.setVisibility(View.GONE);

                } else {
                    ibtnClearPass.setVisibility(View.VISIBLE);
                }
                if (checkFill() == true) {
                    btnSignIn.setEnabled(true);

                } else
                    btnSignIn.setEnabled(false);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ibtnClearEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmail.setText("");
                editEmail.setError(null);
            }
        });
        ibtnClearPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPassWord.setText("");
                editPassWord.setError(null);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_sign_in, R.id.tv_forgot, R.id.edit_passWord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:

                if(checkFill()) {
                    boolean check = Util.isEmailValid(editEmail.getText().toString());
                    if (!check){
                        tvNotifiAlert.setText(R.string.notify_alert_email);
                        editEmail.setError("Email fail");
                        return;
                    }
                    if(editPassWord.getText().length() < 6){
                        tvNotifiAlert.setText(R.string.notify_alert_pass);
                        editPassWord.setError("Pass slow");
                        return;
                    }

                        Intent intent = new Intent(getActivity(), CalendarActivity.class);
                        startActivity(intent);

                }else
                {
                    tvNotifiAlert.setText(R.string.notify_alert);
                }
                break;
            case R.id.tv_forgot:
                lostPassFragment = new LostPassFragment();
                Util.replaceFragment(lostPassFragment, fm);
                break;
            case R.id.edit_passWord:
                editPassWord.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN) {
                            switch (keyCode) {
                                case KeyEvent.KEYCODE_DPAD_CENTER:
                                case KeyEvent.KEYCODE_ENTER:
                                    hideKeyBoard();
                                    return true;
                                default:
                                    break;
                            }
                        }
                        return false;
                    }
                });
        }
    }

    private void hideKeyBoard() {
        View view1 = this.getActivity().getCurrentFocus();
        if (view1 != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }


    public boolean checkFill() {

        if (editEmail.getText().toString().length() == 0 || editEmail.getText().toString() == null ||
                editPassWord.getText().toString().length() == 0 || editPassWord.getText().toString() == null)
            return false;

        return true;
    }


}
