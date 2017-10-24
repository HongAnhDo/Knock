package com.example.hongdo.knock.fragments;

import android.app.Activity;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Win 8.1 Version 2 on 13/10/2017.
 */

public class CreateAccountFragment extends Fragment {
    Unbinder unbinder;
    FragmentManager fm;
    @BindView(R.id.pHead)
    TextView pHead;
    @BindView(R.id.btn_alreadyLogin)
    Button btnAlreadyLogin;
    @BindView(R.id.ed_name_create)
    EditText edNameCreate;
    @BindView(R.id.ed_email_create)
    EditText edEmailCreate;
    @BindView(R.id.ed_pass_create)
    EditText edPassCreate;
    @BindView(R.id.pBody)
    LinearLayout pBody;
    @BindView(R.id.btn_creat)
    Button btnCreat;
    @BindView(R.id.tv_alert)
    TextView tvAlert;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fm = getActivity().getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        addEvents();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void addEvents() {
        edNameCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvAlert.setText("");
                edNameCreate.setError(null);

                if (checkFill() == true)
                    btnCreat.setEnabled(true);
                else
                    btnCreat.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edPassCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvAlert.setText("");
                edPassCreate.setError(null);

                if (checkFill() == true)
                    btnCreat.setEnabled(true);
                else
                    btnCreat.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edEmailCreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edEmailCreate.setError(null);
                tvAlert.setText("");
                if (checkFill() == true)
                    btnCreat.setEnabled(true);
                else
                    btnCreat.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edPassCreate.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            hideKeyBoard();
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

    }

    private void hideKeyBoard() {
        View view1 = this.getActivity().getCurrentFocus();
        if (view1 != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
    }

    @OnClick({R.id.btn_alreadyLogin, R.id.btn_creat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_alreadyLogin:
                LoginFragment loginFragment = new LoginFragment();
                Util.replaceFragment(loginFragment, fm);
                break;
            case R.id.btn_creat:
                boolean createAccount = true;
                if (checkFill()) {
                    boolean check = Util.isEmailValid(edEmailCreate.getText().toString());
                    if (!check) {
                        tvAlert.setText(R.string.notify_alert_email);
                        edEmailCreate.setError("Email error");
                        createAccount = false;

                    }
                    if (edPassCreate.getText().length() < 6) {
                        edPassCreate.setError("Pass fail");
                        tvAlert.setText(R.string.notify_alert_pass);
                        createAccount = false;

                    }


                    if (createAccount)
                        Toast.makeText(getContext(), "Tạo thành công", Toast.LENGTH_SHORT).show();

                } else {
                    tvAlert.setText(R.string.notify_alert);
                    if (edPassCreate.getText().length() == 0 || edPassCreate.getText().toString() == null)
                        edPassCreate.setError("pass isempty");

                    if (edNameCreate.getText().length() == 0 || edNameCreate.getText().toString() == null)
                        edNameCreate.setError("name is empty");

                    if (edEmailCreate.getText().length() == 0 || edEmailCreate.getText().toString() == null)
                        edEmailCreate.setError("email is empty");
                }
                break;

        }
    }

    public boolean checkFill() {

        if (edEmailCreate.getText().toString().length() == 0 || edEmailCreate.getText().toString() == null ||
                edPassCreate.getText().toString().length() == 0 || edPassCreate.getText().toString() == null ||
                edNameCreate.getText().toString().length() == 0 || edNameCreate.getText().toString() == null)
            return false;

        return true;
    }
}
