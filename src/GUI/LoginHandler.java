package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView=loginView;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ENTER==e.getKeyCode()) {
            loginMethod();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JButton jButton= (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("清除")){
            loginView.getNameInput().setText("");
            loginView.getPasswordInput().setText("");
        }else if(text.equals("登录")){
            loginMethod();
        }

    }

    private void loginMethod() {
        String userStr = loginView.getNameInput().getText();
        String passwordStr = loginView.getPasswordInput().getText();
        System.out.println("输入账号"+userStr);
        System.out.println("输入密码"+passwordStr);
        boolean flag = passwordStr.equals("123456")&&userStr.equals("admin");
        if(flag){
            System.out.println("登陆成功");
            new MainView();
            loginView.dispose();
        }
        else {
            System.out.println("登陆失败");
            loginView.getNameInput().setText("");
            loginView.getPasswordInput().setText("");
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }
}
