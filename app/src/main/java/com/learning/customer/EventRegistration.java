package com.learning.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class EventRegistration extends AppCompatActivity implements PaymentResultListener {
    Button registerevent;
    EditText NameE,CollegenameE,CollegeStudentIdE,EmailE,PhoneE;
    DatabaseReference ref;
    String TAG="Payment Error";
    String idj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventregistration);
        registerevent=findViewById(R.id.buttonRegisterEvent);
        NameE=findViewById(R.id.eventregName);
        CollegenameE=findViewById(R.id.eventregCollegeName);
        CollegeStudentIdE=findViewById(R.id.eventregStudentID);
        EmailE=findViewById(R.id.eventregEmail);
        PhoneE=findViewById(R.id.eventregPhoneNumber);


        Intent intent =getIntent();
         idj= intent.getStringExtra(EventsAdapter.Event_ID);

        ref=FirebaseDatabase.getInstance().getReference("client");

        registerevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClient();
                startPayment();
            }
        });
    }

    private void saveClient() {
        String NameEj=NameE.getText().toString();
        String CollegenameEj=CollegenameE.getText().toString();
        String CollegeStudentIdEj=CollegeStudentIdE.getText().toString();
        String EmailEj=EmailE.getText().toString();
        String PhoneEj=PhoneE.getText().toString();

        if(NameEj.isEmpty())
        {
            NameE.setError("Name is required");
            NameE.requestFocus();
            return;
        }
        if(CollegenameEj.isEmpty())
        {
            CollegenameE.setError("College Name is required");
            CollegenameE.requestFocus();
            return;
        }
        if(CollegeStudentIdEj.isEmpty())
        {
            CollegeStudentIdE.setError("Student ID is required");
            CollegeStudentIdE.requestFocus();
            return;
        }
        if(EmailEj.isEmpty())
        {
            EmailE.setError("Email is required");
            EmailE.requestFocus();
            return;
        }
        if(PhoneEj.isEmpty())
        {
            PhoneE.setError("Phone Number is required");
            PhoneE.requestFocus();
            return;
        }

        String id=ref.push().getKey();
        Client client=new Client(id,NameEj,CollegenameEj,CollegeStudentIdEj,EmailEj,PhoneEj,idj);
        ref.child(id).setValue(client);
        Toast toast=Toast.makeText(EventRegistration.this,"You are successfully registered",Toast.LENGTH_SHORT);
        toast.show();

    }

    public void startPayment() {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        // checkout.setKeyID("rzp_test_Ls5G93gnQXeVCo");
        /**
         * Set your logo here
         */
        //checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Eventopedia");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "50000");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Payment Successful",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Error:Unable to make Payment",Toast.LENGTH_LONG).show();
    }
}
