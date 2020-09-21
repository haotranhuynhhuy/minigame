package vn.edu.com.minigame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtDiem;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;
    ImageButton ibPlay;
    int soDiem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        DisableSeekbar();
        txtDiem.setText(soDiem+ "");
        final CountDownTimer countDownTimer = new CountDownTimer(20000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                sbOne.setProgress(sbOne.getProgress()+one);
                sbTwo.setProgress(sbTwo.getProgress()+two);
                sbThree.setProgress(sbThree.getProgress()+three);

                //kiem tra Win
                if(sbThree.getProgress()>=sbThree.getMax()){
                    this.cancel();
                    ibPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    if (cbThree.isChecked()){
                        soDiem +=10;
                        Toast.makeText(MainActivity.this, "YOU WIN", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this, "YOU LOSE", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem+"");
                    EnableCheckbox();
                }
                if(sbTwo.getProgress()>=sbTwo.getMax()){
                    this.cancel();
                    ibPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    if (cbTwo.isChecked()){
                        soDiem +=10;
                        Toast.makeText(MainActivity.this, "YOU WIN", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this, "YOU LOSE", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem+"");
                    EnableCheckbox();
                }
                if (sbOne.getProgress()>=sbOne.getMax()){
                    this.cancel();
                    ibPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    if (cbOne.isChecked()){
                        soDiem +=10;
                        Toast.makeText(MainActivity.this, "YOU WIN", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this, "YOU LOSE", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem+"");
                    EnableCheckbox();
                }
            }
            @Override
            public void onFinish() {

            }
        };
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbOne.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });

        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });

        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soDiem<=0){
                    Toast.makeText(MainActivity.this, "YOU HAVE NO MORE POINT", Toast.LENGTH_SHORT).show();
                }else{
                    if (cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                        sbOne.setProgress(0);
                        sbTwo.setProgress(0);
                        sbThree.setProgress(0);
                        ibPlay.setVisibility(view.INVISIBLE);
                        countDownTimer.start();
                        DisableCheckbox();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Please Check Before Run", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void EnableCheckbox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheckbox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

    private void DisableSeekbar(){
        sbOne.setEnabled(false);
        sbThree.setEnabled(false);
        sbTwo.setEnabled(false);
    }
    private void AnhXa(){
        txtDiem = findViewById(R.id.textDiem);
        cbOne = findViewById(R.id.checkboxOne);
        cbTwo = findViewById(R.id.checkboxTwo);
        cbThree = findViewById(R.id.checkboxThree);
        sbOne = findViewById(R.id.seekbarOne);
        sbTwo = findViewById(R.id.seekbarTwo);
        sbThree = findViewById(R.id.seekbarThree);
        ibPlay = findViewById(R.id.imageButton);
    }
}