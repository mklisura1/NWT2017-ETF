package TemplatesService.Security.Auth.Jwt.Extractor;

public interface TokenExtractor {
    public String extract(String payload);
}
