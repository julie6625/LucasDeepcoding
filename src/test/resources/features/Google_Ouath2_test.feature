Feature: Google OAuth2 Login

  @googleoauth2login
  Scenario: User can authenticate with Google Oauth2
    Given the navigates to Google Oauth2 login page
    When the user complete the login with valid Google credentials
    Then the user should receive an access token