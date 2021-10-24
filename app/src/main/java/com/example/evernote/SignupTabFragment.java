package com.example.evernote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evernote.userentity.Account;

public class SignupTabFragment extends Fragment {

    private Button signUp;

    EditText email;
    EditText password;
    EditText comfirm_password;

    Account _account;
    SendMessage SM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);

        signUp = (Button) root.findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validation() == true) {
                    _account = new Account(1, email.getText().toString(), password.getText().toString());

                    SM.sendData(_account);
                }
            }
        });

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        comfirm_password = root.findViewById(R.id.comfirm_pass);

        return root;
    }

    boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean validation() {
        if (!(isEmailValid(email.getText().toString()))) {
            Toast.makeText(getContext(), "Emailul nu este valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Parola nu este valid", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (comfirm_password.getText().toString().isEmpty() && password.getText().toString().compareTo(password.getText().toString()) == 0) {
            Toast.makeText(getContext(), "Parola nu se potriveste", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    interface SendMessage {
        void sendData(Account message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Eroare , datele nu au fost trimise cu succes");
        }
    }
}
