package com.example.infrastructure.jwt;

import com.example.infrastructure.jwt.external.ExternalProvidersConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig({ExternalProvidersConfiguration.class})
public class ExternalProviderTest {

    String jwt = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImI2ZjhkNTVkYTUzNGVhOTFjYjJjYjAwZTFhZjRlOGUwY2RlY2E5M2QiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiMTU1MTY3ODYwODAxLXE1aGowaGkzaGhkcmd0b24xYW9wbGZkMDI2dGdmY2g3LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTU1MTY3ODYwODAxLXE1aGowaGkzaGhkcmd0b24xYW9wbGZkMDI2dGdmY2g3LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAxOTUyNzEyOTU5MzA2MjQ2NDk5IiwiaGQiOiJyZXNwdWJsaWNhLm9yZy5wbCIsImVtYWlsIjoicGlvdHIuc3V3YWxhQHJlc3B1YmxpY2Eub3JnLnBsIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJLdlk1eFJtOGg4WXV3YmR4azkxN1Z3IiwibmFtZSI6IlBpb3RyIFN1d2HFgmEiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFUWEFKekJ1VkxZSXVLMktrX1pKNmZmcFAxa05kSlQ5a2VHbXhvRHhBeVY9czk2LWMiLCJnaXZlbl9uYW1lIjoiUGlvdHIiLCJmYW1pbHlfbmFtZSI6IlN1d2HFgmEiLCJsb2NhbGUiOiJwbCIsImlhdCI6MTYyNTA4MTkxOSwiZXhwIjoxNjI1MDg1NTE5LCJqdGkiOiJlYWUwNjJhZDIxZDAxMjljZTQ1OGVlODI3NGIwMjZhM2QyMjE0YTM5In0.zzO6dBzNSDLVIEMmc4krUFxqgfyGWD3ZK_AWGrXYybi8uKs2Uk918ftcRX3M6NJmJ43yuAZF9xDNGFc0c8aQSmKsAjcqsZEVxrFgvav0crv_R0Oim1zo8q-7B6jmtZzvnDyrPvXI1bMP929K3jvpT3JD1vB8hqRiyaKhkKxAvk_MpUYi8emFD36kVKl0VaG2n-e_fm1e1MOEdhMKiSaeCz0Zpdwust6lbGOnheaCee5QE0Yu7zxm2xsIavvUugM6VCtncQvtHFEfovsy2uPN4lvugidl5fmJmidJ8XaocpLVy9I1tXSxo08yWr1kpeufosB38HPEiw1TeCsW2PnGqQ";

    @Autowired
    AuthenticationProvider googleProvider;

    @Disabled("Unfortunately it is not easy to override the clock :<")
    @Test
    public void whenCorrectGoogleProviderIsGivenWhenValidatingCorrectTokenThenPasses() {
        final var auth = googleProvider.authenticate(new BearerTokenAuthenticationToken(jwt));
    }
}