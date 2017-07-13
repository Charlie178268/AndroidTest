package com.example.learnrecy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final int FIRST_DIALOG = 1;
    private RecyclerView rv;
    private Spinner spinner = null;
    CheckBox checkBox1, checkBox2, checkBox3,checkBox4;
    TextView txtshow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rv = new RecyclerView(this);
//        setContentView(rv);
//        //设置布局：表格布局管理器，一行3列，堆叠排布
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//
//        rv.setLayoutManager(manager);
//        //设置数据适配器
//        rv.setAdapter(new MyAdapter());
        setContentView(R.layout.activity_main);
        final String[] datasource = new String[
                ]{"选项1", "选项2", "选项3"};
        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datasource));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("选择了："+datasource[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.setDateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    System.out.println("year-month-day:"+year+month+day);
                    new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            Button btn = (Button) findViewById(R.id.setDateBtn);
                            btn.setText(String.format("%d-%d-%d", i, i1, i2));
                        }
                    }, year, month, day).show();

                }
            }
        });

        findViewById(R.id.setTimeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            Button btn = (Button)findViewById(R.id.setTimeBtn);
                            btn.setText(String.format("%d:%d",i, i1));
                        }
                    },3,20,true).show();

                }
            }
        });

        Button submitBtn = (Button)findViewById(R.id.submitBtn);
        final RadioButton rightRadio = (RadioButton)findViewById(R.id.radio2);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightRadio.isChecked()){
                    Toast.makeText(MainActivity.this, "你所选的答案正确", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "你所选的答案错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtshow = (TextView) findViewById(R.id.showText);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
        checkBox4.setOnCheckedChangeListener(this);

        //autocomplete
        String[] str = new String[]{"SiChuan", "ShangHai", "Beigjing", "HangZhou"};
        AutoCompleteTextView autoCmp = (AutoCompleteTextView) findViewById(R.id.autoCmpText);
        autoCmp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str));

        Button tobtn = (Button)findViewById(R.id.tobtn);
        tobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //自定义视图
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);//生成该类的对象
                final View dialogView = inflater.inflate(R.layout.dialog, null);//将布局文件填充到view中，第二个参数是viewgroup，即容器，这里没有

                Toast toast = new Toast(MainActivity.this);
                toast.setView(dialogView);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 20, 20);
                toast.show();

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String str = "你喜欢吃：";
        if (checkBox1.isChecked()){
            str += checkBox1.getText();
            str += ",";
        }
        if (checkBox2.isChecked()){
            str += checkBox2.getText();
            str += ",";
        }
        if (checkBox3.isChecked()){
            str += checkBox3.getText();
            str += ",";
        }
        if (checkBox4.isChecked()){
            str += checkBox4.getText();
        }
        txtshow.setText(str);
    }

}
