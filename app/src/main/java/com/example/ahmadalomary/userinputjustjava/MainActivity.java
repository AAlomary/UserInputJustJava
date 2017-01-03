package com.example.ahmadalomary.userinputjustjava;

    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.TextView;

    import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int total=0;
    int totalPrice=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void addMore(View view) {
            display(1);
        }

        public void submitOrder(View view) {
            displayPrice();
        }
         public void minusMore(View view) {
             if(total > 0) {
                 display(-1);
             }
        }

        private void display(int number) {
            TextView quantityTextView = (TextView) findViewById(R.id.quantityTextView);
            total += number;
            quantityTextView.setText("" + total);
        }

        private void displayPrice() {
            TextView priceTextView = (TextView) findViewById(R.id.priceTextView);
            totalPrice = total * 5;
            priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(totalPrice));
        }
    }

