package com.example.cryptotest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FirstFragment extends Fragment {
    public SecretKey secretkey;
    public byte[] encryptedMessage;
    public IvParameterSpec ivspec;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View genkey = view.findViewById(R.id.button_genkey);
        genkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View keyBox = ((View) view.getParent()).findViewById(R.id.key);
                TextInputEditText key = (TextInputEditText) keyBox;
                ivspec = MainActivity.generateIv();
                try {
                    secretkey = MainActivity.generateKey();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String privateKeyString = new String(secretkey.getEncoded());
                key.setText(privateKeyString);
            }
        });
        View encrypt = view.findViewById(R.id.button_encrypt);
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View keyBox = ((View) view.getParent()).findViewById(R.id.key);
                View message = ((View) view.getParent()).findViewById(R.id.message1);
                TextView messageFinal = ((View) view.getParent()).findViewById(R.id.messageFinal);
                TextInputEditText messageT = (TextInputEditText) keyBox;
                String messageText = (String.valueOf(messageT.getText()));
                try {
                    encryptedMessage = MainActivity.encrypt(messageText, secretkey, ivspec);
                    messageFinal.setText(new String(encryptedMessage));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        View decrypt = view.findViewById(R.id.button_decrypt);
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View keyBox = ((View) view.getParent()).findViewById(R.id.key);
                View message = ((View) view.getParent()).findViewById(R.id.message1);
                TextView messageFinal = ((View) view.getParent()).findViewById(R.id.messageFinal);
                TextInputEditText messageT = (TextInputEditText) keyBox;
                String messageText = (String.valueOf(messageT.getText()));
                encryptedMessage = encryptedMessage;
                try {
                    messageFinal.setText(MainActivity.decrypt(encryptedMessage, secretkey, ivspec));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}