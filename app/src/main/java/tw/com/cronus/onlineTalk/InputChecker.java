package tw.com.cronus.onlineTalk;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class InputChecker {
    private static boolean isEmailFormat(String input) {
        return input.matches("^\\w+((-\\w+)|(\\.\\w+))*@[A-Za-z0-9]+(([.\\-])[A-Za-z0-9]+)*\\.[A-Za-z]+$");
    }

    static void EmailCheck(final EditText sInput) {
        sInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() == 0) return;
                if (!isEmailFormat(charSequence.toString()))
                    sInput.setError("Email不符合格式!!!");
            }
        });     //密碼格式判斷
    }

    private static boolean isPasswordFormat(String input) {
        return input.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$");
    }

    private static boolean passwordLengthCheck(String input) {
        return input.matches("^.{9,20}$");
    }

    static void PasswordCheck(final EditText sInput, final EditText sConfirm) {
        sInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() == 0) return;
                if (!charSequence.toString().equals(sConfirm.getText().toString()))
                    sConfirm.setError("兩次密碼不一致");
                else sConfirm.setError(null);
                if (!isPasswordFormat(charSequence.toString()))
                    sInput.setError("密碼須包含英文大寫、小寫及數字");
                else if (!passwordLengthCheck(charSequence.toString()))
                    sInput.setError("密碼須介於9到20字之間");
            }
        });     //email格式判斷
    }

    static void PasswordConfirm(final EditText sPassword, final EditText sConfirmPassword) {
        sConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() == 0) return;
                if (!charSequence.toString().equals(sPassword.getText().toString()))
                    sConfirmPassword.setError("兩次密碼不一致");
            }
        });     //密碼確認
    }

    private static boolean isAccountFormat(String input) {
        return input.matches("^[A-Za-z0-9_.]+$");
    }

    private static boolean accountLengthCheck(String input) {
        return input.matches("^.{6,16}$");
    }

    static void AccountCheck(final EditText sInput) {
        sInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() == 0) return;
                if (!accountLengthCheck(charSequence.toString()))
                    sInput.setError("帳號長度需在6~16字之間");
                if (!isAccountFormat(charSequence.toString()))
                    sInput.setError("帳號只能有英文、數字、下畫線及點號");
            }
        });     //密碼格式判斷
    }
}
