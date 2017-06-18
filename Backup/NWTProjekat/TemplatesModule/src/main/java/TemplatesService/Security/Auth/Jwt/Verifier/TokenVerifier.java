package TemplatesService.Security.Auth.Jwt.Verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
