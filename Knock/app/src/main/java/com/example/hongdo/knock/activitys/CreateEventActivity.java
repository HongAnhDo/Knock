package com.example.hongdo.knock.activitys;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.utils.ConvertCalendar;
import com.sevenheaven.iosswitch.ShSwitchView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventActivity extends AppCompatActivity {

    @BindView(R.id.mHeadFr)
    RelativeLayout mHeadFr;
    @BindView(R.id.view01)
    LinearLayout view01;
    @BindView(R.id.btn_more)
    ImageButton btnMore;
    @BindView(R.id.tv_tvHome)
    TextView tvTvHome;
    @BindView(R.id.view03)
    LinearLayout view03;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_timeStart)
    TextView tvTimeStart;
    @BindView(R.id.tv_DateEvent)
    TextView tvDateEvent;
    @BindView(R.id.view_time_start)
    RelativeLayout viewTimeStart;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.tv_timeFinish)
    TextView tvTimeFinish;
    @BindView(R.id.tv_DateEventEnd)
    TextView tvDateEventEnd;
    @BindView(R.id.view_time_end)
    RelativeLayout viewTimeEnd;
    @BindView(R.id.ibtn_moreRepeat)
    ImageButton ibtnMoreRepeat;
    @BindView(R.id.tv_titleEvent)
    EditText tvTitleEvent;
    @BindView(R.id.tv_locate)
    EditText tvLocate;
    @BindView(R.id.btnAdd)
    ImageButton btnAdd;
    @BindView(R.id.sh_switchView)
    ShSwitchView shSwitchView;
    private Calendar calendar = Calendar.getInstance();
    private Calendar timeStart;
    private Calendar timeEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        ButterKnife.bind(this);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorStausBar));

        setUpView();
    }

    private void setUpView() {
        timeStart = Calendar.getInstance();
        timeEnd = Calendar.getInstance();
        tvTimeStart.setText(ConvertCalendar.convertCtoSTime(timeStart));
        tvTimeFinish.setText(ConvertCalendar.convertCtoSTime(timeEnd));
        tvDateEvent.setText(ConvertCalendar.convertCtoSDate(timeStart));
        shSwitchView.setOnSwitchStateChangeListener(new ShSwitchView.OnSwitchStateChangeListener() {
            @Override
            public void onSwitchStateChange(boolean isOn) {
                if (isOn){
                    timeEnd = calendar;
                    timeEnd.set(Calendar.HOUR_OF_DAY, 23);
                    timeEnd.set(Calendar.MINUTE, 59);

                    timeStart = calendar;
                    timeStart.set(Calendar.HOUR_OF_DAY, 0);
                    timeStart.set(Calendar.MINUTE, 0);

                    tvTimeStart.setText(ConvertCalendar.convertCtoSTime(timeStart));
                    tvTimeFinish.setText(ConvertCalendar.convertCtoSTime(timeEnd));
                    Log.d("TV: " , timeStart.getTime() + ": " + timeEnd.getTime());

                    if (checkFill() && checkTime(timeStart, timeEnd)) {
                        btnAdd.setEnabled(true);

                    } else
                        btnAdd.setEnabled(false);


                }
            }
        });

        tvTitleEvent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkFill() && checkTime(timeStart, timeEnd)) {
                    btnAdd.setEnabled(true);

                } else
                    btnAdd.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvLocate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkFill() && checkTime(timeStart, timeEnd)) {
                    btnAdd.setEnabled(true);

                } else
                    btnAdd.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Calendar selectDate() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                tvDateEvent.setText(ConvertCalendar.convertCtoSDate(calendar));
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                onDateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();
        return calendar;
    }

    private Calendar selectTime(final TextView tvTime) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                tvTime.setText(ConvertCalendar.convertCtoSTime(calendar));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
        return calendar;
    }


    @OnClick({R.id.tv_timeStart, R.id.tv_DateEvent, R.id.tv_timeFinish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_timeStart:
                timeStart = selectTime(tvTimeStart);
                break;
            case R.id.tv_DateEvent:
                selectDate();
                break;
            case R.id.tv_timeFinish:
                timeEnd = selectTime(tvTimeFinish);
                break;
        }
    }

    private boolean checkTime(Calendar calendarStart, Calendar calendarEnd) {
        long st = calendarStart.getTimeInMillis();
        long end = calendarEnd.getTimeInMillis();
        Log.d("HAD", st + ":" + end);

        if (end <= st)
            return false;
        return true;
    }

    private boolean checkFill() {
        if (tvLocate.getText().length() == 0 || tvLocate.getText().toString() == null ||
                tvTitleEvent.getText().length() == 0 || tvTitleEvent.getText().toString() == null)
            return false;

        return true;
    }

    @OnClick({R.id.btnAdd, R.id.sh_switchView})
    public void onViewClicked() {

        boolean checkAdd = true;
        if (tvLocate.getText().length() == 0 || tvLocate.getText().toString() == null) {
            tvLocate.setError("require");
            checkAdd = false;
        }

        if (tvTitleEvent.getText().length() == 0 || tvTitleEvent.getText().toString() == null) {

            tvTitleEvent.setError("require");
            checkAdd = false;
        }

        if (!checkTime(timeStart, timeEnd))
            checkAdd = false;

        if (checkAdd) {
            Toast.makeText(this, "Add success", Toast.LENGTH_SHORT);
        }


    }

}
