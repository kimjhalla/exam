package com.kb.exam.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return GroupedOpenApi
                .builder()
                .group("user") // group 설정 (API들을 그룹화시켜 그룹에 속한 API들만 확인할 수 있도록 도와줌)
                .pathsToMatch("/v1/**") // group에 포함될 API endpoint 경로
                .addOpenApiCustomizer(
                        openApi ->
                                openApi
                                        .setInfo(
                                                new Info()
                                                        .title("KB Health Care Exam API") // API 제목
                                                        .description("KB 헬스케어 과제 API") // API 설명
                                                        .version("1.0.0") // API 버전
                                        )
                )
                .build();
    }
}
