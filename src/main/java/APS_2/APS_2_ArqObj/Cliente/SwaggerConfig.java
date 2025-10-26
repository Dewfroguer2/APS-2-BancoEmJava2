package APS_2.APS_2_ArqObj.Cliente;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean

    // cria um botão Authorize e envia automaticamente o header token nas chamadas quando autorizado.
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "token-header";
        return new OpenAPI()
                .info(new Info().title("API Banco APS-2").version("3.0")
                        .description("API para Clientes, contas e Cartões"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("token")
                                .description("Token temporário gerado pela rota /Usuario/login")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));

    }
}
