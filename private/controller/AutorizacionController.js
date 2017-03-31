function autorizar(req, res) {
    var accountLinkingToken = req.query.account_linking_token;
    var redirectURI = req.query.redirect_uri;

    var authCode = "1234567890";

    var redirectURISuccess = redirectURI + "&authorization_code=" + authCode;

    res.render('authorize', {
        accountLinkingToken: accountLinkingToken,
        redirectURI: redirectURI,
        redirectURISuccess: redirectURISuccess
    });
}

module.exports = {
    autorizar: autorizar
}
