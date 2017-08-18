package com.jck.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jck.beans.Utilisateur;

public class InscriptionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "motdepasse";
    private static final String CHAMP_CONF  = "confirmation";
    private static final String CHAMP_NOM   = "nom";

    private String              resultat;
    private Map<String, String> erreurs     = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat( String resultat ) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs( Map<String, String> erreurs ) {
        this.erreurs = erreurs;
    }

    public Utilisateur inscrireUtilisateur( HttpServletRequest request ) {

        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String nom = getValeurChamp( request, CHAMP_NOM );

        Utilisateur utilisateur = new Utilisateur();

        try {
            validationEmail( email );

        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }

        utilisateur.setEmail( email );

        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        utilisateur.setMotDePasse( motDePasse );

        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }

        utilisateur.setNom( nom );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Echec de l'inscription";
        }

        return utilisateur;

    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valable." );
            } else {
                throw new Exception( "Merci de saisir une adresse mail valable." );

            }
        }

    }

    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "les mots de passe entrés sont différents, mercide les saisir à nouveau" );
            } else if ( motDePasse.trim().length() < 3 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            } else {
                throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
            }
        }

    }

    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
            throw new Exception( "Le nom d'utilsateur doit contenir au moins 3 caractères" );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );

    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {

            return null;
        } else {

            return valeur.trim();
        }
    }

}
