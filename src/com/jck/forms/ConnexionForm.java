package com.jck.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jck.beans.Utilisateur;

public class ConnexionForm {
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs     = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {

        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = MethodsInOneBox.getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

        try {
            MethodsInOneBox.validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }

        utilisateur.setEmail( email );

        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( motDePasse );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Echec de la connexion";
        }

        return utilisateur;

    }

    private void validationMotDePasse( String motDepasse ) throws Exception {
        if ( motDepasse != null ) {
            if ( motDepasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères" );
            }

        } else {
            throw new Exception( "Merci de saisir votre mot de passe" );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );

    }
}
