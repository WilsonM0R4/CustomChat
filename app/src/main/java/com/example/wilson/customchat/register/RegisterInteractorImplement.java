package com.example.wilson.customchat.register;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterInteractorImplement implements RegisterInteractor {

    RegisterRepository registerRepository;

    public RegisterInteractorImplement(){
        registerRepository = new RegisterRepositoryImplement();
    }


    @Override
    public void signUp(String username,String email, String password) {
        if(registerRepository==null)
            registerRepository = new RegisterRepositoryImplement();

        registerRepository.signUp(username,email,password);
    }

    @Override
    public boolean getSignUpResult() {
        return registerRepository.getSignUpResult();
    }
}
