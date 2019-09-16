package com.henrique.fructose.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.henrique.fructose.R;
import com.henrique.fructose.session.Session;
import com.henrique.fructose.session.UserSession;

import java.util.Arrays;
import java.util.Collection;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.henrique.fructose.R.string.default_web_client_id;
import static com.henrique.fructose.util.LoadingHelper.failure;

public class LoginFragment extends Fragment {

    private static final int RC_SIGN_IN = 200;

    GoogleSignInOptions signInOptions;
    CallbackManager callbackManager;
    private Button btLoginFacebook, btLoginGoogle;
    private UserSession userSession;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth fireAuth = FirebaseAuth.getInstance();

    private ConstraintLayout cl;
    private ProgressBar pbLogando;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        //isLogado();
        // configurarLayout();
        initiateObjects();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_fragment, container, false);
        configurarLayout(rootView);
        return rootView;
    }

    private void configurarLayout(View rootView) {

        btLoginFacebook = rootView.findViewById(R.id.btLoginFacebook);
        btLoginGoogle = rootView.findViewById(R.id.btLoginGoogle);

        cl = rootView.findViewById(R.id.clLogionRoot);
        pbLogando = rootView.findViewById(R.id.pbLoginLoad);

        btLoginFacebook.setOnClickListener(view -> {
            Collection<String> permissions = Arrays.asList("email", "public_profile");

            LoginManager.getInstance().logInWithReadPermissions(
                    LoginFragment.this,
                    permissions);

            loading();
        });

        btLoginGoogle.setOnClickListener(view -> {
            logarGoogle();
            loading();
        });
    }

    private void initiateObjects() {

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResults) {
                        GraphRequest mGraphRequest = GraphRequest.newMeRequest(
                                loginResults.getAccessToken(), (me, response) -> {
                                    if (response.getError() != null) {
                                        // handle error
                                        failure(getActivity());
                                    } else {
                                        String email = me.optString("email");
                                        String name = me.optString("name");
                                        String profileImg = "https://graph.facebook.com/" + loginResults.getAccessToken().getUserId() + "/picture?type=large&width=1080";
                                        AutenticarFacebook(loginResults.getAccessToken(), name, profileImg, email);
                                        LoginManager.getInstance().logOut();

                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email, picture");
                        mGraphRequest.setParameters(parameters);
                        mGraphRequest.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Snackbar.make(cl, "Login cancelado", Snackbar.LENGTH_LONG).show();
                        displayLogin();
                        initLogin();
                    }

                    @Override
                    public void onError(FacebookException e) {
                        failure(getActivity());
                        displayLogin();
                        initLogin();
                    }
                });

        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestIdToken(getString(default_web_client_id))
                .requestProfile()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), signInOptions);
    }

    private void logarGoogle() {
        Intent logIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(logIntent, RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount conta = result.getSignInAccount();
                AutenticarGoogle(conta);
            } else {
                displayLogin();
                failure(getActivity());
            }
        }

    }

    private void AutenticarGoogle(final GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        fireAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task ->
                {
                    if (task.isSuccessful()) {

                        userSession = new UserSession();
                        userSession.setEmail(acct.getEmail());
                        userSession.setName(acct.getDisplayName());
                        String urlFoto = acct.getPhotoUrl().toString();
                        if (!urlFoto.equals(null))
                            userSession.setFotoUri(acct.getPhotoUrl().toString());

                        userSession.selfUpdate();
                        Session.getInstance(getActivity()).updateUserData();
                    } else {
                        displayLogin();
                        String mensagem;
                        if (!task.isSuccessful() | !task.isComplete()) {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException | FirebaseAuthUserCollisionException e) {
                                mensagem = "Faça o login com o Facebook\n" + e.getLocalizedMessage();

                            } catch (Exception e) {
                                mensagem = "Erro no servidor";
                                //Log.e(TAG, e.getMessage());
                            }
                        } else {
                            mensagem = "Algo deu errado ao completar o login";
                        }

                        displayLogin();

                        failure(getActivity());

                    }
                    exitGoogle();

                });
    }

    private void AutenticarFacebook(final AccessToken token, final String name,
                                    final String fotoPerfil, final String email) {
        // Log.d(TAG, "LOGIN FACEBOOK:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        fireAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task ->
                {
                    if (task.isSuccessful()) {

                        userSession = new UserSession();
                        FirebaseUser fu = task.getResult().getUser();
                        userSession.setEmail(email);
                        userSession.setName(name);
                        userSession.setFotoUri(fotoPerfil);
                        userSession.setTelefone(fu.getPhoneNumber());

                    } else {
                        displayLogin();
                        String mensagem;
                        if (!task.isSuccessful()) {

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException | FirebaseAuthUserCollisionException e) {
                                mensagem = "Faça o login com o Google";

                            } catch (Exception e) {
                                mensagem = "Erro no servidor";
                                // Log.e(TAG, e.getMessage());
                            }
                        } else {
                            mensagem = "Algo deu errado ao completar o login";
                        }

                        displayLogin();

                        Snackbar s = Snackbar.make(cl, mensagem, Snackbar.LENGTH_INDEFINITE);
                        s.setAction("OK", v -> initLogin());
                        s.show();


                    }
                    LoginManager.getInstance().logOut();

                });
    }

    private void exitGoogle() {

        googleSignInClient.signOut().addOnCompleteListener(getActivity(),
                task -> {
                });
    }

    private void initLogin() {
        fireAuth.signOut();
        googleSignInClient.signOut();
        LoginManager.getInstance().logOut();
        exitGoogle();
        if (Session.getInstance(getActivity()).isUserLogged())
            Session.getInstance(getActivity()).DeleteSessionUser();
    }

    private void displayLogin() {
        pbLogando.setVisibility(GONE);
        btLoginGoogle.setEnabled(true);
        btLoginFacebook.setEnabled(true);
    }

    private void loading() {
        pbLogando.setVisibility(VISIBLE);
        btLoginGoogle.setEnabled(false);
        btLoginFacebook.setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        // TODO Verificar se existe conexão com a internet
    }

}