package com.example.devtrainee.aadharbridgedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aadhaarconnect.bridge.capture.model.auth.AuthCaptureRequest;
import com.aadhaarconnect.bridge.capture.model.auth.Demographics;
import com.aadhaarconnect.bridge.capture.model.common.Location;
import com.aadhaarconnect.bridge.capture.model.common.LocationType;
import com.aadhaarconnect.bridge.capture.model.common.request.CertificateType;
import com.aadhaarconnect.bridge.capture.model.common.request.Modality;

public class DemographicFragment extends Fragment implements View.OnClickListener {

    EditText aadhaarEditText, nameEditText;
    
    Button btSubmit;
    boolean isEditable=true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_demographic,container,false);

        aadhaarEditText=(EditText) view.findViewById(R.id.et_aadhaar_id);
        nameEditText=(EditText) view.findViewById(R.id.et_name);

        aadhaarEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(isEditable){
                    isEditable=false;
                    String aadhaarNumber= AadhaarNumberFormatter.formatWithSpaces(charSequence.toString());
                    aadhaarEditText.setText(aadhaarNumber);
                    aadhaarEditText.setSelection(aadhaarNumber.length());
                }
                else{
                    isEditable=true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btSubmit=view.findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View view) {
        
        switch (view.getId()){
            
            case R.id.bt_submit:
                authenticate(view);
                break;
            
        }
        
    }

    private void authenticate(View view) {

        AuthCaptureRequest authCaptureRequest= new AuthCaptureRequest();
        authCaptureRequest.setAadhaar(aadhaarEditText.getText().toString()
                .replaceAll("\\s",""));
        authCaptureRequest.setModality(Modality.demo);
        authCaptureRequest.setCertificateType(CertificateType.preprod);

        Demographics demographics= new Demographics();
        Demographics.Name name= new Demographics.Name();
        name.setMatchingStrategy(Demographics.MatchingStrategy.exact);
        name.setNameValue(nameEditText.getText().toString());

        demographics.setName(name);
        authCaptureRequest.setDemographics(demographics);

        Location location= new Location();
        location.setType(LocationType.pincode);
        location.setPincode("560076");

        authCaptureRequest.setLocation();



    }
}
