package pl.adkr.hsbc.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("posting")
                .apiInfo(apiEndPointsInfo())
                .select()
                .paths(PathSelectors.ant("/api/v**/message/**"))
                .build();
    }

    @Bean
    public Docket followingApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("following")
                .apiInfo(apiEndPointsInfo())
                .select()
                .paths(PathSelectors.ant("/api/v**/user/**"))
                .build();
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Code Challenge")
                .description("Twitter-like REST API")
                .contact(new Contact("Adrian Kremblewski", "", "kremblewski.adrian@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}